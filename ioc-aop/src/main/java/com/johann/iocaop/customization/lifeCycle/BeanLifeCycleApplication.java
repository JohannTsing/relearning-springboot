package com.johann.iocaop.customization.lifeCycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class BeanLifeCycleApplication {

    private ApplicationContext applicationContext;

    public BeanLifeCycleApplication() {
        System.out.println("BeanLifeCycleDemo容器启动初始化。。。");
        applicationContext = new ClassPathXmlApplicationContext("beans-lifecycle.xml");
    }

    public static void main(String[] args) {
        BeanLifeCycleApplication beanLifeCycleApplication = new BeanLifeCycleApplication();
        beanLifeCycleApplication.sayHello();
        beanLifeCycleApplication.close();
    }

    public void sayHello(){
        Hello hello = applicationContext.getBean("lifecycle-hello",Hello.class);
        System.out.println(hello.sayHello());
    }

    public void close(){
        ((ClassPathXmlApplicationContext)applicationContext).close();
    }

}
