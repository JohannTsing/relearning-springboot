package com.johann.iocaop.customization.postProcessor;

import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Bean后置处理器
 * @author Johann
 * @version 1.0
 * @see
 **/
public class HelloBeanPostProcessor implements BeanPostProcessor {

        public HelloBeanPostProcessor() {
            System.out.println("HelloBeanPostProcessor构造方法");
        }

    /**
     * 在Bean的初始化方法之前执行
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * @return
     */
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) {
            if ("postProcessor-hello".equals(beanName)){
                System.out.println("HelloBeanPostProcessor.postProcessBeforeInitialization");
            }
            return bean;
        }

    /**
     * 在Bean的初始化方法之后执行
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * @return
     */
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) {
            if ("postProcessor-hello".equals(beanName)){
                System.out.println("HelloBeanPostProcessor.postProcessAfterInitialization");
            }
            return bean;
        }
}
