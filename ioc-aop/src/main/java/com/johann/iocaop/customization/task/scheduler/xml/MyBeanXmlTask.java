package com.johann.iocaop.customization.task.scheduler.xml;

/** 使用<task:scheduled-tasks>标签配置定时任务
 * @Description: MyBeanXmlScheduler
 * @Auther: Johann
 * @Version: 1.0
 */
public class MyBeanXmlTask {

    public void doTask(){
        System.out.println("<task:scheduled-tasks>配置的定时任务执行中..."+System.currentTimeMillis());
    }
}
