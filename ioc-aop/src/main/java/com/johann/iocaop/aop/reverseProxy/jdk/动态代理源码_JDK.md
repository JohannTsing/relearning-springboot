```java
@CallerSensitive
public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h) {
    Objects.requireNonNull(h);

    final Class<?> caller = System.getSecurityManager() == null
                                ? null
                                : Reflection.getCallerClass();

    /*
     * Look up or generate the designated proxy class and its constructor.
     * 用指定的调用处理程序调用其构造函数。
     */
    Constructor<?> cons = getProxyConstructor(caller, loader, interfaces);

    return newProxyInstance(caller, cons, h);
}
```
* @CallerSensitive 注解表示该方法对调用者敏感，可以根据调用者的上下文进行不同的处理。
* newProxyInstance 方法用于创建代理对象。它接受一个类加载器 loader、一个接口数组 interfaces 和一个 InvocationHandler 对象 h 作为参数。
* 首先，对 h 进行非空检查。
* 然后，通过 System.getSecurityManager() 方法获取安全管理器，如果安全管理器存在，则通过 Reflection.getCallerClass() 方法获取调用者的类。
* 接下来，调用 getProxyConstructor 方法查找或生成指定代理类及其构造函数。
* 最后，调用 newProxyInstance 方法创建代理对象，并返回。

```java
/**
 * 返回一个代理类的构造器对象，该对象需要一个InvocationHandler类型的单一参数，给定一个类加载器和一个接口数组。
 * 返回的构造函数将已经设置了可访问的标志。
 * 形参：
 *  caller - 如果设置了SecurityManager，则由面向公众的@CallerSensitive方法传递，如果没有SecurityManager，则为null 
 *  loader - 定义代理类的类加载器 
 *  interfaces - 代理类要实现的接口列表
 * 返回值：
 *  代理类的构造函数，接收单个InvocationHandler参数。
 */
private static Constructor<?> getProxyConstructor(Class<?> caller,
                                                  ClassLoader loader,
                                                  Class<?>... interfaces) {
    // optimization for single interface 对单一接口的优化
    if (interfaces.length == 1) {
        Class<?> intf = interfaces[0];
        if (caller != null) {
            checkProxyAccess(caller, loader, intf);
        }
        return proxyCache.sub(intf).computeIfAbsent(
            loader,
            (ld, clv) -> new ProxyBuilder(ld, clv.key()).build()
        );
    } else {
        // interfaces cloned 克隆的接口
        final Class<?>[] intfsArray = interfaces.clone();
        if (caller != null) {
            checkProxyAccess(caller, loader, intfsArray);
        }
        final List<Class<?>> intfs = Arrays.asList(intfsArray);
        return proxyCache.sub(intfs).computeIfAbsent(
            loader,
            (ld, clv) -> new ProxyBuilder(ld, clv.key()).build()
        );
    }
}
```
* getProxyConstructor 方法用于查找或生成代理类的构造函数。
* 如果只有一个接口，进行优化处理：
  * 获取接口 intf。
  * 如果调用者 caller 不为空，则调用 checkProxyAccess 方法检查调用者是否有权限访问代理类。
  * 从 proxyCache 缓存中获取代理类的构造函数，如果缓存中不存在，则使用 ProxyBuilder 类生成代理类并缓存。
* 如果有多个接口，进行如下处理：
  * 克隆接口数组 interfaces，得到 intfsArray。
  * 如果调用者 caller 不为空，则调用 checkProxyAccess 方法检查调用者是否有权限访问接口数组。
  * 将接口数组转换为列表 intfs。
  * 从 proxyCache 缓存中获取代理类的构造函数，如果缓存中不存在，则使用 ProxyBuilder 类生成代理类并缓存。

```java
private static void checkProxyAccess(Class<?> caller,
                                     ClassLoader loader,
                                     Class<?> ... interfaces) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
        ClassLoader ccl = caller.getClassLoader();
        if (loader == null && ccl != null) {
            sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
        }
        ReflectUtil.checkProxyPackageAccess(ccl, interfaces);
    }
}
```
* checkProxyAccess 方法用于检查调用者是否有权限访问代理类。
* 首先，获取系统安全管理器 sm。
* 如果安全管理器不为空，则进行权限检查：
  * 获取调用者的类加载器 ccl。
  * 如果传入的类加载器 loader 为空，并且调用者的类加载器 ccl 不为空，则通过 sm.checkPermission 方法检查获取类加载器的权限。
  * 调用 ReflectUtil.checkProxyPackageAccess 方法检查调用者的类加载器 ccl 是否有权限访问代理类的包。

```java
private static Object newProxyInstance(Class<?> caller, // null if no SecurityManager
                                       Constructor<?> cons,
                                       InvocationHandler h) {
    /*
     * Invoke its constructor with the designated invocation handler.
     * 用指定的调用处理程序调用其构造函数。
     */
    try {
        if (caller != null) {
            checkNewProxyPermission(caller, cons.getDeclaringClass());
        }

        return cons.newInstance(new Object[]{h});
    } catch (IllegalAccessException | InstantiationException e) {
        throw new InternalError(e.toString(), e);
    } catch (InvocationTargetException e) {
        Throwable t = e.getCause();
        if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        } else {
            throw new InternalError(t.toString(), t);
        }
    }
}
```
* newProxyInstance 方法用于通过代理类的构造函数创建代理对象。
* 首先，如果调用者 caller 不为空，则调用 checkNewProxyPermission 方法检查调用者是否有权限创建代理类。
* 然后，使用代理类的构造函数 cons 和指定的 InvocationHandler 对象 h 创建代理对象。
* 如果在创建代理对象的过程中出现异常，将其捕获并抛出相应的异常。

```java
private static void checkNewProxyPermission(Class<?> caller, Class<?> proxyClass) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
        if (ReflectUtil.isNonPublicProxyClass(proxyClass)) {
            ClassLoader ccl = caller.getClassLoader();
            ClassLoader pcl = proxyClass.getClassLoader();

            // do permission check if the caller is in a different runtime package
            // of the proxy class
			// 如果调用者在代理类的不同运行时包中，做权限检查
            String pkg = proxyClass.getPackageName();
            String callerPkg = caller.getPackageName();

            if (pcl != ccl || !pkg.equals(callerPkg)) {
                sm.checkPermission(new ReflectPermission("newProxyInPackage." + pkg));
            }
        }
    }
}
```
* checkNewProxyPermission 方法用于检查调用者是否有权限创建代理类。
* 首先，获取系统安全管理器 sm。
* 如果安全管理器不为空，并且代理类 proxyClass 是非公共的代理类，则进行权限检查：
  * 获取调用者的类加载器 ccl 和代理类的类加载器 pcl。
  * 获取代理类的运行时包名 pkg 和调用者的运行时包名 callerPkg。
  * 如果调用者的类加载器与代理类的类加载器不同，或者运行时包名不同，则通过 sm.checkPermission 方法进行权限检查，检查权限为 "newProxyInPackage." + pkg。
