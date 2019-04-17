package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.Wall_user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInterface {
    /**
     * 根据昵称查记录
     * @param name
     * @return
     */
    Wall_user getUser(String name);

    /**
     * 根据id查记录
     * @param id
     * @return
     */
    Wall_user getUserById(String id);

    /**
     * 根据实体插入数据
      * @param user
     * @return
     */
    boolean insertByUser(Wall_user user);

    /**
     * 统计用户数
     * @return
     */
    int userCount();

    /**
     * 查询所有用户
     * @return
     */
    List<Wall_user> getAllUser();

    /**
     * 更新用户的评论数，动态数
     * @param id
     * @param trends
     * @param assess
     * @return
     */

    boolean updateUserTA(int trends,int assess,int id);
}
