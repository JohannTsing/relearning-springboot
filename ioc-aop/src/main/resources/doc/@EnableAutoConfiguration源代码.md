### `@EnableAutoConfiguration`

```java
/**
 * Enable auto-configuration of the Spring Application Context, attempting to guess and
 * configure beans that you are likely to need. Auto-configuration classes are usually
 * applied based on your classpath and what beans you have defined. For example, if you
 * have {@code tomcat-embedded.jar} on your classpath you are likely to want a
 * {@link TomcatServletWebServerFactory} (unless you have defined your own
 * {@link ServletWebServerFactory} bean).
 * 启用 Spring 应用程序上下文的自动配置，尝试猜测并配置您可能需要的 bean。 自动配置类通常根据您的类路径和您定义的 bean 来应用。 
 * 例如，如果您的类路径中有 tomcat-embedded.jar，您可能需要一个 TomcatServletWebServerFactory（除非您定义了自己的 ServletWebServerFactory bean）。
 * 
 * <p>
 * When using {@link SpringBootApplication @SpringBootApplication}, the auto-configuration
 * of the context is automatically enabled and adding this annotation has therefore no
 * additional effect.
 * 当使用@SpringBootApplication时，上下文的自动配置会自动启用，因此添加此注释没有额外的效果。
 *
 * <p>
 * Auto-configuration tries to be as intelligent as possible and will back-away as you
 * define more of your own configuration. You can always manually {@link #exclude()} any
 * configuration that you never want to apply (use {@link #excludeName()} if you don't
 * have access to them). You can also exclude them through the
 * {@code spring.autoconfigure.exclude} property. Auto-configuration is always applied
 * after user-defined beans have been registered.
 * 自动配置会尽量智能化，当您定义了更多您自己的配置时，自动配置就会退出。
 * 你可以通过exclude()手动排除任何你不想应用的配置（如果你无法访问它们，使用excludeName()）。
 * 你也可以通过spring.autoconfigure.exclude属性排除它们。自动配置总是在用户自定义Bean注册后应用。
 
 * <p>
 * The package of the class that is annotated with {@code @EnableAutoConfiguration},
 * usually through {@code @SpringBootApplication}, has specific significance and is often
 * used as a 'default'. For example, it will be used when scanning for {@code @Entity}
 * classes. It is generally recommended that you place {@code @EnableAutoConfiguration}
 * (if you're not using {@code @SpringBootApplication}) in a root package so that all
 * sub-packages and classes can be searched.
 * 用@EnableAutoConfiguration注解的类的包，通常是通过@SpringBootApplication，具有特定的意义，并且经常被用作“默认”。 
 * 例如，它将在扫描 @Entity 类时使用。 
 * 通常建议您将@EnableAutoConfiguration（如果您不使用@SpringBootApplication）放置在根包中，以便可以搜索所有子包和类。
 *
 * <p>
 * Auto-configuration classes are regular Spring {@link Configuration @Configuration}
 * beans. They are located using {@link ImportCandidates} and the
 * {@link SpringFactoriesLoader} mechanism (keyed against this class). Generally
 * auto-configuration beans are {@link Conditional @Conditional} beans (most often using
 * {@link ConditionalOnClass @ConditionalOnClass} and
 * {@link ConditionalOnMissingBean @ConditionalOnMissingBean} annotations).
 * 自动配置类是常规的 Spring @Configuration bean。 它们使用 ImportCandidates 和 SpringFactoriesLoader 机制（针对此类）进行定位。 
 * 通常自动配置bean 是 @Conditional bean（最常使用@ConditionalOnClass 和 @ConditionalOnMissingBean 注解）。
 * 
 * @author Phillip Webb
 * @author Stephane Nicoll
 * @since 1.0.0
 * @see ConditionalOnBean
 * @see ConditionalOnMissingBean
 * @see ConditionalOnClass
 * @see AutoConfigureAfter
 * @see SpringBootApplication
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {

	/**
	 * Environment property that can be used to override when auto-configuration is
	 * enabled.
	 * 启用自动配置时可用于覆盖的环境属性。
	 */
	String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";

	/**
	 * Exclude specific auto-configuration classes such that they will never be applied.
	 * @return the classes to exclude
	 * 排除特定的自动配置类，以便它们永远不会被应用
	 */
	Class<?>[] exclude() default {};

	/**
	 * Exclude specific auto-configuration class names such that they will never be
	 * applied.
	 * @return the class names to exclude
	 * @since 1.3.0
	 * 排除特定的自动配置类名称，以便它们永远不会被应用。
	 */
	String[] excludeName() default {};

}
```

### `@AutoConfiguration`

```java
/**
 * Indicates that a class provides configuration that can be automatically applied by
 * Spring Boot. Auto-configuration classes are regular
 * {@link Configuration @Configuration} with the exception that
 * {@literal Configuration#proxyBeanMethods() proxyBeanMethods} is always {@code false}.
 * 表示一个类提供了Spring Boot可以自动应用的配置。 
 * 自动配置类是常规的 @Configuration，但 Configuration#proxyBeanMethods() proxyBeanMethods 始终为 false。
 * 
 * <p>
 * They are located using {@link ImportCandidates} and the {@link SpringFactoriesLoader}
 * mechanism (keyed against {@link EnableAutoConfiguration}).
 * 它们使用 ImportCandidates 和 SpringFactoriesLoader 机制（针对EnableAutoConfiguration）进行定位。
 * 
 * <p>
 * Generally auto-configuration classes are marked as {@link Conditional @Conditional}
 * (most often using {@link ConditionalOnClass @ConditionalOnClass} and
 * {@link ConditionalOnMissingBean @ConditionalOnMissingBean} annotations).
 * 一般来说，自动配置类被标记为 @Conditional（通常使用 @ConditionalOnClass 和 @ConditionalOnMissingBean 注解）。
 * 
 * @author Moritz Halbritter
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

