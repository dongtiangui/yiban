package com.yiban.yiban_application.web;
import com.yiban.yiban_application.annotation.Log;
import com.yiban.yiban_application.javabean.Wall_trends;
import com.yiban.yiban_application.javabean.Wall_user;
import com.yiban.yiban_application.system.dao.UserInterface;
import com.yiban.yiban_application.system.service.RendsService;
import com.yiban.yiban_application.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/index")
public class MeController {

    private final RendsService rendsService;

    private final UserInterface userInterface;

    @Autowired
    public MeController(UserInterface userInterface, RendsService rendsService) {
        this.userInterface = userInterface;
        this.rendsService = rendsService;
    }
    @Log(value = "用户查看自己主页")
    @RequestMapping(value = "/user.html",params = {"id","session"})
    public ModelAndView userPage(@RequestParam("id") String id){
        Wall_user user = userInterface.getUserById(id);
        ModelAndView mv = new ModelAndView();
        List<Wall_trends> trends = rendsService.getAllByUser(id);
        if (trends!=null){
            for (Wall_trends wallTrends:trends) {
                if (wallTrends.getTrends_img().equals("")){
                    break;
                }
                String[] split = wallTrends.getTrends_img().split(",");
                List<String> list = new ArrayList<>();
                for (String s: split) {
                    list.add(AppUtil.IMAGESITEURL+s);
                }
                wallTrends.setImage_info(list);
            }
            for (Wall_trends wallTrends:trends) {
                if (wallTrends.getTrends_video().equals("")){
                    break;
                }
                String[] split = wallTrends.getTrends_video().split(",");
                List<String> list = new ArrayList<>();
                for (String s: split) {
                    list.add(AppUtil.IMAGESITEURL+s);
                }
                wallTrends.setVideo_info(list);
            }
        }
        mv.addObject("user_info",user);
        mv.addObject("trends_info",trends);
        mv.setViewName("index_page/html/user_profile");
        return mv;
    }
    @Log(value = "返回登陆界面")
    @RequestMapping("/login.html")
    public ModelAndView loginPage(@RequestParam("id") String id){
        Wall_user wall_user = new Wall_user();
        Wall_user userById = userInterface.getUserById(id);
        if (userById!=null){
            wall_user = userById;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("user_info",wall_user);
        mv.setViewName("lockscreen");
        return mv;
    }
}
