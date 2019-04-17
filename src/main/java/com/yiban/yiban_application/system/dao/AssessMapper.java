package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.Wall_assess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssessMapper {

    boolean insertWallAssess(Wall_assess wallAssess);

    boolean updateAssess(Wall_assess wallAssess);

    int assessConut();

    int assessConutByUser(String user_id);

    List<Wall_assess> assessAll();

    List<Wall_assess> assessAllByTrends(String trends_id);

    boolean delAssessById(int id);

    boolean delAssessByList(List<String> list);

}
