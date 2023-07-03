package com.johann.iocaop.aop.reverseProxy.jdk;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class TargetImpl implements ITarget{
    @Override
    public void doSomething() {
        System.out.println("TargetImpl.doSomething方法被调用");
    }
}
