package com.yiban.yiban_application.system.service.impl;

import com.yiban.yiban_application.javabean.Wall_trends;
import com.yiban.yiban_application.system.dao.RendsInterface;
import com.yiban.yiban_application.system.service.RendsService;
import com.yiban.yiban_application.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RendsServiceImpl implements RendsService {

    private final RendsInterface rendsInterface;

    @Autowired
    public RendsServiceImpl(RendsInterface rendsInterface) {
        this.rendsInterface = rendsInterface;
    }

    @Override
    public int rendsConut() {
        return rendsInterface.rendsConut();
    }

    @Override
    public int noConut() {
        return rendsInterface.noConut();
    }

    @Override
    public int yesConut() {
        return rendsInterface.yesConut();
    }

    @Override
    public int trendsConutByUser(String user_id) {

        return rendsInterface.trendsConutByUser(user_id);
    }

    @Override
    public Wall_trends getAllById(String trends_id) {
        return rendsInterface.getAllById(trends_id);
    }

    @Override
    public Wall_trends getTrendsByMax() {
        return rendsInterface.getTrendsByMax();
    }

    @Override
    public List<Wall_trends> getAll() {
        return rendsInterface.getAll();
    }

    @Override
    public List<Wall_trends> getAllByUser(String user_id) {
        return rendsInterface.getAllByUser(user_id);
    }
    @Override
    public List<Wall_trends> getAllByHome() {
        return rendsInterface.getAllByHome();
    }

    @Override
    public List<Wall_trends> getAllByType(Integer type) {
        return rendsInterface.getAllByType(type);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public ResponseResult insertRends(Wall_trends wall_trends) {
        ResponseResult result = new ResponseResult();
        try {
            if (rendsInterface.insertRends(wall_trends)){
                result.setStatus(1);
                result.setMessage("提交成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(0);
            result.setError("系统错误");
        }
        return result;
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public ResponseResult deleteRendsById(String id) {
        boolean byId = rendsInterface.deleteRendsById(id);
        ResponseResult<Object> result = new ResponseResult<>();
        if (byId){
            result.setMessage("删除成功");
            result.setStatus(200);
            return result;
        }
        result.setStatus(400);
        result.setMessage("系统异常");
        return result;
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public ResponseResult updateRendsById(Wall_trends wall_trends) {
        ResponseResult result = new ResponseResult();
        boolean byId = rendsInterface.updateRendsById(wall_trends);
        if (byId){
            result.setStatus(1);
            result.setMessage("修改成功");
            return result;
        }
        return null;
    }

    @Override
    public ResponseResult updateSataus(String id, Integer status,String admin) {
        ResponseResult<Object> result = new ResponseResult<>();
        if (rendsInterface.updateSataus(status, id,admin)){
            result.setMessage("授权成功");
            result.setStatus(200);
            return result;
        }
        result.setMessage("授权失败");
        result.setStatus(400);
        return result;
    }
}
