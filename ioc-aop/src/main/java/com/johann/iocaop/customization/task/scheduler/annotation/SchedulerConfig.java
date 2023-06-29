package com.johann.iocaop.customization.task.scheduler.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

/**
 * @Description: SchedulerConfig
 * @Auther: Johann
 * @Version: 1.0
 */
@Configuration
@ComponentScan("com.johann.iocaop.customization.task.scheduler")
@EnableScheduling
/**
 * SchedulingConfigurer接口说明：
 *
 * Optional interface to be implemented by @Configuration classes annotated with @EnableScheduling.
 * Typically used for setting a specific TaskScheduler bean to be used when executing scheduled tasks or for registering
 * scheduled tasks in a programmatic fashion as opposed to the declarative approach of using the @Scheduled annotation.
 * For example, this may be necessary when implementing Trigger-based tasks, which are not supported by the @Scheduled annotation.
 * <p>See {@link EnableScheduling @EnableScheduling} for detailed usage examples.
 *
 * 可选接口，由使用@EnableScheduling注解的@Configuration类实现。
 * 通常用于在执行定时任务时设置特定的TaskScheduler bean，或以编程方式注册定时任务，而不是使用@Scheduled注解的声明性方法。
 * 例如，当实现基于触发器的任务时，这可能是必要的，因为@Scheduled注解不支持此类任务。
 * 有关详细使用示例，请参阅@EnableScheduling。
 */
public class SchedulerConfig implements SchedulingConfigurer {
    /**
     * Callback allowing a TaskScheduler and specific Task instances to be registered against the given the ScheduledTaskRegistrar.
     * 允许将TaskScheduler和特定的Task实例注册到给定的ScheduledTaskRegistrar的回调函数。
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
        taskRegistrar.setScheduler(myScheduler());
        taskRegistrar.addTriggerTask(
                ()-> System.out.println("执行动态定时任务"+System.currentTimeMillis()),
                triggerContext -> {
                    // 定时任务的触发规则
                    CronTrigger cronTrigger = new CronTrigger("0/8 * * * * ?");
                    return cronTrigger.nextExecutionTime(triggerContext);
                });
    }

    @Bean
    public TaskScheduler myScheduler(){
        // 创建一个任务调度器
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

}
