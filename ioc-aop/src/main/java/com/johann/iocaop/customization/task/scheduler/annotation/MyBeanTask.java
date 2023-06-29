package com.johann.iocaop.customization.task.scheduler.annotation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description: MyBeanTask
 * @Auther: Johann
 * @Version: 1.0
 */
@Component
public class MyBeanTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void doTask(){
        System.out.println("定时任务执行中..."+System.currentTimeMillis());
    }
}
