package com.johann.iocaop.aop.annotation;

import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Component("msi")
public class MyServiceImpl implements MyService{
    @Override
    public String doSomething(StringBuffer words) {
        return "Hello! "+words;
    }
}
