package com.johann.iocaop.aop.reverseProxy.jdk;

/**
 * JDK动态代理示例
 * @author Johann
 * @version 1.0
 * @see
 **/
public class JdkProxyDemo {

    public static void main(String[] args) {
        ITarget target = new TargetImpl();
        ITarget proxy = TargetProxy.newInstance(target, new TargetInvocationHandler(target));
        proxy.doSomething();
    }
}
