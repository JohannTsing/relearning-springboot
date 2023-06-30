### 1, Spring核心包

#### 1.1, spring-core 
该包是Spring框架的核心，提供了Spring框架的基本组成部分，如IoC和DI容器、资源管理、类型转换、事件机制等。其他所有的Spring模块都依赖于该模块。

#### 1.2, spring-beans【依赖spring-core】
该包提供了Spring框架的BeanFactory和相关的接口和类，用于管理和创建对象实例。BeanFactory是Spring框架的核心容器，负责创建、管理和销毁Bean对象。
该包还提供了一些工具类，如BeanUtils、PropertyEditorRegistrar等。

#### 1.3, spring-aop【依赖spring-core, spring-beans】
该包提供了Spring框架的面向切面编程（AOP）的实现，包括AOP框架、切面、通知、切点等。
AOP是一种编程范式，可以将横切关注点（如日志、事务、安全等）从应用程序的主要业务逻辑中分离出来，从而提高代码的可重用性和可维护性。

#### 1.4, spring-context【依赖spring-core, spring-beans, spring-aop, spring-expression】
该包是Spring框架的上下文模块，提供了许多与Spring应用程序上下文相关的类和接口。 Spring应用程序上下文是一个配置信息的集合，
用于管理Bean对象的生命周期、依赖注入、事件发布等。 该包还提供了一些实用工具类，如ResourceLoader、ApplicationEventPublisher等。

### 2, Spring容器(应用上下文)的继承

| 容器的继承                                             | Java类继承                                     |
|:--------------------------------------------------|:--------------------------------------------|
| 子上下文可以看到父上下文中定义的 Bean，反之则不行                       | 子类可以看到父类的 protected 和 public 属性和方法，父类看不到子类的 |
| 子上下文中可以定义与父上下文同 ID 的 Bean，各自都能获取自己定义的 Bean(不会被覆盖) | 子类可以覆盖父类定义的属性和方法                            |

### 3, 依赖注入(DI)
依赖注入（Dependency Injection）是Spring框架中的一个重要概念，它是指在创建对象时，通过外部传递依赖对象的方式来实现对象之间的解耦。
具体来说，依赖注入是指将一个对象所依赖的其他对象的引用，通过构造函数、Setter方法、字段或接口等方式注入到该对象中，从而实现对象之间的协作。
这样做的好处是可以降低对象之间的耦合度，提高代码的可维护性和可测试性。在Spring框架中，依赖注入是通过IoC容器来实现的。

#### 3.1, 构造器注入(Constructor Injection)
通过构造函数将依赖项传递给Bean。这种方式要求Bean必须有一个或多个构造函数，每个构造函数都接受一个或多个依赖项作为参数。
Spring会自动解析依赖项并将它们传递给构造函数。

- 适用场景
`构造器注入`适用于需要注入的依赖关系比较固定，且在对象创建时就需要初始化的情况。
例如，一个对象依赖于另一个对象的实例，并且这个依赖关系在对象创建时就已经确定，那么就可以使用构造器注入的方式来实现依赖注入。

- 示例: [构造器注入xml配置](beans-dependency-constructor-injection.xml)

#### 3.2, Setter方法注入(Setter Injection)
通过Setter方法将依赖项传递给Bean。这种方式要求Bean必须有一个或多个Setter方法，每个Setter方法都接受一个或多个依赖项作为参数。

- 适用场景
`setter方法注入`适用于需要在Bean创建之后动态设置依赖关系的场景。
- 例如，需要在运行时根据某些条件来设置依赖关系，或者需要在Bean创建之后才能获取到依赖的对象等情况。

- 示例: [Setter注入xml配置](beans-dependency-setter-injection.xml)

#### 3.3, 字段注入(Field Injection)

#### 3.4, 接口注入(Interface Injection)

#### 3.5, 自动装配(Automatic Wiring)
自动装配是指Spring框架自动将Bean之间的依赖关系注入到Bean中，而不需要手动配置。
Spring框架提供了三种自动装配的方式，分别是`byName`、`byType`和`constructor`。

