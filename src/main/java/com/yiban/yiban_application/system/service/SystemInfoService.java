package com.yiban.yiban_application.system.service;

import com.yiban.yiban_application.javabean.Sys_info;

/**
 * 系统统计mapper接口
 */

public interface SystemInfoService {
    /**
     * 动态总数
     * @return
     */
    int trendsConut();

    /**
     * 访问总数
     * @param op
     * @return
     */
    int accessConut(String op);

    /**
     * 每天的访问量
     * @param date
     * @return
     */
    int accessConutByDay(String date);

    /**
     * 用户总数
     * @return
     */

    int userCount();

    /**
     * 插入系统信息
     * @param sysInfo
     * @return
     */
    boolean insert(Sys_info sysInfo);

    /**
     * 未授权数
     * @return
     */

    int noGankCount();

    /**
     * 已授权数
     * @return
     */

    int yesGankCount();

    /**
     * 累计评论总数
     * @return
     */
    int assessCout();


}
