package com.johann.iocaop.aop.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AnnotationConfig.class);

        MyService myService = applicationContext.getBean("msi",MyService.class);
        String str = myService.doSomething(new StringBuffer("My Friend. "));
        System.out.println(str);
        System.out.println(myService.doSomething(new StringBuffer("My Dear Friend. ")));
    }

}