在Spring framework中，`<bean></bean>`标签中的`autowire属性`用于指定自动装配的方式。
`autowire属性`有以下几种取值：
1. `no`：【默认值】，表示不进行自动装配，需要手动指定依赖关系。
2. `byName`：根据属性名自动装配，要求属性名和依赖的bean的名称一致。
3. `byType`：根据属性类型自动装配，要求属性类型和依赖的bean的类型一致。
4. `constructor`：根据构造函数参数类型自动装配，要求构造函数参数类型和依赖的bean的类型一致。

> 注意: 
> 1, 开启自动装配后，仍可以手动设置依赖，手动设置的依赖优先级高于自动装配；
> 2, 自动织入无法注入基本类型和字符串，不支持循环依赖；
> 3, 对于集合类型的属性，自动织入会把上下文里找到的 Bean 都放进去，但如果属性不是集合类型，有多个候选 Bean 就会有问题。
> 
> 如果有多个候选Bean, Spring会抛出异常，提示找到多个候选Bean，无法确定要注入哪个Bean。
> 此时，可以将 <bean/> 的 autowire-candidate 属性设置为 false，
> 也可以在你所期望的候选 Bean 的 <bean/> 中将 primary 设置为 true，这样 Spring 就会优先选择这个 Bean。

- 示例: [自动装配xml配置](beans-dependency-autowire-injection.xml)

### 4, Bean的配置方式

#### 4.1, 基于XML的配置方式

#### 4.2, 基于注解的配置方式

#### 4.3, 基于Java的配置方式

### 5, Bean的作用域(Scope)

#### 5.1, 单例(Singleton)

#### 5.2, 原型(Prototype)

#### 5.3, 请求(Request)

#### 5.4, 会话(Session)

#### 5.5, 全局会话(Global Session)

### 6, 定制容器与 Bean 的行为

#### 6.1, Bean的生命周期

Spring中的Bean生命周期包括以下阶段：
1. 实例化：当Spring容器启动时，它会根据配置文件或注解创建Bean的实例。
2. 属性赋值【注入依赖】：在实例化后，Spring容器会将Bean的属性值注入到Bean实例中。
3. 初始化：在属性赋值完成后，Spring容器会调用Bean的初始化方法，可以通过实现 InitializingBean 接口或在配置文件中指定init-method来定义初始化方法。
4. 使用：初始化完成后，Bean可以被应用程序使用。
5. 销毁：当Spring容器关闭时，它会调用Bean的销毁方法，可以通过实现DisposableBean接口或在配置文件中指定destroy-method来定义销毁方法。

总的来说，Bean的生命周期可以概括为实例化、属性赋值【注入依赖】、初始化、使用和销毁。在这个过程中，Spring容器会负责管理Bean的生命周期，确保Bean在应用程序中正确地被创建、初始化、使用和销毁。

#### 6.2, Bean的初始化方法和销毁方法
可以通过以下三种方式，定制Bean初始化和销毁方法，它们的优先级顺序如序号所示
1. @PostConstruct和@PreDestroy注解【JSR-250】；
2. 实现InitializingBean和DisposableBean接口；
3. 在<bean></bean>中指定init-method和destroy-method属性。（@Bean中配置的initMethod或destroyMethod）

- 示例: [Bean的初始化和销毁方法](beans-lifecycle.xml)

#### 6.3, Bean的后置处理器
Bean的后置处理器是Spring框架中的一个重要组件，它可以在Bean实例化、属性注入和初始化完成后进行一些额外的处理操作。后置处理器可以对Bean进行修改、增强或者替换，从而实现一些特定的功能。

Spring框架中提供了两个常用的后置处理器接口：BeanPostProcessor和BeanFactoryPostProcessor。

BeanPostProcessor接口定义了两个方法：postProcessBeforeInitialization和postProcessAfterInitialization。
这两个方法分别在Bean初始化之前和之后被调用，可以对Bean进行修改或者增强。

BeanFactoryPostProcessor接口定义了一个方法：postProcessBeanFactory。
这个方法在BeanFactory加载Bean定义之后、初始化Bean之前被调用，可以对Bean定义进行修改或者增强。

通过实现这些后置处理器接口，我们可以在Spring框架中实现一些高级的功能，比如AOP、事务管理等。

- 示例: [Bean的后置处理器](com/johann/iocaop/customization/postProcessor/)

#### 6.4, Aware接口
Spring中的Aware接口是一组接口，它们可以让Bean感知到Spring容器的存在，从而获取Spring容器中的一些资源或者进行一些操作。

