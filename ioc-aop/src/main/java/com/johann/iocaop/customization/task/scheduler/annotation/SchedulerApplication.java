package com.johann.iocaop.customization.task.scheduler.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class SchedulerApplication {

    private AnnotationConfigApplicationContext applicationContext;

    public SchedulerApplication(){
        applicationContext = new AnnotationConfigApplicationContext(SchedulerConfig.class);
    }

    public static void main(String[] args) throws InterruptedException {
        SchedulerApplication schedulerApplication = new SchedulerApplication();
        Thread.sleep(10000);
        // 关闭容器
        schedulerApplication.applicationContext.close();
    }
}
