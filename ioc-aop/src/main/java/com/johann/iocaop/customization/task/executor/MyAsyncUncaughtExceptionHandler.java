package com.johann.iocaop.customization.task.executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * 自定义异步任务异常处理器
 * @author Johann
 * @version 1.0
 * @see
 **/
public class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
    /**
     * Handle the given uncaught exception thrown from an asynchronous method.
     *
     * @param ex     the exception thrown from the asynchronous method
     * @param method the asynchronous method
     * @param params the parameters used to invoke the method
     */
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        System.out.printf("[当前线程名称: %s]: MyAsyncUncaughtExceptionHandler捕获到异常，异常信息为：%s\n",Thread.currentThread().getName(),ex.getMessage());
        System.out.printf("[当前线程名称: %s]: MyAsyncUncaughtExceptionHandler捕获到异常，方法名为：%s\n",Thread.currentThread().getName(),method.getName());
        for (Object param : params) {
            System.out.println("MyAsyncUncaughtExceptionHandler捕获到异常，参数为："+param);
        }
    }
}
