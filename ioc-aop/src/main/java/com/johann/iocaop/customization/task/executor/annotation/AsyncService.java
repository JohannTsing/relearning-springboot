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
        System.out.println("异步执行sayHello");
        System.out.println("当前线程名称："+Thread.currentThread().getName());
        int i = 1/0;
        throw new RuntimeException("异步任务发生异常");
    }

}