Aware接口是一个标记接口，它没有任何方法，只是用来标识具有特定功能的接口。

Spring中的Aware接口有以下几种：
1. 【ApplicationContextAware】：让Bean获取ApplicationContext对象，从而可以获取Spring容器中的其他Bean。
2. BeanNameAware：让Bean获取自己在Spring容器中的名称。
3. BeanFactoryAware：让Bean获取BeanFactory对象，从而可以获取Spring容器中的其他Bean。
4. EnvironmentAware：让Bean获取Environment对象，从而可以获取Spring应用程序的环境变量和属性。
5. ResourceLoaderAware：让Bean获取ResourceLoader对象，从而可以加载资源文件。
6. ServletContextAware：让Bean获取ServletContext对象，从而可以获取Web应用程序的上下文信息。
7. MessageSourceAware：让Bean获取MessageSource对象，从而可以获取国际化信息。
8. 【ApplicationEventPublisherAware】：让Bean获取ApplicationEventPublisher对象，从而可以发布事件。
9. LoadTimeWeaverAware：让Bean获取LoadTimeWeaver对象，从而可以进行类加载时的增强。
10. EmbeddedValueResolverAware：让Bean获取EmbeddedValueResolver对象，从而可以解析占位符。
11. ConversionServiceAware：让Bean获取ConversionService对象，从而可以进行类型转换。
12. ResourceLoaderAware：让Bean获取ResourceLoader对象，从而可以加载资源文件。

#### 6.4, 事件机制
Spring中的事件机制是指，当某个事件发生时，Spring会自动通知所有对该事件感兴趣的监听器，从而让监听器可以进行相应的处理。
事件机制是Spring框架中非常重要的一个特性，它可以让不同的组件之间进行松耦合的通信，从而提高系统的可扩展性和可维护性。

ApplicationContext 提供了一套事件机制，在容器发生变动时我们可以通过 ApplicationEvent 的子类通知到 ApplicationListener 接口的实现类，做对应的处理。
例如，ApplicationContext 在启动、停止、关闭和刷新 a 时，分别会发出 ContextStartedEvent、ContextStoppedEvent、ContextClosedEvent
和 ContextRefreshedEvent 事件，这些事件就让我们有机会感知当前容器的状态。

- 示例: [事件机制](com/johann/iocaop/customization/event/)

#### 6.5, 优雅地关闭容器
Java 进程在退出时，我们可以通过 Runtime.getRuntime().addShutdownHook() 方法添加一些钩子，在关闭进程时执行特定的操作。
如果是 Spring 应用，在进程退出时也要能正确地执行一些清理的方法。

ConfigurableApplicationContext 接口扩展自 ApplicationContext，其中提供了一个 registerShut-downHook()。
AbstractApplicationContext 类实现了该方法，正是调用了前面说到的 Runtime.get-Runtime().addShutdownHook()，并且在其内部调用了 doClose() 方法。

设想在生产代码里有这么一种情况：一个Bean通过 ApplicationContextAware 注入了Application-Context，业务代码根据逻辑判断从
ApplicationContext 中取出对应名称的 Bean，再进行调用；问题出现在应用程序关闭时，容器已经开始销毁 Bean 了，可是这段业务代码还在执行，
仍在继续尝试从容器中获取 Bean，而且代码还没正确处理此处的异常……这该如何是好？

针对这种情况，我们可以借助 Spring Framework 提供的 Lifecycle 来感知容器的启动和停止，容器会将启动和停止的信号传播给实现了该接口的组件和上下文。

- 示例: [优雅地关闭容器](com/johann/iocaop/customization/shutdown/ShutdownApplication.java)

### 7, 容器中的抽象

#### 7.1, 环境抽象
Spring中代表程序运行环境的 Environment 接口包含两个关键信息：Profile 和 Properties

##### 7.1.1, Profile 抽象
Spring中的Profile是一种机制，用于根据不同的环境或条件，选择不同的配置文件或bean定义。
它可以让开发人员在不同的环境中使用不同的配置，例如开发、测试、生产等环境。
通过使用Profile，可以轻松地切换不同的配置，而不需要手动修改代码或配置文件。

