package com.johann.iocaop.customization.event;

import org.springframework.stereotype.Component;

/**
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Component("event-hello")
public class Hello {
    public String sayHello(){
        return "Hello World!";
    }
}
