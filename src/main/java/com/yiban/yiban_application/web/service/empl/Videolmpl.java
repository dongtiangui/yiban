package com.yiban.yiban_application.web.service.empl;

import com.yiban.yiban_application.javabean.Wall_video;
import com.yiban.yiban_application.system.dao.ResourceInterface;
import com.yiban.yiban_application.util.ResponseResult;
import com.yiban.yiban_application.web.service.VideoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Videolmpl implements VideoInterface {

    @SuppressWarnings("unchecked")
    private ResponseResult<Wall_video> result = new ResponseResult();

    @Autowired
    private ResourceInterface resourceInterface;

    @Override
    public ResponseResult videoAllByList() {
        List<Wall_video> videos = resourceInterface.videoAllByList();
        if (videos.size()!=0){
            result.setStatus(1);
            result.setMessage("查询成功");
            result.setResult(videos);
            return result;
        }
        return result;
    }
}
