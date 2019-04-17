package com.yiban.yiban_application.system.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiban.yiban_application.controller.BaseController;
import com.yiban.yiban_application.javabean.Wall_assess;
import com.yiban.yiban_application.system.dao.AssessMapper;
import com.yiban.yiban_application.util.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(value = "/assess")
public class AssessDataController extends BaseController {
    private final AssessMapper assessMapper;
    @Autowired
    public AssessDataController(AssessMapper assessMapper) {
        this.assessMapper = assessMapper;
    }

    @RequestMapping(value = "/assessdatapage")
    public String assessdatapage(){

        return "admin_pages/tables/assessdata";
    }
    @RequestMapping("/ajaxassessdata")
    @ResponseBody
    public Map<String, Object> assessList(QueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Wall_assess> list = this.assessMapper.assessAll();
        PageInfo<Wall_assess> pageInfo = new PageInfo<Wall_assess>(list);
        return getDataTable(pageInfo);
    }
    @ResponseBody
    @RequestMapping(value = "/deleteassessById/{id}")
    public Map<String,Object> deletassess(@PathVariable("id") String id){
        Map<String,Object> map = new HashMap<>();
        boolean byId = assessMapper.delAssessById(Integer.parseInt(id));
        if (byId){
            map.put("status",200);
            map.put("message","删除成功");
            return map;
        }
        map.put("status",400);
        map.put("message","系统错误！无法删除");
        return map;
    }
    @RequestMapping(value = "/deleteAll/id")
    @ResponseBody
    public Map<String, Object> deleteByAll(@RequestParam("ids") String id){
        Map<String, Object> map = new HashMap<>();
        String[] ids = id.split(",");
        try {
            for (String s:ids) {
                assessMapper.delAssessById(Integer.parseInt(s));
            }
            map.put("status",200);
            map.put("message","评论删除成功");
            return map;
        }catch (Exception e){
            map.put("status",400);
            map.put("message","评论删除失败");
        }
        return map;
    }
}
