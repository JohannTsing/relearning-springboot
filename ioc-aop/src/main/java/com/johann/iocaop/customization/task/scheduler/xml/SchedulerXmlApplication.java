package com.johann.iocaop.customization.task.scheduler.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于xml配置文件的方式配置任务调度器
 * @author Johann
 * @version 1.0
 * @see
 **/
public class SchedulerXmlApplication {

    private ApplicationContext applicationContext;

    public SchedulerXmlApplication(){
        applicationContext = new ClassPathXmlApplicationContext("spring-taskExecutor-taskScheduler.xml");
    }

    public static void main(String[] args) {
        new SchedulerXmlApplication();
    }

}
