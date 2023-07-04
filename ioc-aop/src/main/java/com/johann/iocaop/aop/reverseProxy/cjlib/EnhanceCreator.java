package com.johann.iocaop.aop.reverseProxy.cjlib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * <p> CGLib代理类生成器 </p>
 * @author Johann
 * @version 1.0
 * @see
 **/
public class EnhanceCreator {

    //private static Enhancer enhancer = new Enhancer();

    /**
     * 创建CGLib代理类
     * @param target
     * @param interceptor
     * @return
     * @param <T>
     */
    public static <T> T getCGLibProxyInstance(T target, MethodInterceptor interceptor){
        // 创建Enhancer对象，用于生成代理类
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(target.getClass());
        // 设置enhancer的回调对象
        enhancer.setCallback(interceptor);
        // 创建代理类
        T proxyInstance = (T) enhancer.create();
        return proxyInstance;
    }

}
