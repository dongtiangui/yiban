package com.yiban.yiban_application.web;

import com.yiban.yiban_application.javabean.Print;
import com.yiban.yiban_application.javabean.PrintLocal;
import com.yiban.yiban_application.system.dao.PrintInterface;
import com.yiban.yiban_application.system.dao.PrintLocalInterface;
import com.yiban.yiban_application.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class PrintController {

    @Autowired
    private PrintLocalInterface printLocalInterface;

    @Autowired
    private PrintInterface printInterface;

    @RequestMapping(value = "/print.html", params = {"id", "session"})
    public String printPage(@RequestParam("id") String id, @RequestParam("session") String session, Model model) {

        List<PrintLocal> all = printLocalInterface.getAll();

        model.addAttribute("local",all);

        model.addAttribute("user",id);

        return "index_page/html/print";
    }
    @RequestMapping(value = "/print/file/up")
    @ResponseBody
    public Map<String, Object> printFileUp(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Map<String, Object> json = new HashMap<>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile;
        Map map = multipartRequest.getFileMap();
        for (Object obj : map.keySet()) {
            multipartFile = (MultipartFile) map.get(obj);
            if (multipartFile.getSize() > 52428800L) {
                json.put("message", "文件过大");
                json.put("status", false);
                return json;
            }
            String filename = multipartFile.getOriginalFilename();
            assert filename != null;
            String newFileName = UUID.randomUUID().toString()
                    .replaceAll("-", "")
                    .concat(filename.substring(filename.lastIndexOf(".")));
            File file;
            String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath() + "static/";
            File upload = new File(path, "print");
            if (!upload.exists()) upload.mkdirs();
            file = new File(upload.getPath() + "/" + newFileName);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                json.put("message", "系统错误");
                json.put("status", false);
                return json;
            }
            json.put("fileMd5", newFileName);
            json.put("message", "文件上传成功");
            json.put("status", true);
            json.put("filePath", newFileName);
            return json;
        }
        return json;
    }

    @RequestMapping(value = "/print/form/{id}")
    @ResponseBody
    public Map<String,Object> form(@PathVariable("id") String id,HttpServletRequest request){
        StringBuffer img = new StringBuffer();
        String number = request.getParameter("number");
        String print_local = request.getParameter("print_local");
        String res = request.getParameter("res");
        String format = request.getParameter("format");
        String color = request.getParameter("color");
        String localhost = request.getParameter("localhost");
        Map<String,Object> map = new ConcurrentHashMap<>();
        if (!res.equals("")){
            String[] res_list = res.split(",");
            for (String item:res_list) {
                img.append(item).append(",");
            }
        }
        Print print = new Print();
        PrintLocal printLocal = new PrintLocal();
        printLocal.setT_local_id(Integer.parseInt(print_local));
        print.setPrintLocal(printLocal);
        print.setPrint_number(number);
        print.setPrint_file(new String(img));
        print.setPrint_color(color);
        print.setPrint_time(new Date());
        print.setPrint_fomat(format);
        print.setPrint_status("0");
        print.setPrint_name(id);
        print.setPrint_local(localhost);
        try {
            printInterface.insertPrint(print);
            map.put("message","提交成功等待管理员处理");
            map.put("status",200);
        }catch (Exception e){
            map.put("error","系统正忙");
        }
        return map;
    }


}
