package com.yiban.yiban_application.web.service;

import com.yiban.yiban_application.javabean.NiceDetail;

public interface NiceDetailService {

    /**
     * 插入点赞记录
     * @param niceDetail
     * @return
     */
    void insertNiceDetail(NiceDetail niceDetail);

    /**
     * 删除点赞记录
     * @param id
     * @return
     */
    void deleteNiceDetail(Integer id);

 /**
      * 根据用户id和文章id信息查询点赞记录
      * @param niceDetail
      * @return
      */
        NiceDetail findNiceDetail(NiceDetail niceDetail);

}
