package com.yiban.yiban_application.system.service.impl;
import com.yiban.yiban_application.javabean.Sys_info;
import com.yiban.yiban_application.system.dao.*;
import com.yiban.yiban_application.system.service.SystemInfoService;
import com.yiban.yiban_application.web.service.AssessInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SystemInfoServiceImpl implements SystemInfoService {

    @Autowired
    private LogInterface logInterface;

    @Autowired
    private RendsInterface rendsInterface;

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private SystermInterface systermInterface;
    @Autowired
    private AssessInterface assessInterface;
    @Override
    public int trendsConut() {
        return rendsInterface.rendsConut();
    }

    @Override
    public int accessConut(String op) {
        return logInterface.getLogByOperation(op);
    }

    @Override
    public int accessConutByDay(String date) {

        return logInterface.getLogByDate(date);
    }

    @Override
    public int userCount() {
        return userInterface.userCount();
    }

    @Override
    public boolean insert(Sys_info sysInfo) {
        return systermInterface.insert(sysInfo);
    }

    @Override
    public int noGankCount() {
        return rendsInterface.noConut();
    }

    @Override
    public int yesGankCount() {
        return rendsInterface.yesConut();
    }

    @Override
    public int assessCout() {
        return assessInterface.assessConut();
    }
}
