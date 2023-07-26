### `@ConfigurationProperties`

Annotation for externalized configuration. Add this to a class definition or a @Bean method in a @Configuration class if you want to bind and validate some external Properties (e.g. from a .properties file).
Binding is either performed by calling setters on the annotated class or, if @ConstructorBinding is in use, by binding to the constructor parameters.
Note that contrary to @Value, SpEL expressions are not evaluated since property values are externalized.

>   用于外部化配置的注解。如果您想绑定和验证一些外部属性（例如来自 .properties 文件），请将此注解添加到类定义或 @Configuration 类中的 @Bean 方法中。
>
>   绑定可以通过调用注解类上的setters来执行，或者，如果使用了 @ConstructorBinding，则通过绑定到构造函数参数来执行。
>   请注意，与 @Value 相反，SpEL 表达式不会被求值，因为属性值是外部化的。


将 @ConfigurationProperties 注解的 Bean 注册到 Spring 容器中，有以下三种方式：
1. 可以在其他的配置类中添加这样的注解 @EnableConfigurationProperties(DataSourceProperties.class)，将绑定后的 DataSourceProperties 注册为 Bean。
通过这种方式注册的Bean，它的Bean名称是【属性前缀 - 配置类的全限定类名】。
2. 也可以直接用 @Component 注解将 DataSourceProperties 注册为 Bean。
3. 将@ConfigurationProperties 注解加到带有 @Bean 注解的方法上，此时就能为方法返回的 Bean 对象绑定上下文中的属性了。
```java
/**
 * 通过在其他的配置类中添加 @EnableConfigurationProperties(DataSourceProperties.class) 注解
 * 将绑定后的 DataSourceProperties 注册为 Bean
 * 此时，注册Bean名称是：【属性前缀 - 配置类的全限定类名】，即“spring.datasource-org.springframework.boot.autoconfigure.jdbc.DataSourceProperties”
 */
//@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperties implements BeanClassLoaderAware, InitializingBean {
    private ClassLoader classLoader;
    private String name;
    private boolean generateUniqueName = true;
    private Class<? extends DataSource> type;
    private String driverClassName;
    private String url;
    // 以下省略
}
```

`@ConfigurationProperties`源代码：
```java
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface ConfigurationProperties {

	/**
	 * The prefix of the properties that are valid to bind to this object. Synonym for
	 * {@link #prefix()}. A valid prefix is defined by one or more words separated with
	 * dots (e.g. {@code "acme.system.feature"}).
	 * @return the prefix of the properties to bind 要绑定的属性的前缀
	 * 
	 * 绑定到此对象的有效属性的前缀。prefix()的同义词。一个有效的前缀由一个或多个用点分隔的单词定义（例如 "acme.system.feature"）。
	 */
	@AliasFor("prefix")
	String value() default "";

	/**
	 * The prefix of the properties that are valid to bind to this object. Synonym for
	 * {@link #value()}. A valid prefix is defined by one or more words separated with
	 * dots (e.g. {@code "acme.system.feature"}).
	 * @return the prefix of the properties to bind
	 */
	@AliasFor("value")
	String prefix() default "";

	/**
	 * Flag to indicate that when binding to this object invalid fields should be ignored.
	 * Invalid means invalid according to the binder that is used, and usually this means
	 * fields of the wrong type (or that cannot be coerced into the correct type).
	 * @return the flag value (default false) 
	 * 
	 * 指示绑定到此对象时应忽略无效字段的标志。 根据所使用的绑定器，无效意味着无效，通常这意味着字段类型错误（或者不能强制转换为正确的类型）。
	 * 默认false
	 */
	boolean ignoreInvalidFields() default false;

	/**
	 * Flag to indicate that when binding to this object unknown fields should be ignored.
	 * An unknown field could be a sign of a mistake in the Properties.
	 * @return the flag value (default true) 
	 * 
	 * 指示绑定到此对象时应忽略未知字段的标志。 未知字段可能表示属性中存在错误。
	 * 默认true
	 */
	boolean ignoreUnknownFields() default true;

}
```



### `@ConstructorBinding`

Annotation that can be used to indicate that configuration properties should be bound using constructor arguments rather than by calling setters. Can be added at the type level (if there is an unambiguous constructor) or on the actual constructor to use.
Note: To use constructor binding the class must be enabled using @EnableConfigurationProperties or configuration property scanning. Constructor binding cannot be used with beans that are created by the regular Spring mechanisms (e.g. @Component beans, beans created via @Bean methods or beans loaded using @Import).

>   该注解用于指示应使用构造函数参数而不是调用 setter 来绑定配置属性。可以在类级别添加（如果有明确的构造函数）或在实际使用的构造函数上添加。
>
>   注意：要使用构造函数绑定，必须使用 @EnableConfigurationProperties 或配置属性扫描来启用类。 构造函数绑定不能与由常规 Spring 机制创建的 bean 一起使用（例如 @Component注解的beans、通过 @Bean 方法创建的 beans 或使用 @Import 加载的 beans）。

```java
@Target({ ElementType.TYPE, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConstructorBinding {

}
```



### `@EnableConfigurationProperties`

Enable support for @ConfigurationProperties annotated beans. @ConfigurationProperties beans can be registered in the standard way (for example using @Bean methods) or, for convenience, can be specified directly on this annotation.

