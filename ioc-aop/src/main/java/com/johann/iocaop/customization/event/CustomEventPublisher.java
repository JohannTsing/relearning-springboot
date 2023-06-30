package com.johann.iocaop.customization.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * 发布自定义事件，实现ApplicationEventPublisherAware接口，重写setApplicationEventPublisher方法
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Component
public class CustomEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 在Spring容器启动时，Spring会自动扫描 CustomEventPublisher类，并调用setApplicationEventPublisher方法，
     * 将ApplicationEventPublisher类型的Bean注入到CustomEventPublisher中。
     *
     * 我们没有显式地定义 ApplicationEventPublisher 类型的Bean，但是Spring会自动为我们创建一个默认的
     * ApplicationEventPublisher 类型的Bean，并将其注入到所有实现了 ApplicationEventPublisherAware 接口的Bean中。
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 发布自定义事件
     * @params []
     * @return void
     */
    public void fire(){
        System.out.println("【自定义事件发布器】准备发布了自定义事件");
        CustomEvent event = new CustomEvent(this,"自定义事件xxx");
        applicationEventPublisher.publishEvent(event);
        System.out.println("【自定义事件发布器】发布了自定义事件："+event.getMessage());
    }
}
