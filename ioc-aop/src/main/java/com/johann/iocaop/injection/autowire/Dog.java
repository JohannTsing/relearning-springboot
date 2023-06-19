package com.johann.iocaop.injection.autowire;

/**
 * @Description: Dog
 * @Auther: Johann
 * @Version: 1.0
 */
public class Dog implements Pet{
    @Override
    public String say() {
        return "wang wang wang";
    }
}
