package com.yiban.yiban_application.system.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiban.yiban_application.annotation.Log;
import com.yiban.yiban_application.controller.BaseController;
import com.yiban.yiban_application.javabean.NiceDetail;
import com.yiban.yiban_application.javabean.Wall_assess;
import com.yiban.yiban_application.javabean.Wall_trends;
import com.yiban.yiban_application.javabean.Wall_user;
import com.yiban.yiban_application.system.service.RendsService;
import com.yiban.yiban_application.util.AppUtil;
import com.yiban.yiban_application.util.QueryRequest;
import com.yiban.yiban_application.util.ResponseResult;
import com.yiban.yiban_application.web.service.AssessInterface;
import com.yiban.yiban_application.web.service.NiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Controller
@RequestMapping(value = "/trends")
public class DynamicController extends BaseController {

    private ResponseResult<Wall_assess> responseResult;

    private final NiceDetailService niceDetailService;

    private final RendsService rendsService;

    private final AssessInterface assessInterface;

    @Autowired
    public DynamicController(NiceDetailService niceDetailService, RendsService rendsService, AssessInterface assessInterface) {
        this.niceDetailService = niceDetailService;
        this.rendsService = rendsService;
        this.assessInterface = assessInterface;
    }


    @Log("初始化主页")
    @RequestMapping(value = "/trendsAllOther")
    @ResponseBody
    public List<Wall_trends> allOther(@RequestParam("type") Integer type){
        List<Wall_trends> list = rendsService.getAllByType(type);
        commonImage(list);
        commonVideo(list);
        return list;
    }
    @Log("查看动态详情")
    @RequestMapping(value = "/list_card.html")
    public String list_card(@RequestParam("id") String id,@RequestParam("user_id") String user_id, Model model){
        model.addAttribute("trends_id",id);
        model.addAttribute("user_id",user_id);
        return "index_page/html/list_card";
    }


    @Log("查看评论")
    @RequestMapping(value = "/list_assess")
    @ResponseBody
    public Map<String,Object> list_assess(@RequestParam("trends_id") String trends_id){
        List<String> image_array_new = new LinkedList<>();
        List<String> video_array_new = new LinkedList<>();
        Map<String,Object> map = new ConcurrentHashMap<>();
        List<Wall_assess> wallAssesses = assessInterface.assessAllByTrends(trends_id);
        Wall_trends allById = rendsService.getAllById(trends_id);
        try {
            String[] image_array = allById.getTrends_img().split(",");
            if (image_array.length>0&&!image_array[0].equals("")){
                for (String s : image_array) {
                    image_array_new.add(AppUtil.IMAGESITEURL + s);
                }
            }
            String[] video_array = allById.getTrends_video().split(",");
            if (video_array.length>0&&!video_array[0].equals("")){
                for (String s : video_array) {
                    video_array_new.add(AppUtil.IMAGESITEURL + s);
                }
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());

        }
        map.put("img",image_array_new);
        map.put("video",video_array_new);
        map.put("trends",allById);
        map.put("assesses",wallAssesses);
        return map;
    }


