package com.johann.iocaop.aop.annotation;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author Johann
 * @version 1.0
 * @see
 **/
@Component
@Aspect
@Order(1)
@Log4j2
public class MyAspect {

    /**
     * 前置通知
     * @param words
     */
    @Before("target(com.johann.iocaop.aop.annotation.MyService+) && args(words)")
    public void addWords(StringBuffer words){
        log.info(this.getClass().getName()+": addWords前置通知, args: "+words);
        words.append("Welcome to Spring! ");
    }
}
