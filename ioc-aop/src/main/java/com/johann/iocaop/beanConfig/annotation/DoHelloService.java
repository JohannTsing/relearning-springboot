package com.johann.iocaop.beanConfig.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Service
public class DoHelloService {

    // 3，不建议使用字段注入
//    @Autowired(required = false)
//    @Qualifier("annotation-helloBean")
    private Hello hello;

    // 1，建议使用构造器注入
    //@Autowired(required = false)
//    public DoHelloService(Hello hello) {
//        this.hello = hello;
//        System.out.println("DoHelloService容器启动初始化【构造器注入】。。。");
//    }


    // 2，建议使用Setter注入
    @Autowired(required = false)
    public void setHello(Hello hello) {
        this.hello = hello;
        System.out.println("DoHelloService容器启动初始化【Setter注入】。。。");
    }

    public void sayHello(){
        hello.sayHello();
    }
}
