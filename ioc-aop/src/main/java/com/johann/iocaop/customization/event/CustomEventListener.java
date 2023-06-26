package com.johann.iocaop.customization.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 通过注解@EventListener，来监听自定义事件，CustomEvent事件
 * @author Johann
 * @version 1.0
 * @see
 **/
@Component
public class CustomEventListener {

    @EventListener
    public void onEvent(CustomEvent event){
        System.out.println("【@EventListener注解监听器】 CustomEvent事件被监听到了。。。");
        System.out.println("【CustomEvent事件】："+event.getMessage());
    }
}
