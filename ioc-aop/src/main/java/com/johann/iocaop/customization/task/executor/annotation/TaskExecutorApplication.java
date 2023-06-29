package com.johann.iocaop.customization.task.executor.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 根据注解配置异步任务执行器
 * @author Johann
 * @version 1.0
 * @see
 **/
public class TaskExecutorApplication {

    private AnnotationConfigApplicationContext applicationContext;

    public TaskExecutorApplication(){
        applicationContext = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
    }

    public static void main(String[] args) {
        TaskExecutorApplication taskExecutorApplication = new TaskExecutorApplication();
        taskExecutorApplication.applicationContext.getBean(AsyncService.class).asyncSayHello();
        System.out.println("异步任务执行完毕");
        taskExecutorApplication.applicationContext.close();
    }
}
