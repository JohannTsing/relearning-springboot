### 1, 起步依赖
起步依赖（starter dependency）的目的就是解决依赖管理难题：针对一个功能，需要引入哪些依赖、它们的版本又是什么、互相之间是否存在冲突、它们的间接依赖项之间是否存
在冲突……
现在我们可以把这些麻烦都交给 Spring Boot 的起步依赖来解决。

#### 1.1, 起步依赖的命名规则
Spring Boot 的起步依赖命名规则如下：
- spring-boot-starter：Spring Boot 的核心功能，比如自动配置、配置加载等；
- spring-boot-starter-test：测试模块，包括 JUnit、Hamcrest、Mockito；
- spring-boot-starter-web：Web 模块，包括 RESTful、JSON、Tomcat，默认使用 Tomcat 作为内嵌容器；
- spring-boot-starter-actuator：生产环境监控和管理模块，包括健康检查、审计、统计和 HTTP 追踪等功能；
- spring-boot-starter-aop：面向切面编程模块，包括 AOP 和 Aspects；
- spring-boot-starter-jdbc：JDBC 数据访问模块，包括 spring-jdbc；
- spring-boot-starter-data-jpa：JPA 数据访问模块，包括 spring-data-jpa、spring-orm 和 Hibernate，默认使用 Hibernate 作为 JPA 实现；
- spring-boot-starter-data-mongodb：MongoDB 数据访问模块，包括 spring-data-mongodb；
- spring-boot-starter-redis：Redis 数据访问模块，包括 spring-data-redis，默认使用 Lettuce 作为 Redis 客户端；
- spring-boot-starter-logging：日志模块，包括 Logback；
- spring-boot-starter-log4j2：日志模块，包括 Log4j2；
- spring-boot-starter-security：安全模块，包括 spring-security；
- spring-boot-starter-data-rest：基于 Spring Data REST 的 RESTful 风格的模块，包括 spring-data-rest-webmvc；
- spring-boot-starter-thymeleaf：模板引擎模块，包括 Thymeleaf；
- spring-boot-starter-velocity：模板引擎模块，包括 Velocity；
- spring-boot-starter-websocket：Web 模块，包括 spring-websocket；
- spring-boot-starter-jms：消息队列模块，包括 JMS；
- spring-boot-starter-activemq：消息队列模块，包括 ActiveMQ；
- spring-boot-starter-amqp：消息队列模块，包括 RabbitMQ；
- spring-boot-starter-social-web：社交应用模块，包括 spring-social-web；

#### 1.2, 起步依赖的实现原理
起步依赖背后使用的其实就是 Maven 的传递依赖机制。看似只添加了一个依赖，但实际上通过传递依赖，我们已经引入了一堆的依赖。
> 什么是传递依赖? 
> 假设 B 依赖于 C，而 A 又依赖于 B，那么 A 无须明确声明对 C 的依赖，而是通过 B 依赖于 C

### 2, 自动配置
Spring Boot 可以根据 CLASSPATH、配置项等条件自动进行常规配置，省去了我们自己动手把一模一样的配置复制来复制去的麻烦。
如果它的配置不是我们想要的，再做些手动配置就好了。

在SpringBoot中，使用`@EnableAutoConfiguration`注解来开启自动配置，它的源码如下：
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
    String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";

    Class<?>[] exclude() default {};

    String[] excludeName() default {};
}
```
`@EnableAutoConfiguration`注解的核心是`@Import(AutoConfigurationImportSelector.class)`，它的作用就是从`META-INF/spring.factories`配置文件中读取`EnableAutoConfiguration`指定的配置类，然后将它们添加到 Spring 的容器中。

> 从 Spring Boot 2.7 开始，AutoConfigurationImportSelector 不再从 /META-INF/spring.factories 加载自动配置类，而
是开始使用新的 /META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports 文件，直接
在里面添加自动配置类的全限定类名即可。

`@SpringBootApplication` 注解上添加了`@EnableAutoConfiguration`注解，所以它也能开启自动配置功能。 
这两个注解上都有 `exclude` 属性，我们可以在其中排除一些不想启用的自动配置类。

如果不想启用自动配置功能，也可以在配置文件中配置 `spring.boot.enableautoconfiguration=false`，关闭该功能。