package com.johann.iocaop.customization.task.scheduler.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.context.Theme;

/**
 * 基于xml配置文件的方式配置任务调度器
 * @author Johann
 * @version 1.0
 * @see
 **/
public class SchedulerXmlApplication {

    private ClassPathXmlApplicationContext applicationContext;

    public SchedulerXmlApplication(){
        applicationContext = new ClassPathXmlApplicationContext("spring-taskExecutor-taskScheduler.xml");
    }

    public static void main(String[] args) throws InterruptedException {
        SchedulerXmlApplication schedulerXmlApplication = new SchedulerXmlApplication();
        Thread.sleep(10000);
        schedulerXmlApplication.applicationContext.close();
    }

}
