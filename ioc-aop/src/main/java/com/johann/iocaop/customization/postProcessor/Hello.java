package com.johann.iocaop.customization.postProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class Hello {

    /**
     * 虽然没有在配置文件中开启<context:annotation-config />，但是AnnotationConfigApplicationContext容器会开启这个注解驱动功能。
     * 激活组件类中的@Required、@Autowired、@PostConstruct、@PreDestroy、@Resource、@PersistenceContext和@PersistenceUnit注解
     */
    @PostConstruct
    public void init(){
        System.out.println("Hello init");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Hello destroy");
    }

    public Hello() {
        System.out.println("Hello构造方法");
    }

    public String sayHello(){
        return "Hello";
    }
}
