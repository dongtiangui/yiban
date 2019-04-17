package com.yiban.yiban_application.web.service;


import com.yiban.yiban_application.javabean.Wall_trends;

import java.util.List;

public interface IndexServiceInterface {

    /**
     *
     * @param pageNum 当前页
     * @param pageSize 展示多少页
     * @return
     */
    List<Wall_trends> getWall_trendsList(int pageNum,int pageSize);

    /**
     * 根据搜索信息匹配动态
     * @param search_info
     * @return
     */
    List<Wall_trends> getWall_trendsListBySearch(String search_info);



}
