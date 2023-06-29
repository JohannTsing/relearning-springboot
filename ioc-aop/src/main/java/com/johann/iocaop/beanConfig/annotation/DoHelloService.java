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
    @Autowired(required = false)
    @Qualifier("annotation-helloBean")
    Hello hello;

    public void sayHello(){
        hello.sayHello();
    }
}
