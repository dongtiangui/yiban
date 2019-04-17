package com.yiban.yiban_application.system.controller;
import com.yiban.yiban_application.annotation.Log;
import com.yiban.yiban_application.javabean.Wall_user;
import com.yiban.yiban_application.system.dao.SystermInterface;
import com.yiban.yiban_application.system.dao.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
@SessionAttributes("admin_info")
public class HomeController {

    @Autowired
    private  UserInterface userInterface;
    @Autowired
    private SystermInterface systermInterface;

    @Log("管理员登陆界面")
    @RequestMapping(value = "/admin")
    public String home(HttpServletRequest request,HttpSession httpSession, Model model){
        String userId = request.getParameter("userId");
        Wall_user admin = userInterface.getUserById(userId);
        model.addAttribute("admin_info",admin);
        model.addAttribute("system_info",systermInterface.getSystemInfo());
        httpSession.setAttribute("admin_info",admin);
        return "index";
    }
    @Log("管理员登出")
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model,@RequestParam("admin_id") String admin_id) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1000*60*60);
        model.addAttribute("user_info",userInterface.getUserById(admin_id));
        return "lockscreen";
    }
}
