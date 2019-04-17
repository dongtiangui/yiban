package com.yiban.yiban_application.huawei.image;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huawei.ais.sdk.AisAccess;
import com.huawei.ais.sdk.util.HttpClientUtils;
import com.yiban.yiban_application.huawei.ClientContextUtils;
import com.yiban.yiban_application.huawei.ResponseProcessUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;

import java.net.URL;

/**
 *  图片标签服务的使用示例类
 */
@Component
public class ImageTaggingUtil {

	private  JSONObject jsonObject;
	//
	// 图片标签服务的使用示例函数
	//
	public String imageTagging(String image_url) {
		//
		// 1. 在ClientContextUtils类中, 配置好访问图像标签服务的基本信息,
		// 然后，在此处生成对应的一个客户端连接对象
		// 		
		// 设置三个超时参数限制连接超时，分别如下
		int connectionTimeout = 5000; //连接目标url超时限制
		int connectionRequestTimeout = 1000;//连接池获取可用连接超时限制
		int socketTimeout = 5000;//获取服务器响应数据超时限制

		AisAccess service = new AisAccess(ClientContextUtils.getAuthInfo(), connectionTimeout,connectionRequestTimeout,socketTimeout);
		try {
			//
			// 2.构建访问图片标签服务需要的参数
			//
			String uri = "/v1.0/image/tagging";
			byte[] fileData = FileUtils.readFileToByteArray(loadImage(image_url));
			String fileBase64Str = Base64.encodeBase64String(fileData);
			JSONObject json = new JSONObject();
			json.put("image", fileBase64Str);
			json.put("threshold", 60);
			StringEntity stringEntity = new StringEntity(json.toJSONString(), "utf-8");
			// 3.传入图片标签服务对应的uri参数, 传入图片标签服务需要的参数，
			// 该参数主要通过JSON对象的方式传入, 使用POST方法调用服务
			HttpResponse response = service.post(uri, stringEntity);
			
			// 4.验证服务调用返回的状态是否成功，如果为200, 为成功, 否则失败。
			ResponseProcessUtils.processResponseStatus(response);
			
			// 5.处理服务返回的字符流，输出识别结果。
			jsonObject= JSON.parseObject(HttpClientUtils.convertStreamToString(response.getEntity().getContent()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.使用完毕，关闭服务的客户端连接
			service.close();
		}
		return JSON.toJSONString(jsonObject, SerializerFeature.PrettyFormat);
	}
	public static void main(String[] args) throws IOException {

		String imageTagging = new ImageTaggingUtil().imageTagging("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=22998d3b3bfae6cd13b4ad613fb20f9e/29381f30e924b8991cb3153460061d950a7bf62b.jpg");

//		String string = JSON.parseObject(imageTagging).getString("result");
//
//		String tags = JSON.parseObject(string).getString("tags");
//		JSONArray objects = JSON.parseArray(tags);
//		String toString = objects.get(0).toString();
//		String confidence = JSON.parseObject(toString).getString("confidence");
//		String tag = JSON.parseObject(toString).getString("tag");
//		System.out.println(confidence+"::::"+tag);

	}

	private static File loadImage(String image_url) throws Exception {
		URL url = new URL(image_url);
		//打开链接
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		//设置请求方式为"GET"
		conn.setRequestMethod("GET");
		//超时响应时间为5秒
		conn.setConnectTimeout(5 * 1000);
		//通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		//得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = readInputStream(inStream);
		//new一个文件对象用来保存图片，默认保存当前工程根目录
		File imageFile = new File("a.jpg");

		FileOutputStream outStream = new FileOutputStream(imageFile);
		//写入数据
		outStream.write(data);
		//关闭输出流
		outStream.close();

		return imageFile;
	}
	private static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		//使用一个输入流从buffer里把数据读取出来
		while( (len=inStream.read(buffer)) != -1 ){
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		//关闭输入流
		inStream.close();
		//把outStream里的数据写入内存
		return outStream.toByteArray();
	}
}
