### `<bean></bean>`标签
`<bean></bean>`用于创建对象

#### `<bean></bean>`标签的属性
1. `id`属性：用于给Bean指定一个唯一的标识符，这个标识符可以用来在其他地方引用这个Bean。
2. `class`属性：用于指定Bean的完整类名。
3. `scope`属性：用于指定Bean的作用域，包括singleton【默认】、prototype、request、session、global session等。
4. `lazy-init`属性：用于指定是否延迟初始化Bean，即在第一次使用Bean时才进行初始化。【默认: false】
5. `init-method`属性：用于指定Bean初始化时要调用的方法名。
6. `destroy-method`属性：用于指定Bean销毁时要调用的方法名。
7. `autowire`属性：用于指定自动装配方式，包括byName、byType、constructor等。
8. `autowire-candidate`属性：用于指定是否参与自动装配。
9. `depends-on`属性：用于指定Bean依赖的其他Bean的id。
10. `parent`属性：用于指定该Bean的父Bean，继承父Bean的属性和配置。
11. `name`属性：用于指定Bean的名称，与`id`属性类似，但可以指定多个名称，以逗号、分号或空格分隔。
12. `alias`属性：用于为Bean指定别名，可以指定多个别名，以逗号、分号或空格分隔。
13. `factory-bean`属性：用于指定Bean工厂的名称，用于创建该Bean实例。
14. `factory-method`属性：用于指定Bean工厂的方法名，用于创建该Bean实例。
15. `primary`属性：用于指定Bean是否为首选Bean，当存在多个同类型的Bean时，优先选择该Bean。
16. `description`属性：用于为Bean提供描述信息。
17. `singleton`属性：用于指定是否强制使用单例模式，如果设置为`false`，则每次获取Bean都会创建一个新的实例。
18. `abstract`属性：用于指定该Bean是否为抽象Bean，如果设置为`true`，则不能被实例化。
19. `property`子标签：用于设置Bean的属性值，包括属性名称、属性值、属性类型等。
20. `profile`属性：用于指定Bean所属的环境，只有当前环境与Bean所属环境一致时，才会创建该Bean实例。

#### `<bean></bean>`标签的子标签
1. `<constructor-arg></constructor-arg>`：用于设置JavaBean的构造函数参数。
2. `<property></property>`：用于设置JavaBean的属性值。
3. `<qualifier></qualifier>`：用于指定自动装配时的限定符。
4. `<lookup-method></lookup-method>`：用于指定JavaBean中的查找方法。
5. `<replaced-method></replaced-method>`：用于指定JavaBean中的替换方法。
6. `<init-method></init-method>`：用于指定JavaBean的初始化方法。
7. `<destroy-method></destroy-method>`：用于指定JavaBean的销毁方法。
8. `<description></description>`：用于指定JavaBean的描述信息。
9. `<meta></meta>`：用于指定JavaBean的元数据信息。
10. `<property-placeholder></property-placeholder>`：用于指定属性占位符的值。

### `<constructor-arg></constructor-arg>`标签
`<constructor-arg></constructor-arg>`用于设置JavaBean的构造函数参数。

#### `<constructor-arg></constructor-arg>`标签的属性
1. `index`：表示构造函数参数的索引位置，从0开始计数。
2. `type`：表示构造函数参数的类型。如果存在多个相同类型的构造函数参数，可以使用该属性进行区分。
3. `name`：表示构造函数参数的名称。如果存在多个相同类型的构造函数参数，可以使用该属性进行区分。
4. `value`：表示构造函数参数的值。可以直接指定一个常量值，或者使用Spring的表达式语言进行计算。
5. `ref`：表示构造函数参数的引用。可以指定一个已经存在的JavaBean的名称，作为构造函数参数的值。

### `<property></property>`标签
`<property></property>`用于设置JavaBean的属性值。

#### `<property></property>`标签的属性
1. `name`：表示JavaBean的属性名称。
2. `value`：表示JavaBean的属性值。可以直接指定一个常量值，或者使用Spring的表达式语言进行计算。
3. `ref`：表示JavaBean的属性引用。可以指定一个已经存在的JavaBean的名称，作为JavaBean的属性值。

#### `<property></property>`标签的子标签
1. `<list></list>`：用于设置JavaBean的List类型属性值。
2. `<set></set>`：用于设置JavaBean的Set类型属性值。
3. `<map></map>`：用于设置JavaBean的Map类型属性值。
4. `<props></props>`：用于设置JavaBean的Properties类型属性值。
5`<ref></ref>`：用于设置JavaBean的引用类型属性值。
6`<bean></bean>`：用于设置JavaBean类型属性值。