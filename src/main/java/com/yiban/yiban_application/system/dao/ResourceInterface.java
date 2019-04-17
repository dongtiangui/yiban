package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.Wall_image;
import com.yiban.yiban_application.javabean.Wall_video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 资源mapper接口
 */
@Mapper
public interface ResourceInterface {

    void insertWall_video(Wall_video wall_video);

    void insertWall_image(Wall_image wall_image);

    List<Wall_image> imageAllByList(List list);

    List<Wall_video> videoAllByList();

    List<Wall_image> imageAll();

    void deleteImageById(String image_id,String image_name);

    void deleteVideoById(String video_id,String video_name);


}
