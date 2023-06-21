package com.johann.iocaop.injection.constructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 依赖关系注入：构造器注入
 * 构造器注入适用于需要注入的依赖关系比较固定，且在对象创建时就需要初始化的情况。
 * 例如，一个对象依赖于另一个对象的实例，并且这个依赖关系在对象创建时就已经确定，那么就可以使用构造器注入的方式来实现依赖注入。
 * @Description: ConstructorInjecttionDemo
 * @Auther: Johann
 * @Version: 1.0
 */
@Log4j2
public class ConstructorInjecttionDemo {

    private ApplicationContext applicationContext;
    public ConstructorInjecttionDemo() {
        applicationContext = new ClassPathXmlApplicationContext("beans-dependency-constructor-injection.xml");
    }

    public static void main(String[] args) {
        ConstructorInjecttionDemo demo = new ConstructorInjecttionDemo();
        Person person = demo.applicationContext.getBean("person", Person.class);
        log.info(person);
    }

}
