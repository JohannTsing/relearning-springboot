package com.johann.iocaop.contextInherit;

/**
 * @Description: Hello
 * @Auther: Johann
 * @Version: 1.0
 */
public class Hello {
    private String name;
    public String hello() {
        return "Hello World! I am "+name+"!;";
    }
    public void setName(String name) {
        this.name = name;
    }
}
