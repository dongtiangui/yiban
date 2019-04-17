package com.yiban.yiban_application.system.controller;
import com.yiban.yiban_application.annotation.Log;
import com.yiban.yiban_application.system.dao.AdminInterface;
import com.yiban.yiban_application.util.Sha256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@Controller
public class loginController {

    @Autowired
    private AdminInterface adminInterface;

    @Log("管理员登陆")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestParam("username") String username
            , @RequestParam("password") String password, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String pass = adminInterface.getPass(username);
        String password_sha = Sha256.getSHA256(password);
        String sha256 = Sha256.getSHA256(request.getSession().getId());
        StringBuilder buffer = new StringBuilder(sha256);
        buffer.append(pass);
        if (pass.equals(password_sha)){
            map.put("status",1);
            map.put("session",buffer);
            return map;
        }
        map.put("status",2);
        map.put("meg","密码错误请重新登陆");
        return map;
    }
    @Log("管理修改密码")
    @CrossOrigin
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> register(@RequestParam("username") String username,@RequestParam("oldpassword") String oldpassword
            , @RequestParam("newpassword") String newpassword){
        Map<String,Object> map = new HashMap<>();
        String database = adminInterface.getPass(username);
        String sha_old = Sha256.getSHA256(oldpassword);
        adminInterface.updateAdmin(Sha256.getSHA256(newpassword),username);
        if (database.equals(sha_old)){
            adminInterface.updateAdmin(Sha256.getSHA256(newpassword),username);
            map.put("status",1);
            map.put("success","修改成功");
            return map;
        }
        map.put("status",2);
        map.put("meg","旧密码错误");
        return map;
    }

}
