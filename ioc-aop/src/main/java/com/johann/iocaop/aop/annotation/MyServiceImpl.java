package com.johann.iocaop.aop.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Service("msi")
public class MyServiceImpl implements MyService{
    @Override
    public String doSomething(StringBuffer words) {
        return "Hello! "+words;
    }
}
