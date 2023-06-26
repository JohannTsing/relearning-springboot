package com.johann.iocaop.beanConfig.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean的配置方式: 基于XML文件配置
 * @author Johann
 * @version 1.0
 * @see
 **/
public class BeanConfigXmlApplication {
    private ApplicationContext applicationContext;

    public BeanConfigXmlApplication() {
        this.applicationContext = new ClassPathXmlApplicationContext("beans-config-xml.xml");
    }

    public static void main(String[] args) {
        BeanConfigXmlApplication beanConfigXmlApplication = new BeanConfigXmlApplication();
        beanConfigXmlApplication.sayHello();
    }

    public void sayHello() {
        // 延迟加载
        MyBean myBean = applicationContext.getBean("myBean", MyBean.class);
        System.out.println(myBean.hello());
    }

}
