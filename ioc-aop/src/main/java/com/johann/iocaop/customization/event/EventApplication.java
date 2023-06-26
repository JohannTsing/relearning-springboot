package com.johann.iocaop.customization.event;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 这是一个配置类
 * @author Johann
 * @version 1.0
 * @see
 **/
@Configuration
/**
 * 自动扫描路径下的所有带以下注解的组件（如果没有指定扫描的基础包路径或者类，默认就从该配置类的包开始扫描），将其注册到Spring容器中。
 * 默认情况下，将检测到Spring提供的@Component、@Repository、@Service、@Controller、@RestController、@ControllerAdvice和@Configuration等注解。
 *
 * <context:component-scan />元素默认隐含了<context:annotation-config />效果。但是，这个注释没有。
 */
@ComponentScan(basePackages = "com.johann.iocaop.customization.event")
@Log4j2
public class EventApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(EventApplication.class);
        Hello hello = applicationContext.getBean("event-hello",Hello.class);
        log.info(hello.sayHello());

        applicationContext.getBean("customEventPublisher", CustomEventPublisher.class).fire();

        applicationContext.close();
    }



}
