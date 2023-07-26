### 循环依赖简介
循环依赖是指两个或多个Bean之间相互依赖，形成一个循环的依赖关系。当存在循环依赖时，Spring容器在初始化Bean时会遇到困难，因为它无法确定先初始化哪个Bean，以及如何解决它们之间的相互依赖关系。

### Spring三级缓存
在 Spring 中，三级缓存是一种用于解决 Bean 之间循环依赖问题的机制。三级缓存是 Spring 容器工厂使用的一种缓存机制。它使用三级缓存模式来解决循环依赖问题。

三级缓存指的是：
- singletonObjects： 这是第一级缓存，存储已实例化和初始化的单例对象。
- earlySingletonObjects： 这是第二级缓存，存储已实例化但尚未初始化的单例对象。
- singletonFactories： 这是第三级缓存，存储用于创建单例对象的单例工厂。

创建一个 Bean 时，首先会在第一级缓存中进行检查。如果在第一层缓存中没有找到，则在第二层缓存中进行检查。如果仍未找到，则在第三级缓存中检查。如果在这些缓存中都没有找到，那么就会创建一个新的 Bean 实例。

三级缓存机制允许 Spring 通过创建一个部分初始化的 Bean 并将其存储在二级缓存中，直到所有依赖关系都得到解决，从而处理 Bean 之间的循环依赖关系。
一旦所有依赖关系都得到解决，Bean 就会被完全初始化，并移动到第一级缓存供将来使用。

- [Spring 的循环依赖，源码详细分析 → 真的非要三级缓存?](https://www.cnblogs.com/youzhibing/p/14337244.html#autoid-4-0-0)
- [再探循环依赖 → Spring 是如何判定原型循环依赖和构造方法循环依赖的？](https://www.cnblogs.com/youzhibing/p/14514823.html)
- [三探循环依赖 → 记一次线上偶现的循环依赖问题](https://www.cnblogs.com/youzhibing/p/15835048.html)
- [四探循环依赖 → 当循环依赖遇上 BeanPostProcessor，爱情可能就产生了！](https://www.cnblogs.com/youzhibing/p/15908602.html)