>   启用对 @ConfigurationProperties 注解 Bean 的支持。@ConfigurationProperties Beans 可以以标准方式注册（例如使用 @Bean 方法），或者为了方便起见，可以直接在此注解中指定。
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableConfigurationPropertiesRegistrar.class)
public @interface EnableConfigurationProperties {

	/**
	 * The bean name of the configuration properties validator.
	 * @since 2.2.0
	 * 
	 * 配置属性验证器的Bean名称。
	 */
	String VALIDATOR_BEAN_NAME = "configurationPropertiesValidator";

	/**
	 * Convenient way to quickly register
	 * {@link ConfigurationProperties @ConfigurationProperties} annotated beans with
	 * Spring. Standard Spring Beans will also be scanned regardless of this value.
	 * @return {@code @ConfigurationProperties} annotated beans to register
	 *
	 * 向Spring快速注册Bean——添加@ConfigurationProperties注解的Bean——的便捷方法。标准的Spring Bean也将被扫描，无论该值如何。
	 * @return  要注册的添加了@ConfigurationProperties注解的Bean
	 */
	Class<?>[] value() default {};

}
```



### `@AutoConfiguration`

Indicates that a class provides configuration that can be automatically applied by Spring Boot. Auto-configuration classes are regular @Configuration with the exception that Configuration#proxyBeanMethods() proxyBeanMethods is always false.
They are located using ImportCandidates and the SpringFactoriesLoader mechanism (keyed against EnableAutoConfiguration).
Generally auto-configuration classes are marked as @Conditional (most often using @ConditionalOnClass and @ConditionalOnMissingBean annotations).

>   表示一个类提供了可以被Spring Boot自动应用的配置。Auto-configuration类是常规的 @Configuration，但 Configuration#proxyBeanMethods() proxyBeanMethods 始终为 false。
>
>   它们使用 ImportCandidates 和 SpringFactoriesLoader 机制（针对 `@EnableAutoConfiguration`）进行定位。
>   通常，自动配置类被标记为@Conditional（最常用的是``@ConditionalOnClass`和`@ConditionalOnMissingBean`注解）。

```java
/** 
 * @see EnableAutoConfiguration
 * @see AutoConfigureBefore
 * @see AutoConfigureAfter
 * @see Conditional
 * @see ConditionalOnClass
 * @see ConditionalOnMissingBean
 * @since 2.7.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore
@AutoConfigureAfter
public @interface AutoConfiguration {

	/**
	 * Explicitly specify the name of the Spring bean definition associated with the
	 * {@code @AutoConfiguration} class. If left unspecified (the common case), a bean
	 * name will be automatically generated.
	 * <p>
	 * The custom name applies only if the {@code @AutoConfiguration} class is picked up
	 * through component scanning or supplied directly to an
	 * {@link AnnotationConfigApplicationContext}. If the {@code @AutoConfiguration} class
	 * is registered as a traditional XML bean definition, the name/id of the bean element
	 * will take precedence.
	 * @return the explicit component name, if any (or empty String otherwise)
	 * @see AnnotationBeanNameGenerator
	 *
	 * 显式指定与 @AutoConfiguration 类关联的 Spring bean 定义的名称。 如果未指定（常见情况），将自动生成 bean 名称。
	 * 仅当通过组件扫描拾取 @AutoConfiguration 类或直接提供给 AnnotationConfigApplicationContext 时，自定义名称才适用。 
	 * 如果 @AutoConfiguration 类注册为传统 XML bean 定义，则 bean 元素的名称/id 将优先。
	 * @return 显式组件名称（如果有）（否则为空字符串）
	 */
	@AliasFor(annotation = Configuration.class)
	String value() default "";

	/**
	 * The auto-configure classes that should have not yet been applied.
	 * @return the classes
	 * 应该尚未应用的自动配置类。即，当前这个自动配置类需要在【此处指定的配置类】之前配置
	 */
	@AliasFor(annotation = AutoConfigureBefore.class, attribute = "value")
	Class<?>[] before() default {};

	/**
	 * The names of the auto-configure classes that should have not yet been applied.
	 * @return the class names
	 * 尚未应用的自动配置类名称
	 */
	@AliasFor(annotation = AutoConfigureBefore.class, attribute = "name")
	String[] beforeName() default {};

	/**
	 * The auto-configure classes that should have already been applied.
	 * @return the classes
	 * 应该已经应用的自动配置类。即，即，当前这个自动配置类需要在【此处指定的配置类】之后配置
	 */
	@AliasFor(annotation = AutoConfigureAfter.class, attribute = "value")
	Class<?>[] after() default {};

	/**
	 * The names of the auto-configure classes that should have already been applied.
	 * @return the class names
	 * 应该已经应用的自动配置类名称
	 */
	@AliasFor(annotation = AutoConfigureAfter.class, attribute = "name")
	String[] afterName() default {};

}
```



### `ConfigurationPropertiesAutoConfiguration`

```java
/**
 * {@link EnableAutoConfiguration Auto-configuration} for
 * {@link ConfigurationProperties @ConfigurationProperties} beans. Automatically binds and
 * validates any bean annotated with {@code @ConfigurationProperties}.
 * 
 * 自动配置 @ConfigurationProperties Bean。自动绑定和验证任何注释了@ConfigurationProperties 的Bean。
 * 
 * @author Stephane Nicoll
 * @since 1.3.0
 * @see EnableConfigurationProperties
 * @see ConfigurationProperties
 */
@AutoConfiguration
@EnableConfigurationProperties
public class ConfigurationPropertiesAutoConfiguration {

}
```

