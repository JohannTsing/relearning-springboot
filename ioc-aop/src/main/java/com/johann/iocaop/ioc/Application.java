package com.johann.iocaop.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class Application {
    private BeanFactory beanFactory;
    /**
     * Spring 应用上下文
     * spring-context 模块在 spring-core 和 spring-beans 的基础上提供了更丰富的功能，例如事件传播、资源加载、国际化支持等。
     * BeanFactory 是容器的基础接口，ApplicationContext 接口继承了 BeanFactory。
     * ApplicationContext 是 Spring 框架的核心接口，它提供了更高级的配置机制，它是 BeanFactory 的超集，通常我们优先考虑使用 ApplicationContext。
     *
     * ApplicationContext 常见的几种实现
     * 1), ClassPathXmlApplicationContext：从 CLASSPATH 中加载 XML 文件来配置 ApplicationContext
     * 2), FileSystemXmlApplicationContext：从文件系统中加载 XML 文件来配置 ApplicationContext
     * 3), AnnotationConfigApplicationContext：根据注解和 Java 类配置 ApplicationContext.（即使没有在配置文件中启用<context:annotation-config />，AnnotationConfigApplicationContext容器也会启用注释驱动的功能）
     * 4), WebXmlApplicationContext：从Web应用下的某个文件加载配置文件
     */
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        // 不使用spring，直接new对象
        //Hello hello = new Hello();
        //System.out.println(hello.hello());
        Application application = new Application();
        application.sayHello();
    }

    public Application() {
        // 使用xml配置bean，加载到beanFactory。此时需要再添加一个依赖：spring-beans
//        beanFactory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader =
//                new XmlBeanDefinitionReader((DefaultListableBeanFactory) beanFactory);
//        //reader.loadBeanDefinitions(new ClassPathResource("beans-basic.xml"));
//        reader.loadBeanDefinitions("beans-basic.xml");

        // 加载到applicationContext,添加依赖：spring-context
        applicationContext = new ClassPathXmlApplicationContext("beans-basic.xml");
    }

    public void sayHello() {
        //Hello hello = (Hello) beanFactory.getBean("hello");
        //Hello hello = beanFactory.getBean("hello", Hello.class);
        Hello hello = applicationContext.getBean("hello", Hello.class);
        System.out.println(applicationContext.getBean("hello", Hello.class)==hello);
        System.out.println(hello.hello());
    }
}
