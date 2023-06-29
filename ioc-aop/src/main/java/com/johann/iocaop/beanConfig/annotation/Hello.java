package com.johann.iocaop.beanConfig.annotation;

import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Component("annotation-helloBean")
public class Hello {
    public void sayHello() {
        System.out.println("Hello World! BeanConfigAnnotation");
    }
}
