package com.johann.iocaop.injection.autowire;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 依赖注入：自动装配
 * autowire属性用于指定自动装配的方式, 有以下几种取值：
 * 1), no：默认值，表示不进行自动装配，需要手动指定依赖关系。
 * 2), byName：根据属性名自动装配，要求属性名和依赖的bean的名称一致。
 * 3), byType：根据属性类型自动装配，要求属性类型和依赖的bean的类型一致。
 * 4), constructor：根据构造函数参数类型自动装配，要求构造函数参数类型和依赖的bean的类型一致。
 *
 * 对于集合类型的属性，自动装配会把上下文里找到的 Bean 都放进去，但如果属性不是集合类型，有多个候选 Bean 就会有问题。
 * 此处 byType, 报错：No qualifying bean of type 'com.johann.iocaop.injection.autowire.Pet' available: expected single matching bean but found 2: cat,dog
 * 可以在你所期望的候选 Bean 的 <bean/> 中将 primary 设置为 true，这就表明在多个候选 Bean 中该 Bean 是主要的。
 * @Description: AutowireInjectionDemo
 * @Auther: Johann
 * @Version: 1.0
 */
@Log4j2
public class AutowireInjectionDemo {
    private ApplicationContext applicationContext;
    public AutowireInjectionDemo() {
        applicationContext = new ClassPathXmlApplicationContext("beans-dependency-autowire-injection.xml");
    }

    public static void main(String[] args) {
        AutowireInjectionDemo demo = new AutowireInjectionDemo();
        Person person = demo.applicationContext.getBean("person", Person.class);
        log.info(person);
    }

}
