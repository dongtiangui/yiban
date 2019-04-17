package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.Sys_info;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SystermInterface {

    boolean insert(Sys_info sysInfo);

    Sys_info getSystemInfo();
}
