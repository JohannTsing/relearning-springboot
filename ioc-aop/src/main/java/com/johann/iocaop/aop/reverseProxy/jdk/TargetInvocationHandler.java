package com.johann.iocaop.aop.reverseProxy.jdk;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理处理器
 * @author Johann
 * @version 1.0
 * @see
 **/
public class TargetInvocationHandler implements InvocationHandler {

    private Object target;

    public TargetInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 代理对象的方法被调用时，会回调invoke方法
     * @param proxy 代理对象
     * @param method 代理对象被调用的方法
     * @param args 调用方法时传入的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理处理器被调用，代理对象为："+proxy.getClass().getName());
        Object obj = method.invoke(target,args);
        return obj;
    }
}
