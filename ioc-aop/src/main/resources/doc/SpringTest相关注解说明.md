### `@RunWith`

`@RunWith`注解用于JUnit测试框架中，它指定了一个自定义的测试运行器（Runner），用于执行测试类中的测试方法。测试运行器负责加载测试类、创建测试实例、执行测试方法等操作。在Spring中，通常使用`SpringJUnit4ClassRunner`作为测试运行器，它能够启动Spring容器，并自动进行依赖注入等操作，以便在测试中使用Spring的功能。

>   【`@RunWith`注解用于JUnit 4】

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyTest {
    // 测试方法...
}
```



### `@ExtendWith`

`@ExtendWith`注解是JUnit 5引入的新注解，用于扩展测试执行的行为。它允许开发人员注册自定义的扩展，以在测试执行期间插入自定义逻辑。在Spring中，通常使用`SpringExtension`作为扩展，它与`@RunWith`注解的作用类似，可以启动Spring容器并进行依赖注入等操作。

>   【`@ExtendWith`注解用于JUnit 5】

```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyTest {
    // 测试方法...
}

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Repeatable(Extensions.class)
@API(status = STABLE, since = "5.0")
public @interface ExtendWith {

	/**
	 * An array of one or more {@link Extension} classes to register.
	 */
	Class<? extends Extension>[] value();

}
```



### `@TestPropertySource`

`@TestPropertySource`注解用于指定测试类或测试方法运行时加载的属性源（Property Source）。它允许在测试环境中指定一组属性值，这些属性值可以在测试过程中被注入到Spring容器中的Bean中。这个注解通常用于覆盖应用程序的默认配置，以便在测试中使用不同的配置。

```java
// @TestPropertySource(locations = "classpath:test.properties")
@TestPropertySource(properties = {
 "custom.ready=true",
 "custom.language=Chinese"
})
public class MyTest {
    // 测试方法...
}


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Repeatable(TestPropertySources.class)
public @interface TestPropertySource {
    @AliasFor("locations")
    String[] value() default {};

    @AliasFor("value")
    String[] locations() default {};

    boolean inheritLocations() default true;

    String[] properties() default {};

    boolean inheritProperties() default true;
}
```



### `@ContextConfiguration`

`@ContextConfiguration`注解用于指定加载Spring上下文的配置信息。它可以指定配置文件的位置、配置类的类型或其他Spring配置的相关信息。该注解通常与`@RunWith`或`@ExtendWith`注解一起使用，以便在测试中加载Spring上下文，并进行依赖注入等操作。

```java
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ContextConfiguration(classes = {AnnotationConfig.class})
public class MyTest {
    // 测试方法...
}


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ContextConfiguration {
    @AliasFor("locations")
    String[] value() default {};

    @AliasFor("value")
    String[] locations() default {};

    Class<?>[] classes() default {};

    Class<? extends ApplicationContextInitializer<?>>[] initializers() default {};

    boolean inheritLocations() default true;

    boolean inheritInitializers() default true;

    Class<? extends ContextLoader> loader() default ContextLoader.class;

    String name() default "";
}
```



### `@SpringJUnitConfig`

在Spring中，`@SpringJUnitConfig`注解用于指定JUnit测试类的配置信息，并启动Spring上下文以进行测试。它是`@RunWith`和`@ContextConfiguration`的组合注解（Spring 4中），提供了一种简化的方式来配置和运行Spring测试。

`@SpringJUnitConfig`注解可以用于加载Spring上下文的配置信息，包括指定配置类、配置文件或基于注解的配置。它可以替代使用`@RunWith(SpringJUnit4ClassRunner.class)`和`@ContextConfiguration`注解的组合，从而简化测试类的注解配置。

```java
@SpringJUnitConfig(classes = MyConfig.class)
public class MyTest {
    // 测试方法...
}

//Spring 5
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SpringJUnitConfig {

	/**
	 * Alias for {@link ContextConfiguration#classes}.
	 */
	@AliasFor(annotation = ContextConfiguration.class, attribute = "classes")
	Class<?>[] value() default {};

	/**
	 * Alias for {@link ContextConfiguration#classes}.
	 */
	@AliasFor(annotation = ContextConfiguration.class)
	Class<?>[] classes() default {};

	/**
	 * Alias for {@link ContextConfiguration#locations}.
	 */
	@AliasFor(annotation = ContextConfiguration.class)
	String[] locations() default {};

	/**
	 * Alias for {@link ContextConfiguration#initializers}.
	 */
	@AliasFor(annotation = ContextConfiguration.class)
	Class<? extends ApplicationContextInitializer<?>>[] initializers() default {};

	/**
	 * Alias for {@link ContextConfiguration#inheritLocations}.
	 */
	@AliasFor(annotation = ContextConfiguration.class)
	boolean inheritLocations() default true;

	/**
	 * Alias for {@link ContextConfiguration#inheritInitializers}.
	 */
	@AliasFor(annotation = ContextConfiguration.class)
	boolean inheritInitializers() default true;

	/**
	 * Alias for {@link ContextConfiguration#name}.
	 */
	@AliasFor(annotation = ContextConfiguration.class)
	String name() default "";

}
```





### `@SpringBootTest`

`@SpringBootTest`注解是Spring Boot提供的用于测试的注解。它可以用于启动完整的Spring Boot应用程序上下文，并提供了一些方便的功能，例如自动配置、自动注入等。该注解通常用于集成测试，以测试整个应用程序的行为。

```java
@SpringBootTest
public class MyTest {
    // 测试方法...
}

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@ExtendWith({SpringExtension.class})
public @interface SpringBootTest {
    @AliasFor("properties")
    String[] value() default {};

    @AliasFor("value")
    String[] properties() default {};

    String[] args() default {};

    Class<?>[] classes() default {};

    WebEnvironment webEnvironment() default SpringBootTest.WebEnvironment.MOCK;

    public static enum WebEnvironment {
        MOCK(false),
        RANDOM_PORT(true),
        DEFINED_PORT(true),
        NONE(false);

        private final boolean embedded;

        private WebEnvironment(boolean embedded) {
            this.embedded = embedded;
        }

        public boolean isEmbedded() {
            return this.embedded;
        }
    }
}
```

