package com.yiban.yiban_application.web.service;

import com.yiban.yiban_application.common.UploadImageResModel;
import com.yiban.yiban_application.javabean.Wall_image;
import java.util.List;
public interface ImageInterface {
    /**
     * 图片上传
     * @param user_id
     * @param image_url
     * @return
     */
    UploadImageResModel upImage(String user_id,String image_url);


    /**
     * 根据ID删除图片
     * @param image_id
     * @return
     */
    UploadImageResModel delImage(String image_id);

    /**
     * 查询所有图片
     * @return
     */

    List<Wall_image> imageAll();

    /**
     * 根据集合id查图片
     */


    List<Wall_image> imageAllByList(List list);


}
