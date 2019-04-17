package com.yiban.yiban_application.web.service.empl;

import com.yiban.yiban_application.common.UploadImageResModel;
import com.yiban.yiban_application.javabean.Wall_image;
import com.yiban.yiban_application.system.dao.ResourceInterface;
import com.yiban.yiban_application.util.ResponseResult;
import com.yiban.yiban_application.web.service.ImageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImageService implements ImageInterface {

    private ResponseResult<Wall_image> result = new ResponseResult<Wall_image>();

    @Autowired
    private ResourceInterface resourceInterface;

    @Override
    public UploadImageResModel upImage(String user_id, String image_url) {

        UploadImageResModel imageResModel = new UploadImageResModel();

        return null;
    }

    @Override
    public UploadImageResModel delImage(String image_id) {
        return null;
    }

    @Override
    public List<Wall_image> imageAll() {
        return resourceInterface.imageAll();
    }
    @Override
    public List<Wall_image> imageAllByList(List list) {

        return resourceInterface.imageAllByList(list);
    }
}
