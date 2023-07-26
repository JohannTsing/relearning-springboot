Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime, for example:

>   表示一个类声明了一个或多个@Bean方法，可以由Spring容器处理，以便在运行时为这些Bean生成Bean定义和服务请求，例如：

```java
@Configuration
public class AppConfig {

    @Bean
    public MyBean myBean() {
        // instantiate, configure and return bean ...
    }
}
```

## 引导`@Configuration`类

>   Bootstrapping `@Configuration` classes

### 通过`AnnotationConfigApplicationContext`

>   Via `AnnotationConfigApplicationContext`

`@Configuration`classes are typically bootstrapped using either `AnnotationConfigApplicationContext` or its web-capable variant, `AnnotationConfigWebApplicationContext`. A simple example with the former follows:

>   `@Configuration`类通常使用`AnnotationConfigApplicationContext`或其Web功能的变体`AnnotationConfigWebApplicationContext`进行引导。下面是一个使用前者的简单例子：

```java
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
ctx.register(AppConfig.class);
ctx.refresh();
MyBean myBean = ctx.getBean(MyBean.class);
// use myBean ...
```

See the `AnnotationConfigApplicationContext javadocs` for further details, and see `AnnotationConfigWebApplicationContext` for web configuration instructions in a Servlet container.

>   请参阅 AnnotationConfigApplicationContext javadocs 以了解更多细节，并参阅 AnnotationConfigWebApplicationContext 以了解 Servlet 容器中的 Web 配置说明。

### 通过Spring <beans> XML

>   Via Spring `<beans>` XML

As an alternative to registering `@Configuration` classes directly against an `AnnotationConfigApplicationContext`, `@Configuration` classes may be declared as normal `<bean>` definitions within Spring XML files:

>   作为直接针对AnnotationConfigApplicationContext注册@Configuration类的替代方案，@Configuration类可以作为Spring XML文件中的普通<bean>定义来声明：

```xml
<beans>
    <context:annotation-config/>
    <bean class="com.acme.AppConfig"/>
</beans>
```

In the example above, `<context:annotation-config/>` is required in order to enable `ConfigurationClassPostProcessor` and other annotation-related post processors that facilitate handling @Configuration classes.

>   在上面的例子中，`<context:annotation-config/>`是必需的，以便启用ConfigurationClassPostProcessor和其他与注释相关的后置处理器，方便处理@Configuration类。

### 通过组件扫描

>   Via component scanning

`@Configuration` is meta-annotated with @Component, therefore @Configuration classes are candidates for component scanning (typically using Spring XML's `<context:component-scan/>` element) and therefore may also take advantage of `@Autowired/@Inject` like any regular @Component. In particular, if a single constructor is present autowiring semantics will be applied transparently for that constructor:

>   `@Configuration`被@Component元标记，因此@Configuration类是组件扫描的候选者（通常使用Spring XML的<context:component-scan/>元素），因此也可以像任何普通的@Component一样利用@Autowired/@Inject的优势。特别是，如果有一个构造函数存在，自动连接语义将透明地应用于该构造函数：

```java
@Configuration
public class AppConfig {

    private final SomeBean someBean;

    public AppConfig(SomeBean someBean) {
        this.someBean = someBean;
    }

    // @Bean definition using "SomeBean"

}
```

`@Configuration` classes may not only be bootstrapped using component scanning, but may also themselves configure component scanning using the `@ComponentScan` annotation:

>   `@Configuration`类不仅可以使用组件扫描进行引导，而且还可以使用@ComponentScan注解自己配置组件扫描：

```java
@Configuration
@ComponentScan("com.acme.app.services")
public class AppConfig {
    // various @Bean definitions ...
}
```

See the `@ComponentScan` javadocs for details.

>   详情见@ComponentScan javadocs。

## 使用外部化的值

>   Working with externalized values

### 使用Environment API

>   Using the Environment API

Externalized values may be looked up by injecting the Spring `org.springframework.core.env.Environment` into a `@Configuration` class — for example, using the `@Autowired` annotation:

>   通过将Spring `org.springframework.core.env.Environment`注入到@Configuration类中--例如，使用`@Autowired`注解，可以查询到外部化的值：

```java
@Configuration
public class AppConfig {

    @Autowired 
    Environment env;

    @Bean
    public MyBean myBean() {
        MyBean myBean = new MyBean();
        myBean.setName(env.getProperty("bean.name"));
        return myBean;
    }
}
```

Properties resolved through the Environment reside in one or more "property source" objects, and @Configuration classes may contribute property sources to the Environment object using the `@PropertySource` annotation:

>   通过环境解析的属性驻留在一个或多个 "属性源 "对象中，而@Configuration类可以使用@PropertySource注解将属性源贡献给环境对象：

```java
@Configuration
@PropertySource("classpath:/com/acme/app.properties")
public class AppConfig {

    @Inject 
    Environment env;

    @Bean
    public MyBean myBean() {
        return new MyBean(env.getProperty("bean.name"));
    }
}
```

See the Environment and `@PropertySource` javadocs for further details.

>   更多细节请参见Environment和@PropertySource javadocs。

### 使用`@Value`注解

>    Using the `@Value` annotation

Externalized values may be injected into `@Configuration` classes using the `@Value` annotation:

>   外化的值可以使用@Value注解注入到@Configuration类中：

```java
@Configuration
@PropertySource("classpath:/com/acme/app.properties")
public class AppConfig {

    @Value("${bean.name}") 
    String beanName;

    @Bean
    public MyBean myBean() {
        return new MyBean(beanName);
    }
}
```

This approach is often used in conjunction with Spring's `PropertySourcesPlaceholderConfigurer` that can be enabled automatically in XML configuration via `<context:property-placeholder/>` or explicitly in a `@Configuration` class via a dedicated static `@Bean` method (see "a note on BeanFactoryPostProcessor-returning @Bean methods" of @Bean's javadocs for details). 

