package com.yiban.yiban_application.web.service;

import com.yiban.yiban_application.javabean.Wall_assess;
import com.yiban.yiban_application.util.ResponseResult;

import java.util.List;

public interface AssessInterface {

    ResponseResult insertWallAssess(Wall_assess wallAssess);
    /**
     * 评论更新
     * @param wallAssess
     * @return ResponseResult
     */

    ResponseResult updateAssess(Wall_assess wallAssess);

    /**
     * 总的评论数
     * @return ResponseResult
     */
    int assessConut();

    /**
     * 用户的评论数
     * @param user_id
     * @return ResponseResult
     */

    int assessConutByUser(String user_id);

    /**
     * 查询所有评论信息
     * @return
     */

    ResponseResult assessAll();

    /**
     *
     * @param trends_id
     * @return
     */
    List<Wall_assess> assessAllByTrends(String trends_id);
    /**
     * 删除一条评论
     * @param id
     * @return
     */

    ResponseResult delAssessBy(String id);

    /**
     * 批量删除评论
     * @param list
     * @return
     */
    ResponseResult delAssessByList(List<String> list);

}
