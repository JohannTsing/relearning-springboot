package com.johann.iocaop.bean_config.clazz;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Bean配置方式: 基于Java类配置
 * @author Johann
 * @version 1.0
 * @see
 **/
public class BeanConfigClazz {
    public BeanConfigClazz() {
        System.out.println("BeanConfigClazz容器启动初始化。。。");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ClazzConfig.class);
    }

    public static void main(String[] args) {
        new BeanConfigClazz();
    }
}