>   这种方法通常与Spring的PropertySourcesPlaceholderConfigurer结合使用，它可以在XML配置中通过`<context:property-placeholder/>`自动启用，也可以在@Configuration类中通过专用的静态@Bean方法显式启用（详见@Bean的javadocs中 "关于BeanFactoryPostProcessor-returning @Bean方法的说明"）。

Note, however, that explicit registration of a `PropertySourcesPlaceholderConfigurer` via a static @Bean method is typically only required if you need to customize configuration such as the placeholder syntax, etc. Specifically, if no bean post-processor (such as a `PropertySourcesPlaceholderConfigurer`) has registered an embedded value resolver for the `ApplicationContext`, Spring will register a default embedded value resolver which resolves placeholders against property sources registered in the Environment. 

>   然而，请注意，通常只有在你需要定制配置（如占位符语法等）时，才需要通过静态的@Bean方法明确注册PropertySourcesPlaceholderConfigurer。具体来说，如果没有Bean后置处理器（如PropertySourcesPlaceholderConfigurer）为ApplicationContext注册嵌入式值解析器，Spring将注册一个默认的嵌入式值解析器，该解析器针对在环境中注册的属性源解析占位符。

See the section below on composing `@Configuration` classes with Spring XML using `@ImportResource`; see the @Value javadocs; and see the @Bean javadocs for details on working with BeanFactoryPostProcessor types such as PropertySourcesPlaceholderConfigurer.

>   请参阅下面有关使用“@ImportResource”与 Spring XML 组合“@Configuration”类的部分； 请参阅@Value javadocs；有关使用 BeanFactoryPostProcessor 类型（例如 PropertySourcesPlaceholderConfigurer）的详细信息，请参阅 @Bean javadocs。

## 组合`@Configuration`类

>   Composing `@Configuration` classes

### 使用@Import

>   With the `@Import` annotation

`@Configuration` classes may be composed using the @Import annotation, similar to the way that `<import>` works in Spring XML. Because `@Configuration` objects are managed as Spring beans within the container, imported configurations may be injected — for example, via constructor injection:

>   `@Configuration` 类可以使用 @Import 注释来组成，类似于 Spring XML 中 `<import>` 的工作方式。 由于“@Configuration”对象在容器内作为 Spring bean 进行管理，因此可以注入导入的配置 - 例如，通过构造函数注入：

```java
@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        // instantiate, configure and return DataSource
    }
}

@Configuration
@Import(DatabaseConfig.class)
public class AppConfig {

    private final DatabaseConfig dataConfig;

    public AppConfig(DatabaseConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

    @Bean
    public MyBean myBean() {
        // reference the dataSource() bean method
        return new MyBean(dataConfig.dataSource());
    }
}
```

Now both AppConfig and the imported DatabaseConfig can be bootstrapped by registering only AppConfig against the Spring context:

>   现在，AppConfig 和导入的 DatabaseConfig 都可以通过仅针对 Spring 上下文注册 AppConfig 来引导：

```java new AnnotationConfigApplicationContext(AppConfig.class);```

### 使用@Profile

