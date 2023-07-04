package com.johann.iocaop.aop.reverseProxy.cjlib;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class CglibProxyDemo {
    public static void main(String[] args) {
        // 创建目标对象
        Target target = new Target();
        // 创建方法拦截器
        TargetInterceptor  targetInterceptor = new TargetInterceptor();
        // 创建代理对象
        Target proxy = EnhanceCreator.getCGLibProxyInstance(target, targetInterceptor);
        // 调用代理对象的方法
        proxy.doSomthing();
    }
}
