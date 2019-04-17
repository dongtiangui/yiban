package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.Sys_admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminInterface {
    /**
     * 获取管理员信息
     * @param id
     * @return
     */
    Sys_admin getAdmin(String id);

    /**
     * 根据用户名匹配密码
     * @param name
     * @return
     */
    String getPass(String name);

    /**
     * 插入管理员
     * @return
     */
    boolean insertAdmin(Sys_admin admin);

    /**
     * 更新管理员密码
     * @param newpassword
     * @param username
     * @return
     */
    boolean updateAdmin(@Param(value = "newpassword") String newpassword,@Param(value = "username") String username);



}
