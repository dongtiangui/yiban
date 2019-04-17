package com.yiban.yiban_application.common;
import com.yiban.yiban_application.system.dao.AdminInterface;
import com.yiban.yiban_application.util.Sha256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
@Component
public class YbHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private AdminInterface adminInterface;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getParameter("sessionId");
        String username = request.getParameter("username");
        String id = request.getSession().getId();
        String pass = adminInterface.getPass(URLDecoder.decode(username, "UTF-8"));
        if (sessionId.equals(Sha256.getSHA256(id) + pass)){
            return true;
        }
        response.sendRedirect("505.html");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

}
