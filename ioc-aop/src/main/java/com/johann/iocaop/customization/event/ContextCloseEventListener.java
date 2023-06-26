package com.johann.iocaop.customization.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 通过实现ApplicationListener接口，来监听ContextClosedEvent事件
 * @author Johann
 * @version 1.0
 * @see
 **/
@Component
// 通过@Order注解，来指定监听器的执行顺序，值越小，越先执行
@Order(2)
public class ContextCloseEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("【实现ApplicationListener接口】 ContextClosedEvent事件被监听到了。。。");
    }
}
