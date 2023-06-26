package com.johann.iocaop.customization.event;

import org.springframework.context.ApplicationEvent;

/**
 * 创建自定义事件，继承ApplicationEvent类
 * @author Johann
 * @version 1.0
 * @see
 **/
public class CustomEvent extends ApplicationEvent {

    private String message;
    public CustomEvent(Object source,String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