    @Log("个人动态")
    @ResponseBody
    @RequestMapping("/AllByUser")
    public Map<String,Object> AllByUser(@RequestParam("user_id") String user_id){
        Map<String,Object> map = new ConcurrentHashMap<>();
        int trendsConutByUser = rendsService.trendsConutByUser(user_id);
        int conutByUser = assessInterface.assessConutByUser(user_id);
        map.put("conutAssess",conutByUser);
        map.put("trendsConutByUser",trendsConutByUser);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/recommend/max")
    public Map<String,Object> recommendmax(){
        Map<String,Object> map = new HashMap<>();
        try {
            Wall_trends byMax = rendsService.getTrendsByMax();
            if (byMax!=null){
                if (!byMax.getTrends_img().equals("")){
                    String[] split = byMax.getTrends_img().split(",");
                    byMax.setTrends_img(new String(new StringBuffer().append(AppUtil.IMAGESITEURL).append(split[0])));
                }
                map.put("status",200);
                map.put("trends",byMax);
            }
        }catch (Exception e){
            map.put("status",400);
            map.put("error","暂无推荐");
        }
        return map;
    }

    @Log("用户点赞")
    @RequestMapping("/updateById/{id}")
    @ResponseBody
    public Map<String,Object> updateById(@PathVariable("id") String id, HttpServletRequest request){
        NiceDetail niceDetail = new NiceDetail();
        niceDetail.setContentid(id);
        niceDetail.setUserid(Integer.parseInt(request.getParameter("user_id")));
        NiceDetail niceDetail1=niceDetailService.findNiceDetail(niceDetail);
        int result;
        if (niceDetail1!=null){
            //如果找到这条记录，删除该记录，同时文章的点赞数减一
            //删除记录
            niceDetailService.deleteNiceDetail(niceDetail1.getId());
            //根据点赞id找到文章
            Wall_trends allById = rendsService.getAllById(niceDetail.getContentid());
            //文章点赞数减一
            allById.setTrends_praise_number(allById.getTrends_praise_number()-1);
            result=allById.getTrends_praise_number();
            //更新文章点赞数
            rendsService.updateRendsById(allById);
        }else{
            //如果没有找到这条记录，则添加这条记录，同时文章数加一；
            //添加记录
            niceDetailService.insertNiceDetail(niceDetail);
            Wall_trends allById = rendsService.getAllById(niceDetail.getContentid());
            //文章点赞数加一
            allById.setTrends_praise_number(allById.getTrends_praise_number()+1);
            result=allById.getTrends_praise_number();
            //更新文章点赞数
            responseResult = rendsService.updateRendsById(allById);
        }
        Map<String,Object> map = new ConcurrentHashMap<>();
        if (responseResult!=null){
            map.put("status",responseResult.getStatus());
            map.put("message",responseResult.getMessage());
            map.put("num",result);
            return map;
        }
        map.put("error","系统错误");
        return map;
    }

    @Log("查看动态")
    @RequestMapping("/trends_table")
    public String page(@SessionAttribute(value = "admin_info") Wall_user admin , Model model){

        model.addAttribute("username",admin);
        return "admin_pages/tables/trendsdata";
    }
    @ResponseBody
    @RequestMapping(value = "/ajaxTrends/table")
    public Map<String, Object> ajaxtrends(QueryRequest request){
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<Wall_trends> list = rendsService.getAll();
        StringBuffer image_array_new = new StringBuffer();
        StringBuffer video_array_new = new StringBuffer();
        for (Wall_trends wallTrends:list){
            String[] image_array = wallTrends.getTrends_img().split(",");
            if (image_array.length>0&&!image_array[0].equals("")){
                for (String s : image_array) {
                    image_array_new.append(AppUtil.IMAGESITEURL).append(s).append(",");
                }
            }
            String[] video_array = wallTrends.getTrends_video().split(",");
            if (video_array.length>0&&!video_array[0].equals("")){
                for (String s : video_array) {
                    video_array_new.append(AppUtil.IMAGESITEURL).append(s).append(",");
                }
            }
            wallTrends.setTrends_img(new String(image_array_new));
            wallTrends.setTrends_video(new String(video_array_new));
        }
        PageInfo<Wall_trends> pageInfo = new PageInfo<Wall_trends>(list);
        return getDataTable(pageInfo);
    }


    @Log("批量删除动态")
    @RequestMapping(value = "/deleteAll/id")
    @ResponseBody
    public Map<String,Object> deteleById(@RequestParam("ids") String id){
        ResponseResult result = new ResponseResult();
        String[] ids = id.split(",");
        for (String s:ids) {
             result= rendsService.deleteRendsById(s);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("result",result);
        return map;
    }

    @Log("更新权限")
    @RequestMapping(value = "/updateStatus/id")
    @ResponseBody
    public Map<String,Object> updateStatus(@RequestParam("id") String id
            ,@RequestParam("status") Integer status
            ,@RequestParam("admin") String admin){
        ResponseResult result;
        Map<String,Object> map = new HashMap<>();
        if (status==0){
            result=rendsService.updateSataus(id,1,admin);
        }
        else {
            result=rendsService.updateSataus(id,0,admin);
        }
        map.put("result",result);
        return map;
    }
    @Log("删除一条动态")
    @ResponseBody
    @RequestMapping(value = "/deleteTrends/{id}")
    public Map<String,Object> deleteTrends(@PathVariable("id") String id){
        Map<String,Object> map = new HashMap<>();
        ResponseResult result = rendsService.deleteRendsById(id);
        map.put("result",result);
        return map;
    }
    private void commonImage(List<Wall_trends> list){
        for (Wall_trends wallTrends:list) {
            if (wallTrends.getTrends_img().equals("")){
               break;
            }
            String[] image_array = wallTrends.getTrends_img().split(",");
            if (!wallTrends.getTrends_img().equals("")){
                wallTrends.setTrends_img(AppUtil.IMAGESITEURL+image_array[0]);
            }
        }
    }
    private void commonVideo(List<Wall_trends> list){
        for (Wall_trends wallTrends:list) {
            if (!wallTrends.getTrends_video().equals("")){
               return;
            }
            String[] video_array = wallTrends.getTrends_video().split(",");
            if (!wallTrends.getTrends_video().equals("")){
                wallTrends.setTrends_video(AppUtil.IMAGESITEURL+video_array[0]);
            }
        }
    }
}


