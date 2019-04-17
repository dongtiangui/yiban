package com.yiban.yiban_application.web;
import com.yiban.yiban_application.annotation.Log;
import com.yiban.yiban_application.javabean.Wall_image;
import com.yiban.yiban_application.web.service.ImageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
@Controller
public class ImageController {

    private final ImageInterface imageInterface;

    @Autowired
    public ImageController(ImageInterface imageInterface) {
        this.imageInterface = imageInterface;
    }
    @Log(value = "查看图片界面")
    @RequestMapping(value = "/image.html")
    public String image(@RequestParam("id") String user_id,@RequestParam("session") String session, Model model){
        List<Wall_image> result = imageInterface.imageAll();
        for (Wall_image wallImage:result) {
            String[] split = wallImage.getImage_format().split(",");
            List<String> list = new ArrayList<>(Arrays.asList(split));
            wallImage.setList(list);
        }
        model.addAttribute("image",result);
        model.addAttribute("user_id",user_id);
        return "index_page/html/image";
    }
}
