package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.Print;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrintInterface {
    /**
     * 插入一条订单
     * @param print
     * @return
     */
    boolean insertPrint(Print print);
    /**
     * 查询所有订单
     * @return
     */
    List<Print> selectPrint();

    /**
     *
     * @param id
     * @param status
     * @return
     */
    boolean UpdatePrintStatus(String status,int id);

    boolean deleteById(Integer id);

}
