Enables support for handling components marked with AspectJ's @Aspect annotation, similar to functionality found in Spring's `<aop:aspectj-autoproxy> `XML element. To be used on @Configuration classes as follows:

>   启用支持处理使用AspectJ的@Aspect注解标记的组件，类似于Spring的`<aop:aspectj-autoproxy>` XML元素中的功能。要在@Configuration类上使用，如下：

```java
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

  @Bean
  public FooService fooService() {
      return new FooService();
  }

  @Bean
  public MyAspect myAspect() {
      return new MyAspect();
  }
}
```

Where FooService is a typical POJO component and MyAspect is an @Aspect-style aspect:

>   其中FooService是一个典型的POJO组件，MyAspect是一个@Aspect风格的方面：

```java
public class FooService {
 
  // various methods
}
@Aspect
public class MyAspect {

  @Before("execution(* FooService+.*(..))")
  public void advice() {
      // advise FooService methods as appropriate
  }
}
```

In the scenario above, @EnableAspectJAutoProxy ensures that MyAspect will be properly processed and that FooService will be proxied mixing in the advice that it contributes.
Users can control the type of proxy that gets created for FooService using the proxyTargetClass() attribute. The following enables CGLIB-style 'subclass' proxies as opposed to the default interface-based JDK proxy approach.

>   在上面的场景中，@EnableAspectJAutoProxy确保MyAspect将被正确处理，FooService将被代理混合在它所贡献的建议中。
>   用户可以使用proxyTargetClass()属性来控制为FooService创建的代理类型。以下是启用CGLIB风格的 "子类 "代理，而不是默认的基于接口的JDK代理方法。

```java
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AppConfig {
  // ...
}
```

Note that `@Aspect` beans may be component-scanned like any other. Simply mark the aspect with both `@Aspect` and `@Component`:

>   请注意，@Aspect beans 可以像其他bean一样进行组件扫描。只需用@Aspect和@Component来标记该方面：

```java
package com.foo;
 
@Component
public class FooService { ... }

@Aspect
@Component
public class MyAspect { ... }
```
Then use the @ComponentScan annotation to pick both up:

> 然后使用@ComponentScan注解来拾取这两个东西：
```java
@Configuration
@ComponentScan("com.foo")
@EnableAspectJAutoProxy
public class AppConfig {

  // no explicit @Bean definitions required
}
```

Note: @EnableAspectJAutoProxy applies to its local application context only, allowing for selective proxying of beans at different levels. Please redeclare @EnableAspectJAutoProxy in each individual context, e.g. the common root web application context and any separate DispatcherServlet application contexts, if you need to apply its behavior at multiple levels.

>   注意： @EnableAspectJAutoProxy只适用于它的本地应用上下文，允许在不同层次上对Bean进行选择性的代理。如果你需要在多个级别上应用它的行为，请在每个单独的上下文中重新声明@EnableAspectJAutoProxy，例如共同的根Web应用上下文和任何单独的DispatcherServlet应用上下文。

This feature requires the presence of aspectjweaver on the classpath. While that dependency is optional for spring-aop in general, it is required for @EnableAspectJAutoProxy and its underlying facilities.

>   这个功能需要在classpath上有aspectjweaver。虽然这个依赖对于spring-aop来说是可选的，但对于@EnableAspectJAutoProxy和它的底层设施来说是必需的。

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AspectJAutoProxyRegistrar.class)
public @interface EnableAspectJAutoProxy {

	/**
	 * Indicate whether subclass-based (CGLIB) proxies are to be created as opposed
	 * to standard Java interface-based proxies. The default is {@code false}.
	 * 指示是否要创建基于子类（CGLIB）的代理，而不是基于标准Java接口的代理。默认为false。
	 */
	boolean proxyTargetClass() default false;

	/**
	 * Indicate that the proxy should be exposed by the AOP framework as a {@code ThreadLocal}
	 * for retrieval via the {@link org.springframework.aop.framework.AopContext} class.
	 * Off by default, i.e. no guarantees that {@code AopContext} access will work.
	 * @since 4.3.1
	 * 指示代理应被AOP框架作为ThreadLocal公开，以便通过org.springframework.aop.framework.AopContext类进行检索。
	 * 默认为关闭，即不保证AopContext的访问会有效。(是否将代理对象暴露到 AopContext 中)
	 */
	boolean exposeProxy() default false;

}
```
