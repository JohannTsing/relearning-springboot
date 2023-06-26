package com.johann.iocaop.customization.shutdown;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;

/**
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
public class Hello implements Lifecycle {

    private boolean running = false;

    public String sayHello(){
        return running ? "Hello World!" : "Bye!";
    }

    /**
     * 启动此组件。
     * 如果组件已经在运行，则不应抛出异常。
     * 对于容器而言，这将向适用的所有组件传播启动信号。
     *
     * @see SmartLifecycle#isAutoStartup()
     */
    @Override
    public void start() {
        System.out.println("Context Started.");
        running = true;
    }

    /**
     * 停止此组件，通常以同步方式停止，以便在此方法返回时完全停止组件。
     * 如果需要异步停止行为，请考虑实现SmartLifecycle及其stop(Runnable)变体。
     * 请注意，此停止通知不能保证在销毁之前到达：在常规关闭时，Lifecycle bean将在传播一般销毁回调之前首先接收停止通知；
     * 但是，在上下文的生命周期内进行热刷新或中止刷新尝试时，将调用给定bean的销毁方法，而不考虑停止信号。
     * 如果组件未运行（尚未启动），则不应抛出异常。
     * 对于容器而言，这将向适用的所有组件传播停止信号。
     *
     * @see SmartLifecycle#stop(Runnable)
     * @see DisposableBean#destroy()
     */
    @Override
    public void stop() {
        System.out.println("Context Stopped.");
        running = false;
    }

    /**
     * 检查此组件当前是否正在运行。
     * 对于容器而言，只有所有适用的组件当前正在运行时，此方法才会返回true。
     *
     * @return 组件当前是否正在运行。
     */
    @Override
    public boolean isRunning() {
        return running;
    }
}
