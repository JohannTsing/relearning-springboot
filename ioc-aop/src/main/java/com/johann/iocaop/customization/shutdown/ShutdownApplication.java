package com.johann.iocaop.customization.shutdown;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

/**
 * 优雅地关闭Spring应用程序
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Configuration
@Log4j2
@PropertySource(value = "classpath:application-shutdown.properties",ignoreResourceNotFound = true)
@Order(1)
public class ShutdownApplication {

    @Bean(name = "shutdown-hello")
    public Hello hello(){
        return new Hello();
    }

    public static void main(String[] args) {
        // 1.创建AnnotationConfigApplicationContext对象
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ShutdownApplication.class);
        applicationContext.start(); // 触发Lifecycle的start()方法
        Hello hello = applicationContext.getBean("shutdown-hello",Hello.class);
        log.info("hello:{}",hello.sayHello());

        applicationContext.close();// 触发Lifecycle的stop()方法
        log.info("hello:{}",hello.sayHello());
    }
}
