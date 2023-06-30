package com.johann.iocaop.customization.task.executor.annotation;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Service
public class AsyncService {

    @Async//("clazz-taskExecutor")
    public void asyncSayHello(){
        System.out.printf("[当前线程名称: %s]: %s\n",Thread.currentThread().getName(),"异步执行sayHello");
        int i = 1/0;
        throw new RuntimeException("异步任务发生异常");
    }

}
