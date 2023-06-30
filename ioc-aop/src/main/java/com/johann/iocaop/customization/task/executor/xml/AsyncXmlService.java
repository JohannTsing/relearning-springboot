package com.johann.iocaop.customization.task.executor.xml;

import org.springframework.scheduling.annotation.Async;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class AsyncXmlService {

    @Async("taskExecutor")
    public void asyncSayHello(){
        System.out.printf("[当前线程名称: %s]: %s\n",Thread.currentThread().getName(),"异步执行sayHello");
        int i = 1/0;
        throw new RuntimeException("异步任务发生异常");
    }
}
