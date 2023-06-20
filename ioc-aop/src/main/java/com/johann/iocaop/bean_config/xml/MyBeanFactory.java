package com.johann.iocaop.bean_config.xml;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class MyBeanFactory {
    public MyBean createInstance(String name) {
        return new MyBean(name);
    }

    private MyBeanFactory() {
        System.out.println("MyBeanFactory empty constructor...");
    }

    private MyBeanFactory(String name) {
        System.out.println("MyBeanFactory constructor...");
        System.out.println("MyBeanFactory initialize name: "+name+"...");
    }

    public static MyBeanFactory createFactoryInstance(String name) {
        return new MyBeanFactory(name);
    }
}
