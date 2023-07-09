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

    // 不建议使用字段注入
//    @Autowired(required = false)
//    @Qualifier("annotation-helloBean")
//    Hello hello;

    private Hello hello;

    @Autowired(required = false)
    public DoHelloService(Hello hello) {
        this.hello = hello;
        System.out.println("DoHelloService容器启动初始化。。。");
    }


    public void sayHello(){
        hello.sayHello();
    }
}
