package com.johann.iocaop.aop.reverseProxy.cjlib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>
 * 定义一个方法拦截器，用于在方法调用前后添加额外的逻辑
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class TargetInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before method: " + method.getName());
        // 在目标方法调用前后添加额外的逻辑
        // 此处应该是调用父类的方法，而不是调用代理类的方法，否则会造成死循环，因为代理类的方法会再次调用intercept方法。
        //Object result = methodProxy.invoke(o, objects);
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("After method: " + method.getName());
        return result;
    }
}