Profile抽象是Spring框架中的一个接口，它定义了一些方法，用于获取当前激活的Profile。
如果使用 XML 进行配置，可以在 <beans/> 的 profile 属性中进行设置。
如果使用 Java 类的配置方式，可以在带有 @Configuration 注解的类上，或者在带有 @Bean 注解的方法上添加 @Profile 注解，并在其中指定该配置生效的具体 Profile。
```java
@Configuration
@Profile("dev")
public class DevConfig {
 @Bean
 public Hello hello() {
 Hello hello = new Hello();
 hello.setName("dev");
 return hello;
 }
}

@Configuration
@Profile("test")
public class TestConfig {
 @Bean
 public Hello hello() {
 Hello hello = new Hello();
 hello.setName("test");
 return hello;
 }
}
```

通过如下两种方式可以指定要激活的 Profile（多个 Profile 用逗号分隔）:
* ConfigurableEnvironment.setActiveProfiles() 方法指定要激活的 Profile（通过 Application-Context.getEnvironment() 方法可获得 Environment）；
* spring.profiles.active 属性指定要激活的 Profile。

例如，在启动应用程序时，可以通过如下方式指定要激活的 Profile：`java -Dspring.profiles.active="dev" -jar xxx.jar`

Spring Framework 还提供了默认的 Profile，一般名为 default，但也可以通过 Configurable-Environment.setDefaultProfiles() 和 spring.profiles.default 来修改这个名称。

##### 7.1.2, PropertySource 抽象
Spring中的PropertySource抽象是一个接口，它定义了一些方法，用于获取属性值。它是Spring框架中的一个核心概念，用于管理应用程序的配置信息。
PropertySource抽象可以从不同的来源获取属性值，例如系统属性、环境变量、配置文件等。

在Spring中，可以通过在配置文件中使用@PropertySource注解来指定属性源，从而获取属性值。
PropertySource抽象还可以与其他Spring框架中的组件一起使用，例如Environment、PropertyResolver等。

在 Spring 中，一般属性用小写单词表示并用点分隔，比如 foo.bar，如果是从环境变量中获取属性，会按照 foo.bar、foo_bar、FOO.BAR 和 FOO_BAR 的顺序来查找。
```java
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }
}

```

@Value 注解，也能获取属性，获取不到时则返回默认值：
```java
public class Hello {
 @Value("${foo.bar:NONE}") // :后是默认值
 private String value;
 
 public void hello() {
 System.out.println("foo.bar: " + value);
 }
}
```
${} 占位符可以出现在 Java 类配置或 XML 文件中，Spring 容器会试图从各种已经配置了的来源中解析属性。

要添加属性来源，可以在 @Configuration 类上增加 @PropertySource 注解，例如：
```java
@Configuration
@PropertySource(value = "classpath:/META-INF/resources/app.properties",ignoreResourceNotFound = true)
public class Config {
    
}
```
如果使用 XML 进行配置，可以像下面这样：
`<context:property-placeholder location="classpath:/META-INF/resources/app.properties" ignore-resource-not-found="true"  order="1"/>`

通常我们的预期是一定能找到需要的属性，但也有这个属性可有可无的情况，这时将注解的 ignoreResourceNotFound 或者 XML 文件的 ignore-resource-not-found 设置为 true 即可。
如果存在多个配置，则可以通过 @Order 注解或 XML 文件的 order 属性来指定顺序。

> Spring Framework是如何实现占位符解析的?
> 
> Spring Framework实现占位符解析的主要方式是通过PropertyPlaceholderConfigurer类。
> PropertyPlaceholderConfigurer 是一个 BeanFactoryPostProcessor，它可以在BeanFactory加载BeanDefinition之前，对BeanDefinition中的占位符进行解析。
> 
> 如果使用 <context:property-placeholder/>，Spring Framework 会自动注册一个 PropertySourcesPlaceholderConfigurera。
> 如果是 Java 配置，则需要我们自己用 @Bean 来注册一个。
> 
> PropertyPlaceholderConfigurer 可以从多个属性源中获取属性值，例如系统属性、环境变量、配置文件等。
> 在解析占位符时，它会按照一定的优先级顺序查找属性值，直到找到第一个非空值为止。
```java
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
 
    // 注意，这里的 PropertySourcesPlaceholderConfigurer 需要使用 static
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("application.properties"));
        return configurer;
    }
 
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("${db.driver}");
        dataSource.setUrl("${db.url}");
        dataSource.setUsername("${db.username}");
        dataSource.setPassword("${db.password}");
        return dataSource;
    }
}
``` 

