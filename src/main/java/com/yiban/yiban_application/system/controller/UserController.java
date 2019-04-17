package com.yiban.yiban_application.system.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiban.yiban_application.annotation.Log;
import com.yiban.yiban_application.controller.BaseController;
import com.yiban.yiban_application.javabean.Wall_user;
import com.yiban.yiban_application.system.dao.UserInterface;
import com.yiban.yiban_application.system.service.RendsService;
import com.yiban.yiban_application.util.QueryRequest;
import com.yiban.yiban_application.web.service.AssessInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.*;
@Controller
public class UserController extends BaseController {

    private final UserInterface userInterface;

    @Autowired
    private  RendsService rendsService;

    @Autowired
    private  AssessInterface assessInterface;

    @Autowired
    public UserController(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
    @Log("查看用户")
    @RequestMapping("/userAdmin")
    public String com(@SessionAttribute("admin_info") Wall_user admin){
        int trendsConutByUser = rendsService.trendsConutByUser(admin.getId());
        int conutByUser = assessInterface.assessConutByUser(admin.getId());
        userInterface.updateUserTA(trendsConutByUser,conutByUser,Integer.parseInt(admin.getId()));
        return "admin_pages/tables/userdata";
    }
    @RequestMapping(value = "/ajaxUser")
    @ResponseBody
    public Map<String, Object> ajax(QueryRequest request){
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Wall_user> list = this.userInterface.getAllUser();
        PageInfo<Wall_user> pageInfo = new PageInfo<Wall_user>(list);
        return getDataTable(pageInfo);
    }
    @RequestMapping(value = "/ajaxGetUser/{name}")
    @ResponseBody
    public Map<String,Object> ajaxGetUserByName(@PathVariable("name") String name){
        Map<String,Object> map = new HashMap<>();
        map.put("user_info",userInterface.getUser(name));
        return map;
    }
}
