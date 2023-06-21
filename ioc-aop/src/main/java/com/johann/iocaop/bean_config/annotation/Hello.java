package com.johann.iocaop.bean_config.annotation;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Component("helloBean")
public class Hello {
    public void sayHello() {
        System.out.println("Hello World! BeanConfigAnnotation");
    }
}
