package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogInterface {

    void insertLog(SysLog log);

    List<SysLog> getAllLog();

    int getLogByDate(String date);

    int getLogByOperation(String operation);



}
