package com.yiban.yiban_application.common;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yiban.yiban_application.annotation.Log;
import com.yiban.yiban_application.huawei.image.ImageTaggingUtil;
import com.yiban.yiban_application.javabean.Wall_image;
import com.yiban.yiban_application.javabean.Wall_video;
import com.yiban.yiban_application.system.dao.ResourceInterface;
import com.yiban.yiban_application.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
@Controller
@RequestMapping("/file")
@CrossOrigin
public class FileUpLoad {

    @Autowired
    private ResourceInterface resourceInterface;

    @Autowired
    private ImageTaggingUtil imageTaggingUtil;

    @Log("用户上传图片/视频资源")
    @RequestMapping("/image")
    @ResponseBody
    public Map<String,Object> addImage(HttpServletRequest request) throws Exception {
        StringBuffer image_tags = new StringBuffer();
        String username = request.getParameter("username");
        request.setCharacterEncoding("UTF-8");
        Map<String, Object> json = new HashMap<>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        /** 页面控件的文件流* */
        MultipartFile multipartFile;
        Map map =multipartRequest.getFileMap();
        for (Object obj : map.keySet()) {
            multipartFile = (MultipartFile) map.get(obj);
            if (multipartFile.getSize() > 52428800L) {
                json.put("message", "文件过大!最大支持50MB");
                json.put("status", false);
                return json;
            }
            String filename = multipartFile.getOriginalFilename();
            assert filename != null;
            String newFileName = UUID.randomUUID().toString()
                    .replaceAll("-", "")
                    .concat(filename.substring(filename.lastIndexOf(".")));
            File file;
            String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath()+"static/";
            File upload = new File(path, "upload");
            if (!upload.exists()) upload.mkdirs();
            file = new File(upload.getPath() + "/"+ newFileName);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                json.put("message", "系统错误");
                json.put("status", false);
                return json;
            }
            String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            if (suffix.toLowerCase().equals("mov")
                    ||suffix.toLowerCase().equals("mp4")
                    ||suffix.toLowerCase().equals("rmvb")
                    ||suffix.toLowerCase().equals("flv")
                    ||suffix.toLowerCase().equals("3gp")){
                String url = AppUtil.IMAGESITEURL +newFileName;
                Wall_video wall_video = new Wall_video();
                wall_video.setVideo_id(String.valueOf(new Date().getTime()));
                wall_video.setVideo_size(getNetFileSizeDescription(file.length()));
                wall_video.setVideo_path(path);
                wall_video.setVideo_url(url);
                wall_video.setVideo_user(username);
                wall_video.setVideo_format(suffix);
                wall_video.setVideo_status(1);
                wall_video.setVideo_name(newFileName);
                resourceInterface.insertWall_video(wall_video);
                json.put("fileMd5", newFileName);
                json.put("message", "文件上传成功");
                json.put("status", true);
                json.put("filePath", newFileName);
                return json;
            }
            String url = AppUtil.IMAGESITEURL +newFileName;
            Runnable r = ()->{
                String tagging = imageTaggingUtil.imageTagging(url);
                String string = JSON.parseObject(tagging).getString("result");
                String tags = JSON.parseObject(string).getString("tags");
                JSONArray objects = JSON.parseArray(tags);
                for (Object s: objects) {
                    JSONObject object = JSON.parseObject(s.toString());
                    String confidence = object.getString("confidence")+"%";
                    String tag = object.getString("tag");
                    image_tags.append(confidence).append(":").append(tag).append(",");
                }
            };
            synchronized (this){
                r.run();
            }
            Wall_image wall_image = new Wall_image();
            wall_image.setImage_id(String.valueOf(new Date().getTime()));
            wall_image.setImage_size(getNetFileSizeDescription(file.length()));
            wall_image.setImage_path(path);
            wall_image.setImage_url(url);
            wall_image.setImage_user(username);
            wall_image.setImage_type(suffix);
            wall_image.setImage_status(1);
            wall_image.setImage_format(new String(image_tags));
            wall_image.setImage_name(newFileName);
            resourceInterface.insertWall_image(wall_image);
            json.put("fileMd5", newFileName);
            json.put("message", "文件上传成功");
            json.put("status", true);
            json.put("filePath", newFileName);
        }
        /** 获取文件的后缀* */
        return json;
    }

//    文件大小格式转换
    private static String getNetFileSizeDescription(long size) {
        StringBuilder bytes = new StringBuilder();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        } else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        } else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        } else {
            if (size <= 0) {
                bytes.append("0B");
            } else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }

    /**
     * //        String username = request.getParameter("username");
     * //
     * //        UploadImageResModel res = new UploadImageResModel();
     * //        if (multipartFile.isEmpty()) {
     * //            res.setUploaded(1);
     * //            res.setMessage("上传失败！未选中文件");
     * //            return res;
     * //        }
     * //        if (multipartFile.getSize()>52428800L){
     * //            res.setUploaded(1);
     * //            res.setMessage("上传失败！大小不可超过50MB");
     * //            return res;
     * //        }
     * //        String filename = multipartFile.getOriginalFilename();
     * //        assert filename != null;
     * //        String newFileName = UUID.randomUUID().toString()
     * //                .replaceAll("-", "")
     * //                .concat(filename.substring(filename.lastIndexOf(".")));
     * //        File file;
     * //        try {
     * //            String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath()+"static/";
     * //            File upload = new File(path, "upload");
     * //            if (!upload.exists()) upload.mkdirs();
     * //            file = new File(upload.getPath() + "/"+ newFileName);
     * //            multipartFile.transferTo(file);
     * //            res.setUploaded(1);
     * //            res.setFileName(newFileName);
     * //            res.setUrl("http://localhost:8083/static/upload/"+newFileName);
     * //            res.setMessage("上传成功");
     * //            String url = "http://localhost:8083/static/upload/"+newFileName;
     * //            Wall_image wall_image = new Wall_image();
     * //            wall_image.setImage_id(String.valueOf(new Date().getTime()));
     * //            wall_image.setImage_size(String.valueOf(file.length()));
     * //            wall_image.setImage_path(path);
     * //            wall_image.setImage_url(url);
     * //            wall_image.setImage_user(username);
     * //            wall_image.setImage_type(file.getName().substring(file.getName().lastIndexOf(".") + 1));
     * //            wall_image.setStatus(1);
     * //            resourceInterface.insertWall_image(wall_image);
     * //            return res;
     * //        } catch (Exception e) {
     * //            return res;
     * //        }
     */

}
