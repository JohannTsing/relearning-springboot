package com.johann.iocaop.aop.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Configuration
@ComponentScan(basePackages = "com.johann.iocaop.aop.annotation")
// 开启@AspectJ 注解支持。启用CGLIB风格的 "子类 "代理，而不是默认的基于接口的JDK代理方法
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AnnotationConfig {

}
