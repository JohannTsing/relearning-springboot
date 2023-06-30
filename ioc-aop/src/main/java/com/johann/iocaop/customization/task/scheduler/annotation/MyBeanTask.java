package com.johann.iocaop.customization.task.scheduler.annotation;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description: MyBeanTask
 * @Auther: Johann
 * @Version: 1.0
 */
@Component
// 如果使用@Scheduled注解创建一个定时任务，此时这个定时任务类不能被设置为懒加载，否则定时任务不会生效
// @Lazy
public class MyBeanTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void doTask(){
        String s = "定时任务执行中..."+System.currentTimeMillis();
        System.out.printf("[当前线程名称: %s]: %s\n",Thread.currentThread().getName(),s);
    }
}
