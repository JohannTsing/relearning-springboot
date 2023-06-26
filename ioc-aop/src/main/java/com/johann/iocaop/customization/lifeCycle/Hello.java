package com.johann.iocaop.customization.lifeCycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 定制初始化和销毁回调
 * @author Johann
 * @version 1.0
 * @see
 **/
public class Hello implements InitializingBean, DisposableBean {

    public String sayHello(){
        return "Hello World";
    }

    /**
     *
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("通过实现InitializingBean接口，定制初始化回调方法");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("通过@PostConstruct注解，定制初始化回调方法");
    }

    public void initMethod(){
        System.out.println("通过</bean>中的init-method属性，定制初始化回调方法");
    }

    /**
     *
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("通过实现DisposableBean接口，定制销毁回调方法");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("通过@PreDestroy注解，定制销毁回调方法");
    }

    public void destroyMethod(){
        System.out.println("通过</bean>中的destroy-method属性，定制销毁回调方法");
    }
}
