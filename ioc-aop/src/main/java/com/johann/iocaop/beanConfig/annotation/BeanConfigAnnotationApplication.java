package com.johann.iocaop.beanConfig.annotation;

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
public class BeanConfigAnnotationApplication {

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        BeanConfigAnnotationApplication beanConfigAnnotationApplication = new BeanConfigAnnotationApplication();
        Hello helloBean = beanConfigAnnotationApplication.applicationContext.getBean("helloBean", Hello.class);
        beanConfigAnnotationApplication.sayHello(helloBean);
    }

    public BeanConfigAnnotationApplication() {
        applicationContext = new ClassPathXmlApplicationContext("beans-config-annotation.xml");
    }

    @Autowired(required = false)
    @Qualifier("helloBean")
    public void sayHello(Hello hello){
        hello.sayHello();
    }
}
