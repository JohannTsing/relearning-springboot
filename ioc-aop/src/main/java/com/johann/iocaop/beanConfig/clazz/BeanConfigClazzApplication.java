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
        // 即使没有在配置文件中启用<context:annotation-config />，AnnotationConfigApplicationContext容器也会启用注释驱动的功能
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ClazzConfig.class);
    }

    public static void main(String[] args) {
        new BeanConfigClazzApplication();
    }
}
