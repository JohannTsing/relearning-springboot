Enables Spring's asynchronous method execution capability, similar to functionality found in Spring's `<task:*> XML namespace`.

To be used together with `@Configuration` classes as follows, enabling annotation-driven async processing for an entire Spring application context:

>   启用Spring的异步方法执行功能，类似于Spring的`<task:*>` XML命名空间中的功能。
>
>   要与`@Configuration`类一起使用，以启用整个Spring应用程序上下文的注解驱动的异步处理：

 ```java
@Configuration
@EnableAsync
public class AppConfig {

}
 ```

`MyAsyncBean` is a user-defined type with one or more methods annotated with either `Spring's @Async` annotation, the `EJB 3.1 @javax.ejb.Asynchronous` annotation, or any custom annotation specified via the annotation attribute. The aspect is added transparently for any registered bean, for instance via this configuration:

>   `MyAsyncBean`是一个用户定义的类型，其中一个或多个方法使用`Spring的@Async`注解、`EJB 3.1 @javax.ejb.Asynchronous`注解或通过注解属性指定的任何自定义注解进行了注解。该方面透明地添加到任何已注册的bean中，例如通过此配置：

```java
@Configuration
public class AnotherAppConfig {

    @Bean
    public MyAsyncBean asyncBean() {
        return new MyAsyncBean();
    }
}
```

By default, Spring will be searching for an associated thread pool definition: either a unique `org.springframework.core.task.TaskExecutor` bean in the context, or an` java.util.concurrent.Executor` bean named `taskExecutor` otherwise. If neither of the two is resolvable, a `org.springframework.core.task.SimpleAsyncTaskExecutor` will be used to process async method invocations. Besides, annotated methods having a void return type cannot transmit any exception back to the caller. By default, such uncaught exceptions are only logged.

>   默认情况下，Spring将搜索关联的线程池定义：要么是上下文中的唯一`org.springframework.core.task.TaskExecutor` bean，要么是名为`taskExecutor`的`java.util.concurrent.Executor` bean。如果这两者都无法解析，将使用`org.springframework.core.task.SimpleAsyncTaskExecutor`来处理异步方法调用。此外，具有void返回类型的注解方法无法将任何异常传递给调用者。默认情况下，这种未捕获的异常只会被记录。

To customize all this, implement `AsyncConfigurer` and provide:

* your own Executor through the `getAsyncExecutor()` method, and
* your own `AsyncUncaughtExceptionHandler` through the `getAsyncUncaughtExceptionHandler()` method.

NOTE: `AsyncConfigurer` configuration classes get initialized early in the application context bootstrap. If you need any dependencies on other beans there, make sure to declare them 'lazy' as far as possible in order to let them go through other post-processors as well.

>   要自定义所有这些，请实现`AsyncConfigurer`并提供以下内容：
>
>   -   通过`getAsyncExecutor()`方法提供自己的Executor，
>   -   通过`getAsyncUncaughtExceptionHandler()`方法提供自己的`AsyncUncaughtExceptionHandler`。

```java
@Configuration
@EnableAsync
public class AppConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(11);
        executor.setThreadNamePrefix("MyExecutor-");
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

If only one item needs to be customized, null can be returned to keep the default settings. Consider also extending from `AsyncConfigurerSupport` when possible.

>   如果只需要自定义一个项目，可以返回null以保持默认设置。在可能的情况下，还可以考虑从`AsyncConfigurerSupport`继承。

Note: In the above example the `ThreadPoolTaskExecutor` is not a fully managed Spring bean. Add the `@Bean` annotation to the `getAsyncExecutor() `method if you want a fully managed bean. In such circumstances it is no longer necessary to manually call the` executor.initialize()` method as this will be invoked automatically when the bean is initialized.
For reference, the example above can be compared to the following `Spring XML` configuration:

>   注意：在上面的示例中，`ThreadPoolTaskExecutor`不是一个完全受管理的Spring bean。如果您想要一个完全受管理的bean，请在`getAsyncExecutor()`方法上添加`@Bean`注解。在这种情况下，不再需要手动调用`executor.initialize()`方法，因为在bean初始化时会自动调用该方法。
>   供参考，上面的示例可以与以下`Spring XML`配置进行比较：

```xml
<beans>
 
    <task:annotation-driven executor="myExecutor" exception-handler="exceptionHandler"/>

    <task:executor id="myExecutor" pool-size="7-42" queue-capacity="11"/>

    <bean id="asyncBean" class="com.foo.MyAsyncBean"/>

    <bean id="exceptionHandler" class="com.foo.MyAsyncUncaughtExceptionHandler"/>

</beans>
```

The above `XML-based` and `JavaConfig-based` examples are equivalent except for the setting of the thread name prefix of the Executor; this is because the `<task:executor>` element does not expose such an attribute. This demonstrates how the `JavaConfig-based` approach allows for maximum configurability through direct access to actual componentry.

>   上述的基于XML和基于JavaConfig的示例是等效的，除了Executor的线程名称前缀的设置；这是因为`<task:executor>`元素没有暴露这样的属性。这证明了基于JavaConfig的方法如何通过直接访问实际的组件来实现最大的可配置性。

The mode attribute controls how advice is applied: If the mode is `AdviceMode.PROXY (the default)`, then the other attributes control the behavior of the proxying. Please note that proxy mode allows for interception of calls through the proxy only; local calls within the same class cannot get intercepted that way.

>   mode属性控制通知的应用方式：如果mode是`AdviceMode.PROXY（默认值）`，则其他属性来控制代理的行为。请注意，代理模式只允许通过代理拦截调用；同一类中的本地调用无法以这种方式拦截。

Note that if the mode is set to `AdviceMode.ASPECTJ`, then the value of the `proxyTargetClass` attribute will be ignored. Note also that in this case the `spring-aspects module JAR` must be present on the classpath, with compile-time weaving or load-time weaving applying the aspect to the affected classes. There is no proxy involved in such a scenario; local calls will be intercepted as well.

>   请注意，如果mode设置为`AdviceMode.ASPECTJ`，则将忽略`proxyTargetClass`属性的值。还要注意，在这种情况下，必须在类路径上存在`spring-aspects模块JAR`，并且使用编译时织入或加载时织入将该方面应用于受影响的类。在这种情况下，没有代理参与；本地调用也将被拦截。