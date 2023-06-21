package com.johann.iocaop.context_inherit;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 容器(应用上下文)的继承
 * @Description: ContextInheritApplication
 * @Auther: Johann
 * @Version: 1.0
 */
@Log4j2
public class ContextInheritApplication {

    private ClassPathXmlApplicationContext parentApplicationContext;
    private ClassPathXmlApplicationContext childApplicationContext;

    public static void main(String[] args) {
        ContextInheritApplication contextInheritApplication = new ContextInheritApplication();
        log.info("parentApplicationContext bean count: {}", contextInheritApplication.parentApplicationContext.getBeanDefinitionCount());
        log.info("childApplicationContext bean count: {}", contextInheritApplication.childApplicationContext.getBeanDefinitionCount());
        log.info("测试可见性");
        contextInheritApplication.testVisibility();
        log.info("测试覆盖");
        contextInheritApplication.testOverride();
    }

    public ContextInheritApplication() {
        // 创建一个父级应用上下文
        parentApplicationContext = new ClassPathXmlApplicationContext("beans-basic-parent.xml");
        parentApplicationContext.setId("parentApplicationContext");
        // 创建一个子级应用上下文。
        // 使用给定的父级创建新的 ClassPathXmlApplicationContext，从给定的 XML 文档加载定义并自动刷新上下文。
        childApplicationContext = new ClassPathXmlApplicationContext(new String[]{"beans-basic-child.xml"}, parentApplicationContext);
        childApplicationContext.setId("childApplicationContext");
        // 设置是否允许通过注册具有相同名称的不同定义来覆盖bean定义，并自动替换前者。否则，将引发异常。默认值为“true”
        childApplicationContext.setAllowBeanDefinitionOverriding(false);
    }

    private void testVisibility() {
        log.info("parentApplicationContext can see bean <parentHello> : {}", parentApplicationContext.containsBean("parentHello"));
        log.info("parentApplicationContext can see bean <childHello> : {}", parentApplicationContext.containsBean("childHello"));
        log.info("childApplicationContext can see bean <parentHello> : {}", childApplicationContext.containsBean("parentHello"));
        log.info("childApplicationContext can see bean <childHello> : {}", childApplicationContext.containsBean("childHello"));
    }

    private void testOverride() {
        log.info("parentApplicationContext bean <hello>, sayHello : {}", parentApplicationContext.getBean("hello", Hello.class).hello());
        log.info("childApplicationContext bean <hello>, sayHello : {}", childApplicationContext.getBean("hello", Hello.class).hello());
    }
}
