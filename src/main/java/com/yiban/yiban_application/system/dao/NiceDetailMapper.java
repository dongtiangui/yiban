package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.NiceDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NiceDetailMapper {

    void deleteByPrimaryKey(Integer id);
    NiceDetail findNiceDetail(NiceDetail niceDetail);
    void insertSelective(NiceDetail record);
}
