package com.johann.iocaop.circularDependency.noDependency_aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class NoDependencyAopApplication {
    private ApplicationContext applicationContext;

    public NoDependencyAopApplication(){
        this.applicationContext = new ClassPathXmlApplicationContext("circularDependency/no-dependency-aop.xml");
    }

    public static void main(String[] args) {
        NoDependencyAopApplication noDependencyAopApplication = new NoDependencyAopApplication();
        SimpleBean simpleBean = (SimpleBean) noDependencyAopApplication.applicationContext.getBean("simpleBean");
        System.out.println(simpleBean.getClass().getTypeName());
        simpleBean.sayHello("Johann");
    }
}
