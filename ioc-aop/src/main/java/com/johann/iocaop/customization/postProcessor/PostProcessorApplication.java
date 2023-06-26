package com.johann.iocaop.customization.postProcessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Configuration
public class PostProcessorApplication {

    @Bean(name = "postProcessor-hello")
    public Hello hello(){
        return new Hello();
    }

    @Bean
    public HelloBeanPostProcessor helloBeanPostProcessor(){
        return new HelloBeanPostProcessor();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(PostProcessorApplication.class);
        applicationContext.close();
    }
}
