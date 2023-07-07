package com.johann.iocaop.aop.annotation;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
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
@Order(2)
@Log4j2
public class SecondAspect {

    /**
     * 引入新的接口
     * 引入（Introduction）是一种特殊的通知，它为现有的类添加新的方法或属性。Spring允许我们向现有的类添加新的接口（而不是实现）。
     */
    @DeclareParents(value = "com.johann.iocaop.aop.annotation.MyService+",defaultImpl = NewInterfaceImpl.class)
    private NewInterface newInterface;
    private int counter = 0;

    /**
     * 前置通知
     * 通过execution表达式指定切入点, 通过args指定参数,对参数进行操作
     * @param words
     */
    @Before("execution(public !static * do*(..)) && args(words)")
    public void addCounter(StringBuffer words){
        log.info(this.getClass().getName()+": addCounter前置通知, args: "+words);
        words.append("[" + ++counter + "]");
    }

    /**
     * 环绕通知
     * 通过execution表达式指定切入点, 通过this指定目标对象,对目标对象进行操作
     * @param pjp
     * @param newInterface
     * @return
     * @throws Throwable
     */
    @Around("execution(public * doSomething(..)) && this(newInterface)")
    public String addSay(ProceedingJoinPoint pjp,NewInterface newInterface) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return pjp.proceed() + newInterface.todoNewSomething();
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("Total time: " + (end - start) + "ms");
        }
    }

    public int getCounter() {
        return counter;
    }

    public void reset(){
        counter = 0;
    }

}
