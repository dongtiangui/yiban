package com.yiban.yiban_application.system.controller;

import com.yiban.yiban_application.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController {

    @Log("页面未找到")
    @RequestMapping(value = "/page404")
    public String page404(){

        return "404";
    }
    @Log("系统异常")
    @RequestMapping(value = "/page500")
    public String page500(){
        return "500";
    }
}
