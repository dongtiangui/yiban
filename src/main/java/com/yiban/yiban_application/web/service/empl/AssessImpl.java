package com.yiban.yiban_application.web.service.empl;

import com.yiban.yiban_application.javabean.Wall_assess;
import com.yiban.yiban_application.system.dao.AssessMapper;
import com.yiban.yiban_application.util.ResponseResult;
import com.yiban.yiban_application.web.service.AssessInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service

public class AssessImpl implements AssessInterface {

    private ResponseResult result = new ResponseResult();

    @Autowired
    private AssessMapper assessMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public ResponseResult insertWallAssess(Wall_assess wallAssess) {
        boolean assess = assessMapper.insertWallAssess(wallAssess);
        if (assess){
            result.setMessage("评论成功");
            result.setStatus(1);
            return result;
        }
        return null;
    }
    @Override
    public ResponseResult updateAssess(Wall_assess wallAssess) {

        assessMapper.updateAssess(wallAssess);
        return null;
    }

    @Override
    public int assessConut() {
        return assessMapper.assessConut();
    }

    @Override
    public int assessConutByUser(String user_id) {

        return assessMapper.assessConutByUser(user_id);
    }

    @Override
    public ResponseResult assessAll() {
        return null;
    }

    @Override
    public ResponseResult delAssessBy(String id) {
        return null;
    }

    @Override
    public ResponseResult delAssessByList(List<String> list) {
        return null;
    }

    @Override
    public List<Wall_assess> assessAllByTrends(String trends_id) {
        return assessMapper.assessAllByTrends(trends_id);
    }
}
