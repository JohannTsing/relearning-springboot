package com.johann.iocaop.customization.event;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 通过注解@EventListener，来监听ContextClosedEvent事件
 * @author Johann
 * @version 1.0
 * @see
 **/
@Component
public class ContextClosedEventAnnotationListener {

    // 通过注解@EventListener，来监听ContextClosedEvent事件
    @EventListener
    // 通过@Order注解，来指定监听器的执行顺序，值越小，越先执行
    @Order(1)
    public void onEvent(ContextClosedEvent event){
        System.out.println("【@EventListener注解监听器】 ContextClosedEvent事件被监听到了。。。");
    }
}
