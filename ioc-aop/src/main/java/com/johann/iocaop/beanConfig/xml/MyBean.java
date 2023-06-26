package com.johann.iocaop.beanConfig.xml;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class MyBean {

    private String name;

    public MyBean() {
        System.out.println("MyBean empty constructor...");
    }

    public MyBean(String name) {
        this.name = name;
        System.out.println("MyBean constructor...");
        System.out.println("MyBean initialize name: "+name+"...");
    }

    public String hello() {
        return "Hello "+name+"!";
    }
}