#### 7.2, 任务抽象

##### 7.2.1, TaskExecutor 抽象
在Spring中，`TaskExecutor`是一个接口，定义了执行异步任务的方法，可以用来发送邮件、短信、推送等。

- 示例：[TaskExecutor的使用](com/johann/iocaop/customization/task/executor)

`TaskExecutor`的实现：
1. `SimpleAsyncTaskExecutor`：一个简单的TaskExecutor实现，每次执行任务时都会创建一个新的线程来处理任务了，但不会重用线程。适用于执行时间较短的异步任务。。
2. `SyncTaskExecutor`：一个同步的TaskExecutor实现，同步执行任务，不使用任何线程池，直接在调用线程中执行任务。
3. `ConcurrentTaskExecutor`：一个基于线程池的TaskExecutor实现，可以配置核心线程数、最大线程数、等待队列等参数。
4. `ThreadPoolTaskExecutor`：一个更高级的基于线程池的TaskExecutor实现，可以配置核心线程数、最大线程数、等待队列、线程池关闭时的等待时间等参数，适用于执行时间较长的异步任务。
5. `SimpleThreadPoolTaskExecutor`：一个简单的基于线程池的TaskExecutor实现，可以配置核心线程数、最大线程数、等待队列等参数。
6. `WorkManagerTaskExecutor`：一个基于Java EE WorkManager的TaskExecutor实现，可以在Java EE容器中使用。
7. `TimerTaskExecutor`：一个基于Java Timer的TaskExecutor实现，可以在Java EE容器中使用。

使用`<bean></bean>`标签配置一个任务执行器: 
```xml
<!-- 配置一个线程池任务执行器 -->
<bean id="taskExecutor" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
    <!-- 核心线程数，线程池维护的最小线程数 -->
    <property name="corePoolSize" value="4"/>
    <!-- 最大线程数，线程池维护的最大线程数 -->
    <property name="maxPoolSize" value="8"/>
    <!-- 队列容量，用于缓存等待执行的任务的队列 -->
    <property name="queueCapacity" value="32"/>
    <!-- 拒绝策略，当线程池已满，无法继续执行任务时的处理方式 -->
    <property name="rejectedExecutionHandler">
        <bean class="java.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy"/>
    </property>
    <!-- 线程名前缀，用于区分不同的线程池 -->
    <property name="threadNamePrefix" value="taskExecutor-"/>
    <!-- 是否等待所有任务执行完毕再关闭线程池 -->
    <property name="waitForTasksToCompleteOnShutdown" value="true"/>
    <!-- 等待所有任务执行完毕的超时时间 -->
    <property name="awaitTerminationSeconds" value="60"/>
</bean>
```
或者使用`<task:executor/>`标签进行配置
```xml
<!--当使用`<task:executor/>`进行配置时，默认使用的是`ThreadPoolTaskExecutor`实现 -->
<task:executor id="taskExecutor" pool-size="4-8" queue-capacity="32" rejection-policy="CALLER_RUNS"/>
```

