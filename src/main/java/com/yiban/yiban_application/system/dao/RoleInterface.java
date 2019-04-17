package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleInterface {
    /**
     * 获取管理员角色
     */
    String getRoleById(int role);

    List<Role> findUserRole(String s);
}