>   With the @Profile annotation

`@Configuration` classes may be marked with the `@Profile` annotation to indicate they should be processed only if a given profile or profiles are active:

>   `@Configuration'类可以用@Profile注解标记，以表明只有在给定的配置文件处于活动状态时才应处理它们：

```java
@Profile("development")
@Configuration
public class EmbeddedDatabaseConfig {

    @Bean
    public DataSource dataSource() {
        // instantiate, configure and return embedded DataSource
    }
}

@Profile("production")
@Configuration
public class ProductionDatabaseConfig {

    @Bean
    public DataSource dataSource() {
        // instantiate, configure and return production DataSource
    }
}
```

Alternatively, you may also declare profile conditions at the `@Bean` method level — for example, for alternative bean variants within the same configuration class:

>   或者，您也可以在 @Bean 方法级别声明配置文件条件 - 例如，对于同一配置类中的替代 bean 变体：

```java
@Configuration
public class ProfileDatabaseConfig {

    @Bean("dataSource")
    @Profile("development")
    public DataSource embeddedDatabase() { ... }

    @Bean("dataSource")
    @Profile("production")
    public DataSource productionDatabase() { ... }
}
```

See the `@Profile` and `org.springframework.core.env.Environment` javadocs for further details.

>   更多细节请参见`@Profile`和`org.springframework.core.env.Environment javadocs`。

### 通过Spring XML使用`@ImportResource`注解

>   With Spring XML using the `@ImportResource` annotation

As mentioned above, `@Configuration` classes may be declared as regular Spring `<bean>` definitions within Spring XML files. It is also possible to import Spring XML configuration files into `@Configuration` classes using the `@ImportResource` annotation. 

Bean definitions imported from XML can be injected — for example, using the `@Inject` annotation:

>   如上所述，@Configuration类可以在Spring XML文件中作为普通的Spring <bean>定义被声明。也可以使用@ImportResource注解将Spring XML配置文件导入到@Configuration类。
>
>   从XML导入的Bean定义可以被注入--例如，使用@Inject注解：

```java
@Configuration
@ImportResource("classpath:/com/acme/database-config.xml")
public class AppConfig {

    @Inject 
    DataSource dataSource; // from XML

    @Bean
    public MyBean myBean() {
        // inject the XML-defined dataSource bean
        return new MyBean(this.dataSource);
    }
}
```

### 使用嵌套的@Configuration类

>   With nested `@Configuration` classes

`@Configuration` classes may be nested within one another as follows:

>   `@Configuration` 类可以相互嵌套，如下所示：

```java
@Configuration
public class AppConfig {

    @Inject 
    DataSource dataSource;

    @Bean
    public MyBean myBean() {
        return new MyBean(dataSource);
    }

    // Any nested configuration classes must be declared as static.
    // 任何嵌套的配置类必须被声明为静态的。
    @Configuration
    static class DatabaseConfig {
        @Bean
        DataSource dataSource() {
            return new EmbeddedDatabaseBuilder().build();
        }
    }
}
```

When bootstrapping such an arrangement, only `AppConfig` need be registered against the application context. By virtue of being a nested `@Configuration` class, `DatabaseConfig` will be registered automatically. This avoids the need to use an `@Import` annotation when the relationship between `AppConfig` and `DatabaseConfig` is already implicitly clear.

>   当启用这样的安排时，只需要针对应用程序上下文注册 AppConfig。 由于是嵌套的 @Configuration 类，DatabaseConfig 将自动注册。 当 AppConfig 和 DatabaseConfig 之间的关系已经隐式明确时，这可以避免使用 @Import 注释。

Note also that nested `@Configuration` classes can be used to good effect with the `@Profile` annotation to provide two options of the same bean to the enclosing `@Configuration` class.

>   另请注意，嵌套的 @Configuration 类可以与 @Profile 注释一起使用，以向封闭的 @Configuration 类提供同一 bean 的两个选项，从而获得良好的效果。

## 配置延迟初始化

>   Configuring lazy initialization

By default, `@Bean` methods will be eagerly instantiated at container bootstrap time. To avoid this, `@Configuration` may be used in conjunction with the `@Lazy` annotation to indicate that all `@Bean` methods declared within the class are by default lazily initialized. Note that `@Lazy` may be used on individual `@Bean` methods as well.

>   默认情况下，@Bean 方法将在容器引导时立即实例化。 为了避免这种情况，@Configuration 可以与 @Lazy 注释结合使用，以指示类中声明的所有 @Bean 方法默认都是延迟初始化的。 请注意，@Lazy 也可以用于单个 @Bean 方法。

## 对 @Configuration 类的测试支持

>   Testing support for @Configuration classes

The Spring` TestContext framework` available in the spring-test module provides the `@ContextConfiguration` annotation which can accept an array of component class references — typically `@Configuration` or `@Component` classes.

>   spring-test模块中的Spring`TestContext框架`提供了`@ContextConfiguration`注解，它可以接受一个组件类的引用数组--通常是`@Configuration`或`@Component`类。

```java
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DatabaseConfig.class})
public class MyTests {