> 1. `核心线程数`和`最大线程数`的区别？
> 
> `核心线程数`是线程池中最少的线程数，也是线程池中一直存在的线程数。当有新的任务提交到线程池时，如果当前线程数小于核心线程数，
> 那么线程池会创建新的线程来处理任务。如果当前线程数等于或大于核心线程数，那么线程池会将任务放入等待队列中，等待空闲线程来处理任务。
> 
> `最大线程数`是线程池中最多的线程数，它限制了线程池中的线程数量。当等待队列已满且当前线程数小于最大线程数时，线程池会创建新的线程来处理任务。
> 如果当前线程数已经等于或大于最大线程数，那么线程池会根据拒绝策略来处理任务。
> 
> 这两个配置项之间的区别在于，核心线程数是线程池中一直存在的线程数，而最大线程数是线程池中最多的线程数。
> 
> ThreadPoolTaskExecutor 有一个 keepAliveSeconds 的属性，通过它可以调整空闲状态线程的存活时间。
> 如果当前线程数大于核心线程数，到存活时间后就会清理线程。
> 
> 
> 2. 线程池的拒绝策略
> 
> 线程池的等待队列是一个先进先出的队列，用于存储等待执行的任务。当线程池中的所有线程都在执行任务时，新的任务会被放入等待队列中，
> 直到有空闲的线程可用来执行任务。线程池的等待队列可以是有界的或无界的，有界队列可以避免任务过多导致内存溢出，但可能会导致任务被拒绝执行。
> 无界队列可以保证所有任务都能被执行，但可能会导致内存占用过高。
> 
> Java中，线程池的等待队列默认是无界队列，即容量为 Integer.MAX_VALUE。这意味着线程池可以接受任意数量的任务，但也可能导致内存占用过高。
> 如果需要限制等待队列的容量，可以使用有界队列，例如 ArrayBlockingQueue 或 LinkedBlockingQueue。
> 
> 在Java中，线程池的“队列满”时的处理策略由拒绝策略（RejectedExecutionHandler）来控制。
> 当线程池中的等待队列已满，且所有线程都在执行任务时，新的任务将被拒绝执行，并根据拒绝策略进行处理。
> 
> Java中提供了四种拒绝策略：
> * `AbortPolicy`（默认）：直接抛出 RejectedExecutionException 异常，阻止系统正常运行。
> * `CallerRunsPolicy`：只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。显然这样做不会真的丢弃任务，但是，任务提交线程的性能极有可能会急剧下降。
> * `DiscardOldestPolicy`：丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务。
> * `DiscardPolicy`：直接丢弃任务，不予任何处理也不抛出异常。

在配置好了 TaskExecutor 后，可以直接调用它的 execute() 方法，传入一个 Runnable 对象；
也 可以在方法上使用 @Async 注解，这个方法可以是空返回值，也可以返回一个 Future ：
```java
@Service
public class AsyncService {
    /**
     * 使用`@Async`注解标注了一个异步方法`asyncMethod()`，并指定了使用名为`taskExecutor`的`ThreadPoolTaskExecutor`来执行异步任务。
     * 当我们调用`asyncMethod()`方法时，Spring会自动将方法调用转换为异步任务，并使用`taskExecutor`来执行任务。
     */
    @Async("taskExecutor")
    public void asyncMethod() {
        // 异步方法体
    }
}
```

在使用`@Async`注解时，需要在配置类上增加`@EnableAsync`注解，或者在XML文件中增加`<task:annotation-driven/>`配置，开启对它的支持。
```java
@Configuration
@EnableAsync
public class AppConfig {
    // 配置其他bean
}
```
或
```
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:task="http://www.springframework.org/schema/task"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/task
                           http://www.springframework.org/schema/task/spring-task.xsd">

    <!-- 配置其他bean -->
    <!-- 自定义AsyncUncaught-ExceptionHandler -->
    <bean id="exceptionHandler" class="com.foo.MyAsyncUncaughtExceptionHandler"/>
    
    <!-- 开启对@Async注解的支持 -->
    <task:annotation-driven executor="myExecutor" exception-handler="exceptionHandler"/>

    <task:executor id="myExecutor" pool-size="7-42" queue-capacity="11"/>
</beans>
```

> 注意：
> 
> 对于异步执行的方法，由于在触发时主线程就返回了，我们的代码在遇到异常时可能根本无法感知，而且抛出的异常也不会被捕获，
> 因此最好我们能自己实现一个 AsyncUncaught-ExceptionHandler 对象来处理这些异常，最起码打印一个异常日志，方便问题排查。
> - 示例: [自定义AsyncUncaught-ExceptionHandler](@EnableAsync源代码.md)

```java
import org.springframework.context.annotation.Bean;

@Configuration
@EnableAsync
public class AppConfig implements AsyncConfigurer {

    @Override
    //@Bean("taskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(11);
        executor.setThreadNamePrefix("MyExecutor-");
        // 没有@Bean注解，需要手动初始化。且要注意 @Async 注解中的value值要与此处的beanName一致
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }
}

public class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        // 在这里可以自定义异常处理逻辑，比如打印日志或发送通知等
        System.out.println("Async method: " + method.getName() + " threw an exception: " + throwable.getMessage());
    }
}
```



##### 7.2.2, TaskScheduler 抽象

