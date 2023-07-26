Enables Spring's scheduled task execution capability, similar to functionality found in Spring's `<task:*>` XML namespace. To be used on `@Configuration` classes as follows:

>   启用Spring的计划任务执行能力，类似于Spring的`<task:*>` XML命名空间中的功能。要在@Configuration类上使用，如下：

```java
@Configuration
@EnableScheduling
public class AppConfig {

  // various @Bean definitions
}
```

This enables detection of `@Scheduled` annotations on any Spring-managed bean in the container. For example, given a class `MyTask`:

>   这使得容器中任何Spring管理的Bean上的@Scheduled注释都能被检测到。例如，给定一个类MyTask：

```java
package com.myco.tasks;
 
public class MyTask {

  @Scheduled(fixedRate=1000)
  public void work() {
      // task execution logic
  }
}
```

the following configuration would ensure that `MyTask.work()` is called once every 1000 ms:

>   以下配置将确保MyTask.work()每1000毫秒被调用一次：

```java
@Configuration
@EnableScheduling
public class AppConfig {

  @Bean
  public MyTask task() {
      return new MyTask();
  }
}
```

Alternatively, if `MyTask` were annotated with `@Component`, the following configuration would ensure that its `@Scheduled` method is invoked at the desired interval:

>   另外，如果MyTask被@Component注释，下面的配置将确保它的@Scheduled方法在所需的时间间隔被调用：

```java
@Configuration
@EnableScheduling
@ComponentScan(basePackages="com.myco.tasks")
public class AppConfig {
}
```

Methods annotated with `@Scheduled` may even be declared directly within `@Configuration` classes:

>   带有@Scheduled注释的方法甚至可以直接在@Configuration类中声明：

```java
@Configuration
@EnableScheduling
public class AppConfig {

  @Scheduled(fixedRate=1000)
  public void work() {
      // task execution logic
  }
}
```

By default, Spring will search for an associated scheduler definition: either a unique `org.springframework.scheduling.TaskScheduler` bean in the context, or a `TaskScheduler` bean named "taskScheduler" otherwise; the same lookup will also be performed for a `java.util.concurrent.ScheduledExecutorService` bean. If neither of the two is resolvable, a local single-threaded default scheduler will be created and used within the registrar.

>   默认情况下，Spring会搜索相关的调度器定义：要么是上下文中唯一的org.springframework.scheduling.TaskScheduler Bean，要么是名为 "taskScheduler "的TaskScheduler Bean；对于java.util.concurrent.ScheduledExecutorService Bean也会进行同样的查找。如果这两者都不能解决，那么将创建一个本地单线程的默认调度器，并在注册中心内使用。

When more control is desired, a `@Configuration` class may implement `SchedulingConfigurer`. This allows access to the underlying `ScheduledTaskRegistrar` instance. For example, the following example demonstrates how to customize the `Executor` used to execute scheduled tasks:

>   当需要更多控制时，@Configuration类可以实现SchedulingConfigurer。这允许访问底层的ScheduledTaskRegistrar实例。例如，下面的例子演示了如何定制用于执行计划任务的Executor：

```java
@Configuration
@EnableScheduling
public class AppConfig implements SchedulingConfigurer {

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
      taskRegistrar.setScheduler(taskExecutor());
  }

  @Bean(destroyMethod="shutdown")
  public Executor taskExecutor() {
      return Executors.newScheduledThreadPool(100);
  }
}
```

Note in the example above the use of `@Bean(destroyMethod="shutdown")`. This ensures that the task executor is properly shut down when the Spring application context itself is closed.

>   注意上面的例子中使用了@Bean(destroyMethod="shutdown")。这确保了当Spring应用上下文本身关闭时，任务执行器被正确关闭。

Implementing `SchedulingConfigurer` also allows for fine-grained control over task registration via the `ScheduledTaskRegistrar`. For example, the following configures the execution of a particular bean method per a custom `Trigger` implementation:

>   实现SchedulingConfigurer还允许通过ScheduledTaskRegistrar对任务注册进行细粒度的控制。例如，下面的内容是根据自定义触发器的实现来配置执行一个特定的Bean方法：

```java
@Configuration
@EnableScheduling
public class AppConfig implements SchedulingConfigurer {

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
      taskRegistrar.setScheduler(taskScheduler());
      taskRegistrar.addTriggerTask(
          () -> myTask().work(),
          new CustomTrigger()
      );
  }

  @Bean(destroyMethod="shutdown")
  public Executor taskScheduler() {
      return Executors.newScheduledThreadPool(42);
  }

  @Bean
  public MyTask myTask() {
      return new MyTask();
  }
}
```

For reference, the example above can be compared to the following Spring XML configuration:

>   作为参考，上面的例子可以与下面的Spring XML配置进行比较：

```xml
<beans>
 
  <task:annotation-driven scheduler="taskScheduler"/>

  <task:scheduler id="taskScheduler" pool-size="42"/>

  <task:scheduled-tasks scheduler="taskScheduler">
      <task:scheduled ref="myTask" method="work" fixed-rate="1000"/>
  </task:scheduled-tasks>

  <bean id="myTask" class="com.foo.MyTask"/>

</beans>
```

The examples are equivalent save that in XML a `fixed-rate` period is used instead of a custom `Trigger` implementation; this is because the `task: `namespace `scheduled` cannot easily expose such support. This is but one demonstration how the code-based approach allows for maximum configurability through direct access to actual componentry.

>   除了在XML中使用`fixed-rate`周期而不是自定义触发器的实现之外，这些例子都是等效的；这是因为task:namespace的计划不容易暴露这种支持。这只是一个示范，基于代码的方法如何通过直接访问实际组件来实现最大的可配置性。

Note: `@EnableScheduling` applies to its local application context only, allowing for selective scheduling of beans at different levels. Please redeclare `@EnableScheduling` in each individual context, e.g. the common root web application context and any separate `DispatcherServlet` application contexts, if you need to apply its behavior at multiple levels.

>   注意： @EnableScheduling只适用于它的本地应用上下文，允许在不同层次上对Bean进行选择性的调度。如果你需要在多个级别上应用它的行为，请在每个单独的上下文中重新声明@EnableScheduling，例如，共同的根Web应用上下文和任何单独的DispatcherServlet应用上下文。