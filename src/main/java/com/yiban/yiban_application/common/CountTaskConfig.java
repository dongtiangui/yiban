package com.yiban.yiban_application.common;
import com.yiban.yiban_application.controller.BaseCronTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

@Configuration
public class CountTaskConfig {
//    统计一天总数
    @Bean(name = "detailFactoryBean")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(BaseCronTrigger cronTrigger){
        MethodInvokingJobDetailFactoryBean method = new MethodInvokingJobDetailFactoryBean();
//        是否并发执行
        method.setConcurrent(false);
//        指定对象
        method.setTargetObject(cronTrigger);
//        指定方法
        method.setTargetMethod("savaContSysInfo");
        return method;

    }

    @Bean("triggerFactoryBean")
     public CronTriggerFactoryBean triggerFactoryBean(MethodInvokingJobDetailFactoryBean detailFactoryBean){

        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(detailFactoryBean.getObject());
//        定义规则
//        每天晚上11点进行定时统计
        factoryBean.setCronExpression("0 0 23 * * ?");
//        factoryBean.setCronExpression("*/5 * * * * ?");
        return factoryBean;
    }

}
