package com.johann.iocaop.aop.reverseProxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 生成代理对象的工具类
 * @author Johann
 * @version 1.0
 * @see
 **/
public class TargetProxy {

    /**
     * 生成代理对象
     * @param target
     * @param invocationHandler
     * @return
     * @param <T>
     */
    public static <T> T newInstance(T target, InvocationHandler invocationHandler) {
        T proxy = (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocationHandler);
        return proxy;
    }
}
