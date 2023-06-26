package com.johann.iocaop.beanConfig.clazz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean配置方式: 基于Java类配置
 * @author Johann
 * @version 1.0
 * @see
 **/
public class BeanConfigClazzApplication {
    public BeanConfigClazzApplication() {
        System.out.println("BeanConfigClazz容器启动初始化。。。");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ClazzConfig.class);
    }

    public static void main(String[] args) {
        new BeanConfigClazzApplication();
    }
}
