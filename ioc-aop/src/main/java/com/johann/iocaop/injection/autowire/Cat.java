package com.johann.iocaop.injection.autowire;

/**
 * @Description: Cat
 * @Auther: Johann
 * @Version: 1.0
 */
public class Cat implements Pet{
    @Override
    public String say() {
        return "miao miao miao";
    }
}
