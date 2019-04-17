package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.Authorization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorizationInterface {
    List<Authorization> findUserPermissions(String name);
}
