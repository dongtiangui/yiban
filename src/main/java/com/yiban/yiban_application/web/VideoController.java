package com.yiban.yiban_application.web;

import com.yiban.yiban_application.annotation.Log;
import com.yiban.yiban_application.javabean.Wall_video;
import com.yiban.yiban_application.util.ResponseResult;
import com.yiban.yiban_application.web.service.VideoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class VideoController {
    private final VideoInterface videoInterface;
    @Autowired
    public VideoController(VideoInterface videoInterface) {
        this.videoInterface = videoInterface;
    }

    @Log(value = "用户查看小视频")
    @RequestMapping(value = "/video.html")
    public String getList(@RequestParam("id") String user_id,@RequestParam("session") String session,Model model){
        @SuppressWarnings("unchecked")
        ResponseResult<Wall_video> result = videoInterface.videoAllByList();
        if (result.getStatus()!=null){
            List<Wall_video> videos = result.getResult();
            model.addAttribute("list",videos);
            model.addAttribute("user_id",user_id);
            return "index_page/html/video";
        }
        return "redirect:/video/null/private";
    }
    @RequestMapping(value = "/video/null/private")
    @ResponseBody
    public String resultNull(){
        return "服务器当前视频为空！如需上传请至个人页面上传";
    }
}
