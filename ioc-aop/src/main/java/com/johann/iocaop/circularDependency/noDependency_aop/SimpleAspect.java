package com.johann.iocaop.circularDependency.noDependency_aop;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class SimpleAspect {
    public void before() {
        System.out.println("前置增强处理...");
    }
}
