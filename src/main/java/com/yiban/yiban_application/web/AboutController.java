package com.yiban.yiban_application.web;

import com.yiban.yiban_application.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {

    @Log("关于我们页面")
    @RequestMapping(value = "/adout.html")
    public String adout(){
        return "index_page/html/about";
    }
}
