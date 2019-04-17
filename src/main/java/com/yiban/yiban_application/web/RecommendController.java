package com.yiban.yiban_application.web;

import com.yiban.yiban_application.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecommendController {

    @Log(value = "用户查看推荐")
    @RequestMapping(value = "recommend.html")
    public String Recommend(){
        return "index_page/html/recommend";
    }


}