- 示例：[TaskScheduler的使用](com/johann/iocaop/customization/task/scheduler)
- 
Spring中的`TaskScheduler`是一个用于调度任务的接口，它可以在指定的时间间隔或者固定的时间点执行任务。

`TaskScheduler`接口提供了多种方法来实现不同的调度策略，例如`schedule(Runnable task, Trigger trigger)`方法可以在指定的时间点执行任务，
`scheduleAtFixedRate(Runnable task, long period)`方法可以按照固定的时间间隔执行任务，
`scheduleWithFixedDelay(Runnable task, long delay)`方法可以在任务执行完成后等待一段时间再执行下一次任务。

`TaskScheduler`接口的实现：
1. `ThreadPoolTaskScheduler`：基于线程池的`TaskScheduler`实现，可以在指定的时间间隔内执行任务。
2. `ConcurrentTaskScheduler`：基于`ScheduledExecutorService`的`TaskScheduler`实现，可以在指定的时间间隔内执行任务。
3. `SimpleThreadPoolTaskScheduler`：基于线程池的`TaskScheduler`实现，可以在指定的时间间隔内执行任务。与`ThreadPoolTaskScheduler`不同的是，它使用`SimpleAsyncTaskExecutor`作为默认的`TaskExecutor`实现。
4. `TimerTaskScheduler`：基于`java.util.Timer`的`TaskScheduler`实现，可以在指定的时间间隔内执行任务。
5. `CustomizableThreadFactory`：一个可定制的线程工厂，可以用于创建自定义的线程池。

使用`<bean></bean>`标签配置一个调度任务:
```xml
<!-- 使用`<bean></bean>`标签配置一个任务调度器 -->
<bean id="taskScheduler" class="org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler">
    <!-- 设置线程池大小为10 -->
    <property name="poolSize" value="10"/>
    <!-- 设置线程名称前缀为myScheduler- -->
    <property name="threadNamePrefix" value="myScheduler-"/>
    <!-- 设置线程池关闭时等待所有任务完成 -->
    <property name="waitForTasksToCompleteOnShutdown" value="true"/>
    <!-- 设置线程池关闭时等待的最长时间 -->
    <property name="awaitTerminationSeconds" value="60"/>
</bean>
```
或者使用`<task:scheduler/>` 来配置 TaskScheduler
```xml
<task:scheduler id="taskScheduler" pool-size="10" />
```

### 8, AOP
AOP（面向切面编程）是一种编程范式，它可以帮助我们更好地组织和管理代码。在传统的面向对象编程中，我们将功能按照对象的职责划分到不同的类中，但是有些功能可能会在多个类中重复出现，导致代码冗余和难以维护。

AOP的思想是将这些重复的功能抽取出来，形成一个独立的模块，称为切面。切面可以包含一些通用的代码，比如日志记录、性能监控、事务管理等。然后，我们可以通过在代码中定义一些特殊的标记，来告诉程序在哪些地方应用这个切面。

当程序运行到被标记的地方时，AOP会自动将切面的功能插入到代码中，实现对应的功能。这样，我们就可以在不修改原有代码的情况下，给程序添加一些额外的功能。

#### 8.1, AOP中的核心概念:
1. 切面（aspect）：切面是AOP中的一个核心概念，它是一个独立的模块，包含了一些通用的功能代码。比如，日志记录、性能监控、事务管理等。 切面可以被应用到多个类或方法中，以实现这些功能的复用。
2. 连接点（join point）：连接点是指在程序执行过程中，可以插入切面的特定位置。比如，方法的调用、方法的执行、异常的抛出等。 连接点是AOP中的具体执行点，它是切面可以插入的地方。
3. 通知（advice）：通知是切面中具体的功能代码，它定义了在连接点处执行的操作。通知可以在连接点之前、之后或者环绕连接点执行。 比如，我们可以在方法执行之前记录日志，或者在方法执行之后进行性能监控。
4. 切入点（pointcut）：切入点是用来定义一些条件，即哪些连接点应该被切面所应用。切入点可以通过表达式或者注解来定义。 比如，我们可以定义一个切入点，表示所有的方法调用都应该被切面所应用，或者只有某些特定的方法才会被切面所应用。

#### 8.2, Spring AOP的实现原理

