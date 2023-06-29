package com.johann.iocaop.customization.task.executor.annotation;

import com.johann.iocaop.customization.task.executor.MyAsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Configuration
@ComponentScan("com.johann.iocaop.customization.task.executor")
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer {

    /**
     * 处理异步方法调用时要使用的Executor实例
     */
    @Override
    @Bean("clazz-taskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(11);
        executor.setThreadNamePrefix("MyExecutor-");
        // 没有@Bean注解，需要手动初始化。且 要注意 @Async 注解中的value值
        // executor.initialize();
        return executor;
    }

    /**
     * 在返回类型为void的异步方法执行期间引发异常时要使用的AsyncUnaughtException Handler实例
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }
}
