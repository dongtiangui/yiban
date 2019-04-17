package com.yiban.yiban_application.system.controller;

import com.yiban.yiban_application.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class DataBaseController {
    @Log("数据库备份")
    @RequestMapping(value = "/databaseback")
    @ResponseBody
    public Map<String,Object> databaseback(){
        Map<String,Object> map = new HashMap<>();
        String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath()+"static/";
        File upload = new File(path, "back/");
        String uploadPath = upload.getPath();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        String dateNowStr = sdf.format(new Date());
        try {
            boolean databaseTool = exportDatabaseTool(uploadPath,dateNowStr+"wall_admin.sql");
            if (databaseTool){
                map.put("message","备份成功");
                map.put("filename",dateNowStr+"wall_admin.sql");
                return map;
            }
            map.put("message","系统错误");
        } catch (InterruptedException e) {
            e.printStackTrace();
            map.put("message","系统错误");
        }
        return map;
    }

    @RequestMapping(value = "/databaseback/download/{file}")
    public void downloadFile(@PathVariable("file") String fileName, HttpServletResponse response) {
        if (fileName != null) {
            String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath() + "static/";
            File upload = new File(path, "back");
            //设置文件路径
            String realPath = upload.getPath() + "/";
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/octet-stream");//
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }





//    数据备份
    private static boolean exportDatabaseTool(String savePath,String fileName) throws InterruptedException {
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if(!savePath.endsWith(File.separator)){
            savePath = savePath + File.separator;
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), StandardCharsets.UTF_8));
            Process process = Runtime.getRuntime().exec(" mysqldump -h" + "localhost" + " -u" + "root" + " -p" + "MyNewPass4!" + " --set-charset=UTF8 " + "wall_admin");
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                printWriter.println(line);
            }
            printWriter.flush();
            if(process.waitFor() == 0){//0 表示线程正常终止。
                return true;
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
