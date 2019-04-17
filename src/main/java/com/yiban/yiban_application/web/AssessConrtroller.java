package com.yiban.yiban_application.web;

import com.yiban.yiban_application.annotation.Log;
import com.yiban.yiban_application.javabean.Wall_assess;
import com.yiban.yiban_application.javabean.Wall_trends;
import com.yiban.yiban_application.util.ResponseResult;
import com.yiban.yiban_application.web.service.AssessInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(value = "/assess")
public class AssessConrtroller {

    private final AssessInterface assessInterface;

    @Autowired
    public AssessConrtroller(AssessInterface assessInterface) {
        this.assessInterface = assessInterface;
    }

    @Log(value = "用户插入评论")
    @RequestMapping(value = "/insert")
    public Map<String,Object> userAssess(@RequestParam("info") String info,
                                         @RequestParam("user_id") String user_id,
                                         @RequestParam("trends_id") String trends_id){
        Map<String,Object> map = new ConcurrentHashMap<>();
        Wall_assess wallAssess = new Wall_assess();
        Wall_trends wallTrends = new Wall_trends();
        wallTrends.setTrends_id(trends_id);
        wallAssess.setAssess_info(info);
        wallAssess.setAssess_user(user_id);
        wallAssess.setAssess_number(0);
        wallAssess.setAssess_status("1");
        wallAssess.setWall_trends(wallTrends);
        ResponseResult<?> result = assessInterface.insertWallAssess(wallAssess);
        map.put("result",result.getMessage());
        return map;
    }
}
