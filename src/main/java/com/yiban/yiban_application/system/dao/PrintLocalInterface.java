package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.PrintLocal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrintLocalInterface {

    boolean insert(PrintLocal printLocal);

    List<PrintLocal> getAll();

    boolean delById(int id);

}
