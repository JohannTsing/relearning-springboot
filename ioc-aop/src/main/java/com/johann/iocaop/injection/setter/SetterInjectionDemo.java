package com.johann.iocaop.injection.setter;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 依赖注入: setter注入
 * setter方法注入适用于需要在Bean创建之后动态设置依赖关系的场景。
 * 例如，需要在运行时根据某些条件来设置依赖关系，或者需要在Bean创建之后才能获取到依赖的对象等情况。
 * @Description: SetterInjectionDemo
 * @Auther: Johann
 * @Version: 1.0
 */
@Log4j2
public class SetterInjectionDemo {

    private ApplicationContext applicationContext;
    public SetterInjectionDemo() {
        applicationContext = new ClassPathXmlApplicationContext("beans-dependency-setter-injection.xml");
    }

    public static void main(String[] args) {
        SetterInjectionDemo demo = new SetterInjectionDemo();
        Person person = demo.applicationContext.getBean("person", Person.class);
        log.info(person);
    }

}
