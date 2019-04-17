package com.yiban.yiban_application.system.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiban.yiban_application.controller.BaseController;
import com.yiban.yiban_application.javabean.Wall_image;
import com.yiban.yiban_application.javabean.Wall_video;
import com.yiban.yiban_application.system.dao.ResourceInterface;
import com.yiban.yiban_application.util.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping(value = "/resource")
public class ResourcesController extends BaseController {

    private final ResourceInterface resourceInterface;

    @Autowired
    public ResourcesController(ResourceInterface resourceInterface) {
        this.resourceInterface = resourceInterface;
    }

    @RequestMapping(value = "/imagePage")
    public String imagePage(){

        return "admin_pages/tables/imagedata";
    }
    @RequestMapping(value = "/videoPage")
    public String videoPage(){

        return "admin_pages/tables/videodata";
    }

    @RequestMapping(value = "/ajaximgdata")
    @ResponseBody
    public Map<String, Object> selectImage(QueryRequest request){
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<Wall_image> images = resourceInterface.imageAll();
        PageInfo<Wall_image> pageInfo = new PageInfo<Wall_image>(images);
        return getDataTable(pageInfo);
    }

    @RequestMapping(value = "/ajaxvideodata")
    @ResponseBody
    public Map<String, Object> selectVideo(QueryRequest request){
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<Wall_video> videos = resourceInterface.videoAllByList();
        PageInfo<Wall_video> pageInfo = new PageInfo<Wall_video>(videos);
        return getDataTable(pageInfo);
    }

    @RequestMapping(value = "/deleteImageById/{id}/{name}")
    @ResponseBody
    public Map<String, Object> deleteImageById(@PathVariable("id") String id,@PathVariable("name") String name){
        String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath()+"static/upload/";
        Map<String, Object> map = new HashMap<>();
        try {
             resourceInterface.deleteImageById(id,name);
             delFile(path,name);
            map.put("status",200);
            map.put("message","删除成功");
            return map;
        }catch (Exception e){
            map.put("status",400);
            map.put("message","删除失败");
        }
        return map;
    }
    @RequestMapping(value = "/deleteAllImage/id")
    @ResponseBody
    public Map<String, Object> deleteAllImage(@RequestParam("ids") String id,@RequestParam("names") String names){
        String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath()+"static/upload/";
        Map<String, Object> map = new HashMap<>();
        String[] ids = id.split(",");
        String[] name = names.split(",");
        try {
            for(int i=0;i<ids.length;i++){
                resourceInterface.deleteImageById(ids[i],name[i]);
                delFile(path,name[i]);
            }
            map.put("status",200);
            map.put("message","删除成功");
            return map;
        }catch (Exception e){
            map.put("status",400);
            map.put("message","删除失败");
        }
        return map;
    }

    @RequestMapping(value = "/deleteVideoById/id/{id}/{name}")
    @ResponseBody
    public Map<String, Object> deleteVideoById(@PathVariable("id") String id,@PathVariable("name") String name){
        String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath()+"static/upload/";
        Map<String, Object> map = new HashMap<>();
        try {
            resourceInterface.deleteVideoById(id,name);
            delFile(path,name);
            map.put("status",200);
            map.put("message","删除成功");
            return map;
        }catch (Exception e){
            map.put("status",400);
            map.put("message","删除失败");
        }
        return map;
    }


    @RequestMapping(value = "/deleteVideoByIds/id")
    @ResponseBody
    public Map<String, Object> deleteVideoByIds(@RequestParam("ids") String id,@RequestParam("names") String name){
        String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath()+"static/upload/";
        Map<String, Object> map = new HashMap<>();
        String[] ids = id.split(",");
        String[] names = name.split(",");
        try {
            for (int i=0;i<ids.length;i++) {
                resourceInterface.deleteVideoById(ids[i],names[i]);
                delFile(path,names[i]);
            }
            map.put("status",200);
            map.put("message","删除成功");
            return map;
        }catch (Exception e){
            map.put("status",400);
            map.put("message","删除失败");
        }
        return map;
    }

//    删除文件
    private void delFile(String path,String filename){
        File file=new File(path+"/"+filename);
        if(file.exists()&&file.isFile())
            file.delete();
    }
}
