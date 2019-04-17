package com.yiban.yiban_application.common;
import cn.yiban.open.common.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiban.yiban_application.annotation.Log;
import com.yiban.yiban_application.javabean.SysLog;
import com.yiban.yiban_application.system.dao.LogInterface;
import com.yiban.yiban_application.util.AppUtil;
import com.yiban.yiban_application.util.HttpContextUtils;
import com.yiban.yiban_application.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

	@Autowired
    private LogInterface logInterface;

	@Pointcut("@annotation(com.yiban.yiban_application.annotation.Log)")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint point) throws IOException {
		Object result = null;
		long beginTime = System.currentTimeMillis();
		try {
			result = point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long time = System.currentTimeMillis() - beginTime;
		saveLog(point, time);
		return result;
	}

	private void saveLog(ProceedingJoinPoint joinPoint, long time) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(new Date());
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLog log = new SysLog();
		Log logAnnotation = method.getAnnotation(Log.class);
		if (logAnnotation != null) {
			log.setOperation(logAnnotation.value());
		}
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		log.setMethod(className + "." + methodName + "()");
		Object[] args = joinPoint.getArgs();
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paramNames = u.getParameterNames(method);
		if (args != null && paramNames != null) {
			StringBuilder params = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				params.append("  ").append(paramNames[i]).append(": ").append(args[i]);
			}
			log.setParams(params.toString());
		}
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		log.setIp(IPUtils.getIpAddr(request));
		log.setUsername("admin");
		log.setTime(time);
		log.setCreateTime(dateNowStr);
		log.setLocation(getXpath());
		this.logInterface.insertLog(log);
	}

	private static String getXpath() throws IOException {
		HttpURLConnection connection;
		InputStream is;
		BufferedReader br;
		String result = null;
		URL url = new URL(AppUtil.APP_IP_LOACAL);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(15000);
		connection.setReadTimeout(60000);
		connection.connect();
		if (connection.getResponseCode() == 200) {
			is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			StringBuilder sbf = new StringBuilder();
			String temp;
			while ((temp = br.readLine()) != null) {
				sbf.append(temp);
				sbf.append("\r\n");
			}
			result = sbf.toString();
		}
		return result;
	}
}
