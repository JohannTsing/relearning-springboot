package com.johann.iocaop.bean_config.clazz;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Repository;

/**
 * @author Johann
 * @version 1.0
 * @see
 **/
@Configuration
/**
 * @ComponentScan 注解指定了类扫描的包名，作用与 <context:component-scan/> 类似。
 * 如果 @Configuration 没有指定扫描的基础包路径或者类，默认就从该配置类的包开始扫描。
 * 请注意: <context:component-scan>元素具有annotation-config属性；然而，这个注释没有。
 * 这是因为在几乎所有情况下，当使用@ComponentScan时，都会假设默认的注释配置处理（例如处理@Autowired和friends）。
 * 此外，当使用AnnotationConfigApplicationContext时，注释配置处理器始终是注册的，这意味着在@ComponentScan级别禁用它们的任何尝试都将被忽略。
 */
@ComponentScan(basePackages = "com.johann.iocaop.bean_config.clazz"
        // 在 @ComponentScan 中，includeFilters 和 excludeFilters 可以用来指定包含和排除的组件
        ,excludeFilters = @ComponentScan.Filter(Repository.class)
        ,includeFilters = @ComponentScan.Filter(Configuration.class)
)
// 通过 @Import 注解可以将其他配置类中的 Bean 导入到当前配置类中
//@Import({Foo.class,Bar.class,Baz.class})
// 通过 @ImportResource 注解可以将 XML 配置文件中定义的 Bean 导入到当前配置类中
@ImportResource("classpath:beans-config-annotation.xml")
public class ClazzConfig {

    @Bean
    //默认为 true,即懒加载
    @Lazy
    //默认为 "",即 singleton单例模式
    @Scope(scopeName = "singleton")
    // 如果不指定 beanname，那么beanname默认为方法名 fooCreatInstance
    public Foo fooCreatInstance(){
        return new Foo();
    }

    /**
     * 在 Java 配置类中指定 Bean 之间的依赖关系有两种方式:
     * 通过方法的参数注入依赖;
     * 或者直接调用类中带有 @Bean 注解的方法
     */

    /**
     * 需要重点说明的是，Spring Framework 针对 @Configuration 类中带有 @Bean 注解的方法通过
     * CGLIB（Code Generation Library）做了特殊处理，针对返回单例类型 Bean 的方法，调用多次返回的
     * 结果是一样的，并不会真的执行多次
     */

    @Bean
    // 通过方法的参数注入依赖;
    public Bar barCreatInstance(Foo foo){
        return new Bar(foo);
    }

    @Bean(name = "bazBean")
    // 直接调用类中带有 @Bean 注解的方法
    public Baz bazCreatInstance(){
        return new Baz(fooCreatInstance());
    }

}