    @Autowired 
    MyBean myBean;

    @Autowired 
    DataSource dataSource;

    @Test
    public void test() {
        // assertions against myBean ...
    }
}
```

See the `TestContext framework`  reference documentation for details.

>   有关详细信息，请参阅 TestContext 框架参考文档。

## 使用@Enable注解启用内置Spring功能

>   Enabling built-in Spring features using `@Enable` annotations

Spring features such as asynchronous method execution, scheduled task execution, annotation driven transaction management, and even Spring MVC can be enabled and configured from `@Configuration` classes using their respective "@Enable" annotations. See `@EnableAsync`, `@EnableScheduling`, `@EnableTransactionManagement`, `@EnableAspectJAutoProxy`, and `@EnableWebMvc` for details.

>   Spring 功能（例如异步方法执行、计划任务执行、注释驱动的事务管理，甚至 Spring MVC）都可以使用各自的“@Enable”注释从“@Configuration”类启用和配置。 详见@EnableAsync、@EnableScheduling、@EnableTransactionManagement、@EnableAspectJAutoProxy和@EnableWebMvc。

## 编写“@Configuration”类时的约束

>   Constraints when authoring `@Configuration` classes

*   Configuration classes must be provided as classes (i.e. not as instances returned from factory methods), allowing for runtime enhancements through a generated subclass.

*   Configuration classes must be non-final (allowing for subclasses at runtime), unless the `proxyBeanMethods` flag is set to false in which case no runtime-generated subclass is necessary.

*   Configuration classes must be non-local (i.e. may not be declared within a method).

*   Any nested configuration classes must be declared as static.

*   @Bean methods may not in turn create further configuration classes (any such instances will be treated as regular beans, with their configuration annotations remaining undetected).

>   *   配置类必须作为类提供（即不是作为从工厂方法返回的实例），从而允许通过生成的子类进行运行时增强。
>   *   配置类必须是非final（允许在运行时生成子类），除非“proxyBeanMethods”标志设置为 false，在这种情况下不需要运行时生成的子类。
>   *   配置类必须是非本地的（即不能在方法内声明）。
>   *   任何嵌套的配置类必须被声明为静态的。
>   *   @Bean方法不能反过来创建更多的配置类（任何这样的实例都将被视为普通的Bean，其配置注解仍未被发现）。

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {
    /** 
     * 显式指定与 @Configuration 类关联的 Spring bean 定义的名称。 如果未指定（常见情况），将自动生成 bean 名称。
     * 
     * 仅当通过组件扫描拾取 @Configuration 类或直接提供给 AnnotationConfigApplicationContext 时，自定义名称才适用。 
     * 如果 @Configuration 类注册为传统的 XML bean 定义，则 bean 元素的名称/id 将优先。
     */
    @AliasFor(annotation = Component.class)
	String value() default "";
    
    /** 
     * 指定 @Bean 方法是否应被代理，以执行 bean 生命周期行为，例如，即使在用户代码中直接调用 @Bean 方法，也要返回共享的单例 bean 实例。
     * 该功能需要方法拦截，通过运行时生成的 CGLIB 子类实现，该子类具有一些限制，如配置类及其方法不允许声明 final。
	 * 
	 * 默认值为 true，允许通过配置类内的直接方法调用进行 "bean间引用"，也允许从外部调用该配置的 @Bean 方法，例如从另一个配置类调用。
	 * 如果不需要这样做，因为每个特定配置的 @Bean 方法都是独立的，并且被设计为供容器使用的普通工厂方法，请将此标志切换为 false 以避免 CGLIB 子类处理。
     * 关闭 bean 方法拦截会有效地单独处理 @Bean 方法，就像在非 @Configuration 类中声明时一样，也就是"@Bean Lite Mode"
     *（请参阅 @Bean 的 javadoc）。因此，它在行为上等同于移除 @Configuration 定型。
     */
    boolean proxyBeanMethods() default true;
}

```



