### 切点的声明

在Spring AOP中，使用`@Pointcut`注解来声明一个切入点时，可以使用以下常用的PCD（Pointcut Designator切入点标识符）：

1. `execution`：匹配方法执行的连接点。可以使用通配符和正则表达式来指定方法的签名和参数。
   例如：`execution(* com.example.service.*.*(..))`表示匹配`com.example.service`包中所有类的所有方法。
2. `within`：匹配指定类型内的方法执行的连接点。
   例如：`within(com.example.service.*)`表示匹配`com.example.service`包中所有类的所有方法。
3. `this`：匹配当前AOP代理对象类型的连接点。
   例如：`this(com.example.service.SomeService)`表示匹配实现`SomeService`接口的代理对象的所有方法。
4. `target`：匹配当前目标对象类型的连接点。
   例如：`target(com.example.service.SomeService)`表示匹配实现`SomeService`接口的目标对象的所有方法。
5. `args`：匹配方法参数类型匹配指定类型的连接点。
   例如：`args(java.lang.String)`表示匹配接受一个`String`类型参数的方法。
6. `@target`：匹配当前目标对象类型有指定注解的连接点。（要求运行时的目标对象带有注解，这 个注解的 @Retention 是 RetentionPolicy.RUNTIME）
   例如：`@target(org.springframework.stereotype.Service)`表示匹配被`@Service`注解标记的目标对象的所有方法。
7. `@args`：匹配当前方法参数类型有指定注解的连接点。
   例如：`@args(org.springframework.web.bind.annotation.PathVariable)`表示匹配接受带有`@PathVariable`注解的参数的方法。
8. `@within`：匹配包含指定注解的类内的方法执行连接点。（要求被拦截的类上带有 @Retention 是 RetentionPolicy.CLASS 的注解）
   例如：`@within(org.springframework.stereotype.Service)`表示匹配包含`@Service`注解的类中的所有方法。
9. `@annotation`：匹配带有指定注解的连接点。
   例如：`@annotation(org.springframework.transaction.annotation.Transactional)`表示匹配带有`@Transactional`注解的方法。

>   `execution`是一个用于匹配方法执行连接点的指示器。它可以根据方法的签名、访问修饰符、返回类型、方法名和参数类型等信息来进行匹配。
>
>   `execution`表达式的一般形式如下：
>
>   ```text
>   execution(modifiers-pattern? return-type-pattern declaring-type-pattern? method-name-pattern(param-pattern) throws-pattern?)
>   execution(访问修饰符<可选> 返回类型 全限定类名<可选> 方法名(参数) 异常<可选>)
>   ```
>
>   其中，各部分的含义如下：
>
>   - `modifiers-pattern`（可选）：表示方法的访问修饰符，使用关键字 `public`、`protected`、`private` 或 `*`（表示任意修饰符）。
>   - `return-type-pattern`：表示方法的返回类型，使用类名、关键字 `void` 或 `*`（表示任意返回类型）。
>   - `declaring-type-pattern`（可选）：表示声明该方法的类名或包名+类名。
>   - `method-name-pattern`：表示方法的名称，可以使用通配符 `*` 匹配任意字符序列。
>   - `param-pattern`：表示方法的参数类型模式，使用类名、通配符 `*`（表示任意类型）或 `..`（表示任意多个参数）。
>   - `throws-pattern`（可选）：表示方法可能抛出的异常类型。
>
>   下面是一些示例，详细描述了`execution`指示器的不同用法：
>
>   - `execution(public void com.example.service.SomeService.doSomething())`：匹配 `SomeService` 类中的 `doSomething` 方法，方法无参数且返回类型为 `void`。
>   - `execution(* com.example.service.*.*())`：匹配 `com.example.service` 包下所有类的无参数方法。
>   - `execution(public * com.example.service.SomeService.*(..))`：匹配 `SomeService` 类中的所有公共方法，包括任意参数和任意返回类型。
>   - `execution(public String com.example.service.SomeService.*(String, ..))`：匹配 `SomeService` 类中以 `String` 作为第一个参数，后面可以是任意多个参数的方法，返回类型为 `String`。
>   - `execution(public * com.example.service.SomeService+.*(..))`：匹配 `SomeService` 类及其所有子类中的所有公共方法。
>   - `execution(public * com.example.service..*.*())`：匹配 `com.example.service` 包及其子包下所有类的无参数方法。
>
>   注意，`execution`指示器是用于匹配方法执行连接点的十分强大且灵活的工具，它允许根据需要进行细粒度的方法匹配，从而实现有针对性的切面逻辑。

这些是Spring AOP中常用的Pointcut Designator，可以根据具体需求选择合适的PCD来定义切入点。同时，还可以使用逻辑操作符（如`&&`、`||`、`!`）来组合多个PCD来创建更复杂的切入点表达式。

```java
package com.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExampleAspect {

    // 使用 execution 指示符匹配方法执行的连接点
    @Pointcut("execution(* com.example.service.*.*(..))")
    public void executionPointcut() {
    }

    // 使用 within 指示符匹配指定类型内的方法执行的连接点
    @Pointcut("within(com.example.service.*)")
    public void withinPointcut() {
    }

    // 使用 this 指示符匹配当前AOP代理对象类型的连接点
    @Pointcut("this(com.example.service.SomeService)")
    public void thisPointcut() {
    }

    // 使用 target 指示符匹配当前目标对象类型的连接点
    @Pointcut("target(com.example.service.SomeService)")
    public void targetPointcut() {
    }

    // 使用 args 指示符匹配方法参数类型匹配指定类型的连接点
    @Pointcut("args(java.lang.String)")
    public void argsPointcut() {
    }

    // 使用 @target 指示符匹配当前目标对象类型有指定注解的连接点
    @Pointcut("@target(org.springframework.stereotype.Service)")
    public void targetAnnotationPointcut() {
    }

    // 使用 @args 指示符匹配当前方法参数类型有指定注解的连接点
    @Pointcut("@args(org.springframework.web.bind.annotation.PathVariable)")
    public void argsAnnotationPointcut() {
    }

    // 使用 @within 指示符匹配包含指定注解的类型内的方法执行的连接点
    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void withinAnnotationPointcut() {
    }

    // 使用 @annotation 指示符匹配带有指定注解的连接点
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void annotationPointcut() {
    }
}
```