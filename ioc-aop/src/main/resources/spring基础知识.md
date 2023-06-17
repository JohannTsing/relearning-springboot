### Spring核心包

#### spring-core 
该包是Spring框架的核心，提供了Spring框架的基本组成部分，如IoC和DI容器、资源管理、类型转换、事件机制等。其他所有的Spring模块都依赖于该模块。

#### spring-beans【依赖spring-core】
该包提供了Spring框架的BeanFactory和相关的接口和类，用于管理和创建对象实例。BeanFactory是Spring框架的核心容器，负责创建、管理和销毁Bean对象。
该包还提供了一些工具类，如BeanUtils、PropertyEditorRegistrar等。

#### spring-aop【依赖spring-core, spring-beans】
该包提供了Spring框架的面向切面编程（AOP）的实现，包括AOP框架、切面、通知、切点等。
AOP是一种编程范式，可以将横切关注点（如日志、事务、安全等）从应用程序的主要业务逻辑中分离出来，从而提高代码的可重用性和可维护性。

#### spring-context【依赖spring-core, spring-beans, spring-aop, spring-expression】
该包是Spring框架的上下文模块，提供了许多与Spring应用程序上下文相关的类和接口。 Spring应用程序上下文是一个配置信息的集合，
用于管理Bean对象的生命周期、依赖注入、事件发布等。 该包还提供了一些实用工具类，如ResourceLoader、ApplicationEventPublisher等。

### Spring容器(应用上下文)的继承

| 容器的继承                                             | Java类继承                                     |
|:--------------------------------------------------|:--------------------------------------------|
| 子上下文可以看到父上下文中定义的 Bean，反之则不行                       | 子类可以看到父类的 protected 和 public 属性和方法，父类看不到子类的 |
| 子上下文中可以定义与父上下文同 ID 的 Bean，各自都能获取自己定义的 Bean(不会被覆盖) | 子类可以覆盖父类定义的属性和方法                            |

### 依赖注入(DI)
依赖注入（Dependency Injection）是Spring框架中的一个重要概念，它是指在创建对象时，通过外部传递依赖对象的方式来实现对象之间的解耦。
具体来说，依赖注入是指将一个对象所依赖的其他对象的引用，通过构造函数、Setter方法、字段或接口等方式注入到该对象中，从而实现对象之间的协作。
这样做的好处是可以降低对象之间的耦合度，提高代码的可维护性和可测试性。在Spring框架中，依赖注入是通过IoC容器来实现的。

