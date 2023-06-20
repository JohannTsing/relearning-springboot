### 1, Spring核心包

#### 1.1, spring-core 
该包是Spring框架的核心，提供了Spring框架的基本组成部分，如IoC和DI容器、资源管理、类型转换、事件机制等。其他所有的Spring模块都依赖于该模块。

#### 1.2, spring-beans【依赖spring-core】
该包提供了Spring框架的BeanFactory和相关的接口和类，用于管理和创建对象实例。BeanFactory是Spring框架的核心容器，负责创建、管理和销毁Bean对象。
该包还提供了一些工具类，如BeanUtils、PropertyEditorRegistrar等。

#### 1.3, spring-aop【依赖spring-core, spring-beans】
该包提供了Spring框架的面向切面编程（AOP）的实现，包括AOP框架、切面、通知、切点等。
AOP是一种编程范式，可以将横切关注点（如日志、事务、安全等）从应用程序的主要业务逻辑中分离出来，从而提高代码的可重用性和可维护性。

#### 1.4, spring-context【依赖spring-core, spring-beans, spring-aop, spring-expression】
该包是Spring框架的上下文模块，提供了许多与Spring应用程序上下文相关的类和接口。 Spring应用程序上下文是一个配置信息的集合，
用于管理Bean对象的生命周期、依赖注入、事件发布等。 该包还提供了一些实用工具类，如ResourceLoader、ApplicationEventPublisher等。

### 2, Spring容器(应用上下文)的继承

| 容器的继承                                             | Java类继承                                     |
|:--------------------------------------------------|:--------------------------------------------|
| 子上下文可以看到父上下文中定义的 Bean，反之则不行                       | 子类可以看到父类的 protected 和 public 属性和方法，父类看不到子类的 |
| 子上下文中可以定义与父上下文同 ID 的 Bean，各自都能获取自己定义的 Bean(不会被覆盖) | 子类可以覆盖父类定义的属性和方法                            |

### 3, 依赖注入(DI)
依赖注入（Dependency Injection）是Spring框架中的一个重要概念，它是指在创建对象时，通过外部传递依赖对象的方式来实现对象之间的解耦。
具体来说，依赖注入是指将一个对象所依赖的其他对象的引用，通过构造函数、Setter方法、字段或接口等方式注入到该对象中，从而实现对象之间的协作。
这样做的好处是可以降低对象之间的耦合度，提高代码的可维护性和可测试性。在Spring框架中，依赖注入是通过IoC容器来实现的。

#### 3.1, 构造器注入(Constructor Injection)
通过构造函数将依赖项传递给Bean。这种方式要求Bean必须有一个或多个构造函数，每个构造函数都接受一个或多个依赖项作为参数。
Spring会自动解析依赖项并将它们传递给构造函数。

- 适用场景
`构造器注入`适用于需要注入的依赖关系比较固定，且在对象创建时就需要初始化的情况。
例如，一个对象依赖于另一个对象的实例，并且这个依赖关系在对象创建时就已经确定，那么就可以使用构造器注入的方式来实现依赖注入。

- 示例: [构造器注入xml配置](constructor-injection-beans.xml)

#### 3.2, Setter方法注入(Setter Injection)
通过Setter方法将依赖项传递给Bean。这种方式要求Bean必须有一个或多个Setter方法，每个Setter方法都接受一个或多个依赖项作为参数。

- 适用场景
`setter方法注入`适用于需要在Bean创建之后动态设置依赖关系的场景。
- 例如，需要在运行时根据某些条件来设置依赖关系，或者需要在Bean创建之后才能获取到依赖的对象等情况。

- 示例: [Setter注入xml配置](setter-injection-beans.xml)

#### 3.3, 字段注入(Field Injection)

#### 3.4, 接口注入(Interface Injection)

#### 3.5, 自动装配(Automatic Wiring)
自动装配是指Spring框架自动将Bean之间的依赖关系注入到Bean中，而不需要手动配置。
Spring框架提供了三种自动装配的方式，分别是`byName`、`byType`和`constructor`。

在Spring framework中，`<bean></bean>`标签中的`autowire属性`用于指定自动装配的方式。
`autowire属性`有以下几种取值：
1. `no`：【默认值】，表示不进行自动装配，需要手动指定依赖关系。
2. `byName`：根据属性名自动装配，要求属性名和依赖的bean的名称一致。
3. `byType`：根据属性类型自动装配，要求属性类型和依赖的bean的类型一致。
4. `constructor`：根据构造函数参数类型自动装配，要求构造函数参数类型和依赖的bean的类型一致。

> 注意: 
> 1, 开启自动装配后，仍可以手动设置依赖，手动设置的依赖优先级高于自动装配；
> 2, 自动织入无法注入基本类型和字符串，不支持循环依赖；
> 3, 对于集合类型的属性，自动织入会把上下文里找到的 Bean 都放进去，但如果属性不是集合类型，有多个候选 Bean 就会有问题。
> 
> 如果有多个候选Bean, Spring会抛出异常，提示找到多个候选Bean，无法确定要注入哪个Bean。
> 此时，可以将 <bean/> 的 autowire-candidate 属性设置为 false，
> 也可以在你所期望的候选 Bean 的 <bean/> 中将 primary 设置为 true，这样 Spring 就会优先选择这个 Bean。

- 示例: [自动装配xml配置](autowire-injection-beans.xml)