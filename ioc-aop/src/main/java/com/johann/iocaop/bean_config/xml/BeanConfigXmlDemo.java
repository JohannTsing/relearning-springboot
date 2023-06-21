package com.johann.iocaop.bean_config.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean的配置方式: 基于XML文件配置
 * @author Johann
 * @version 1.0
 * @see
 **/
public class BeanConfigXmlDemo {
    private ApplicationContext applicationContext;

    public BeanConfigXmlDemo() {
        this.applicationContext = new ClassPathXmlApplicationContext("beans-config-xml.xml");
    }

    public static void main(String[] args) {
        BeanConfigXmlDemo beanConfigXmlDemo = new BeanConfigXmlDemo();
        beanConfigXmlDemo.sayHello();
    }

    public void sayHello() {
        // 延迟加载
        //MyBean myBean = applicationContext.getBean("myBean", MyBean.class);
        //System.out.println(myBean.hello());
    }

}
