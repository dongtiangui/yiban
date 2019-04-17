package com.yiban.yiban_application.system.dao;

import com.yiban.yiban_application.javabean.Wall_trends;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 动态mapper接口
 */
@Mapper
public interface RendsInterface {
    /**
     * 动态总数
     * @return
     */
    int rendsConut();

    /**
     * 已授权数
     * @return
     */
    int noConut();

    /**
     * 未授权数
     * @return
     */

    int yesConut();

    /**
     * 用户动态总数
     * @param user_id
     * @return
     */
    int trendsConutByUser(String user_id);

    /**
     * 根据动态id查询
     * @param trends_id
     * @return
     */

    Wall_trends getAllById(String trends_id);


    Wall_trends getTrendsByMax();
    /**
     * 查询所有的动态信息
     * @return
     */

    List<Wall_trends> getAll();

    /**
     * 根据用户查询动态
     * @param user_id
     * @return
     */

    List<Wall_trends> getAllByUser(String user_id);

    /**
     * 查询点赞数在前三的动态
     * @return
     */

    List<Wall_trends> getAllByHome();

    /**
     * 根据动态类型查询所有动态
     * @param type
     * @return
     */

    List<Wall_trends> getAllByType(Integer type);

    /**
     * 插入一条动态
     * @param wall_trends
     * @return
     */

    boolean insertRends(Wall_trends wall_trends);

    /**
     * 根据ID删除一条动态
     * @param id
     * @return
     */

    boolean deleteRendsById(String id);

    /**
     * 修改一条动态
     * @param wall_trends
     * @return
     */

    boolean updateRendsById(Wall_trends wall_trends);


    /**
     * 授权
      * @param id
     */
    boolean updateSataus(int new_trends_status,String id,String admin);
}
