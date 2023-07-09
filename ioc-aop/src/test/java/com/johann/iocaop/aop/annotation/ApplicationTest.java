package com.johann.iocaop.aop.annotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AOP单元测试
 * @author Johann
 * @version 1.0
 * @see
 **/
// JUnit 5 可以通过 @ExtendWith 注解来添加扩展，@ExtendWith(SpringExtension.class) 就添加了 Spring 的测试支持，
// @ContextConfiguration 注解指定了用来初始化 Spring 容器的配置类或配置文件。
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AnnotationConfig.class})
// 这个@SpringJUnitConfig可以代替上述两行注解
// @SpringJUnitConfig(classes = {AnnotationConfig.class})
public class ApplicationTest {

    @Autowired
    private MyService myService;

    @Autowired
    private SecondAspect secondAspect;

    /**
     * 在每个测试方法执行前后，都可以执行一些初始化和清理的逻辑：
     * 添加了 @BeforeEach 和 @AfterEach 的方法会分别在测试方法执行前后被 JUnit 执行；
     *
     * 如果要在所有测试方法执行前进行总的初始化，可以使用 @BeforeAll 注解，对应的还有所有测试方法执行后执行的 @AfterAll。
     */
    @BeforeEach
    public void setUp(){
        secondAspect.reset();
    }

    @Test
    @DisplayName("myService不为空")
    public void testNotEmpty(){
        assertNotNull(myService);
    }

    @Test
    @DisplayName("myService是否为NewInterface类型")
    public void testIntroduction(){
        assertTrue(myService instanceof NewInterface);
    }

    @Test
    @DisplayName("通知是否均已执行")
    public void testAdvice(){
        StringBuffer words = new StringBuffer("Test. ");
        String sentence = myService.doSomething(words);
        assertEquals("Test. Welcome to Spring! [1]", words.toString());
        assertEquals("Hello! Test. Welcome to Spring! [1] GoodBye! ", sentence);
    }

    @Test
    @DisplayName("说两句话，检查计数")
    public void testMultipleSpeaking(){
        assertEquals("Hello! Test. Welcome to Spring! [1] GoodBye! ",
                myService.doSomething(new StringBuffer("Test. ")));
        assertEquals("Hello! Test. Welcome to Spring! [2] GoodBye! ",
                myService.doSomething(new StringBuffer("Test. ")));
    }

}
