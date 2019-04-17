package com.yiban.yiban_application.controller;

import com.yiban.yiban_application.javabean.Sys_info;
import com.yiban.yiban_application.system.service.impl.SystemInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableScheduling
@Component
public class BaseCronTrigger {

    @Autowired
    private SystemInfoServiceImpl systemInfoServiceImpl;

    /**
     * 定时任务方法
     *
     */
    public void savaContSysInfo(){
        Sys_info sysInfo = new Sys_info();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(new Date());
        synchronized (this){
            int accessConut = systemInfoServiceImpl.accessConut("认证");
            int conutByDay = systemInfoServiceImpl.accessConutByDay(dateNowStr);
            int trendsConut = systemInfoServiceImpl.trendsConut();
            int assessCout = systemInfoServiceImpl.assessCout();
            sysInfo.setInfo_access(String.valueOf(accessConut));
            sysInfo.setInfo_today_access(String.valueOf(conutByDay));
            sysInfo.setInfo_trends(String.valueOf(trendsConut));
            sysInfo.setInfo_ctr(String.valueOf(assessCout));
            sysInfo.setInfo_no_gank(String.valueOf(systemInfoServiceImpl.noGankCount()));
            sysInfo.setInfo_yes_gank(String.valueOf(systemInfoServiceImpl.yesGankCount()));
            systemInfoServiceImpl.insert(sysInfo);
        }
    }
}
