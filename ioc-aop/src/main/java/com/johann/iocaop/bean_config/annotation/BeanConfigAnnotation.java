package com.johann.iocaop.bean_config.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean的配置方式: 基于注解配置
 * @author Johann
 * @version 1.0
 * @see
 **/
public class BeanConfigAnnotation {

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        BeanConfigAnnotation beanConfigAnnotation = new BeanConfigAnnotation();
        beanConfigAnnotation.sayHello(beanConfigAnnotation.applicationContext.getBean(Hello.class));
    }

    public BeanConfigAnnotation() {
        applicationContext = new ClassPathXmlApplicationContext("beans-config-annotation.xml");
    }

    @Autowired(required = false)
    @Qualifier("helloBean")
    public void sayHello(Hello hello){
        hello.sayHello();
    }
}
