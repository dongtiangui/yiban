package com.yiban.yiban_application.system.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiban.yiban_application.controller.BaseController;
import com.yiban.yiban_application.javabean.Print;
import com.yiban.yiban_application.javabean.PrintLocal;
import com.yiban.yiban_application.system.dao.PrintInterface;
import com.yiban.yiban_application.system.dao.PrintLocalInterface;
import com.yiban.yiban_application.util.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(value = "/print")
public class PrintDataController extends BaseController {

    @Autowired
    private PrintLocalInterface printLocalInterface;

    @Autowired
    private PrintInterface printInterface;
    /**
     * 打印订单表格
     * @return
     */
    @RequestMapping(value = "/printdata")
    public String print_table(){
        return "admin_pages/tables/printdata";
    }
    /**
     * 打印室表格
      * @return
     */
    @RequestMapping(value = "/printroomdata")
    public String print_roomdata(){

        return "admin_pages/tables/printroomdata";
    }

    @RequestMapping(value = "/ajaxPrintroomlist")
    @ResponseBody
    public Map<String, Object> selectPrintRoom(QueryRequest request){
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<PrintLocal> printLocals = printLocalInterface.getAll();
        PageInfo<PrintLocal> pageInfo = new PageInfo<PrintLocal>(printLocals);
        return getDataTable(pageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxPrint")
    public Map<String, Object> selectPrint(QueryRequest request) {
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<Print> all = printInterface.selectPrint();
        for (Print print:all){
            StringBuffer image_array_new = new StringBuffer();
            String[] image_array = print.getPrint_file().split(",");
            if (image_array.length>0&&!image_array[0].equals("")){
                for (String s : image_array) {
                    image_array_new.append(s).append(",");
                }
            }
            print.setPrint_file(new String(image_array_new));
        }
        PageInfo<Print> pageInfo = new PageInfo<Print>(all);
        return getDataTable(pageInfo);
    }

    @RequestMapping(value = "/updateById")
    @ResponseBody
    public Map<String, Object> updateById(@RequestParam("id") Integer id,@RequestParam("status") String status){
        Map<String, Object> map = new HashMap<>();
        if (status.equals("1")){
            boolean printStatus = printInterface.UpdatePrintStatus("0",id);
            if (printStatus){
                map.put("status",200);
                map.put("message","处理成功");
                return map;
            }
        }
        else {
            boolean printStatus = printInterface.UpdatePrintStatus("1",id);
            if (printStatus){
                map.put("status",200);
                map.put("message","处理成功");
                return map;

            }
        }
        map.put("status",400);
        map.put("message","订单处理失败");
        return map;
    }

    @RequestMapping(value = "/deleteById/{id}")
    @ResponseBody
    public Map<String, Object> deleteById(@PathVariable("id") Integer id){
        Map<String, Object> map = new HashMap<>();
        boolean printStatus = printInterface.deleteById(id);
        if (printStatus){
            map.put("status",200);
            map.put("message","处理成功");
            return map;
        }
        map.put("status",400);
        map.put("message","订单处理失败");
        return map;
    }

    @RequestMapping(value = "/deleteAll/id")
    @ResponseBody
    public Map<String, Object> deleteByAll(@RequestParam("ids") String id){
        Map<String, Object> map = new HashMap<>();
        String[] ids = id.split(",");
        try {
            for (String s:ids) {
                printInterface.deleteById(Integer.parseInt(s));
            }
            map.put("status",200);
            map.put("message","处理成功");
            return map;
        }catch (Exception e){
            map.put("status",400);
            map.put("message","订单处理失败");
        }
        return map;
    }
    @RequestMapping(value = "/addPrintRoom")
    @ResponseBody
    public Map<String,Object> addPrintRoom(@RequestParam("t_local_name") String t_local_name,
                                           @RequestParam("t_local_person") String t_local_person,
                                           @RequestParam("t_local_number") String t_local_number,
                                           @RequestParam("t_local_host") String t_local_host
                                           ){
        Map<String,Object> map =  new HashMap<>();
        PrintLocal printLocal = new PrintLocal();
        printLocal.setT_local_name(t_local_name);
        printLocal.setT_local_person(t_local_person);
        printLocal.setT_local_number(t_local_number);
        printLocal.setT_local_host(t_local_host);
        try {
            printLocalInterface.insert(printLocal);
        }catch (Exception e){
            map.put("status",400);
            map.put("message","系统错误");
            return map;
        }
        map.put("status",200);
        map.put("message","添加成功");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/delById/{id}")
    public  Map<String,Object> del(@PathVariable("id") Integer id){
        Map<String,Object> map = new HashMap<>();
        try {
            printLocalInterface.delById(id);
            map.put("status",200);
            map.put("message","删除成功");
            return map;
        }catch (Exception e){
            map.put("status",400);
            map.put("message","删除失败");
        }
        return map;
    }

}
