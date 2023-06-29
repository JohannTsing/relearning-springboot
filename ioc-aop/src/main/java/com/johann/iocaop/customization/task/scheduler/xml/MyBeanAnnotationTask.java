package com.johann.iocaop.customization.task.scheduler.xml;

import org.springframework.scheduling.annotation.Scheduled;

/** 使用@Scheduled注解配置定时任务
 * @Description: MyBeanAnnotationScheduler
 * @Auther: Johann
 * @Version: 1.0
 */
public class MyBeanAnnotationTask {

    @Scheduled(cron = "0/8 * * * * ?")
    public void doTask(){
        System.out.println("@Scheduled配置的定时任务执行中..."+System.currentTimeMillis());
    }
}
