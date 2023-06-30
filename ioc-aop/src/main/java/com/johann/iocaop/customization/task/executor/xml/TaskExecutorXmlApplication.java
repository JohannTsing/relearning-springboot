package com.johann.iocaop.customization.task.executor.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 读取xml配置文件的方式配置异步任务执行器
 * @author Johann
 * @version 1.0
 * @see
 **/
public class TaskExecutorXmlApplication {

    private ClassPathXmlApplicationContext applicationContext;

    public TaskExecutorXmlApplication(){
        applicationContext = new ClassPathXmlApplicationContext("spring-taskExecutor-taskScheduler.xml");
    }

    public static void main(String[] args) {
        TaskExecutorXmlApplication taskExecutorXmlApplication = new TaskExecutorXmlApplication();
        taskExecutorXmlApplication.applicationContext.getBean(AsyncXmlService.class).asyncSayHello();
        System.out.println("异步任务执行完毕");
        taskExecutorXmlApplication.applicationContext.close();
    }
}
