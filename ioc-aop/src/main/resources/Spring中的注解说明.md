### Bean创建相关的注解
|注解|说明|
|----|----|
|@Component|用于标注一个类为Spring容器管理的组件，通常用于标注业务层、持久层等组件。|
|@Service|用于标注一个类为业务层组件，通常用于标注业务层组件。|
|@Repository|用于标注一个类为持久层组件，通常用于标注数据访问层组件。一般是 DAO（Data Access Object）层，即访问数据库的类。|
|@Controller|用于标注一个类为控制层组件，通常用于标注控制层组件。|
|@RestController|用于标注一个类为RESTful风格的控制层组件，通常用于标注控制层组件。|
|@Configuration|用于标注一个类为Spring的配置类，通常用于定义Spring的Bean。|
|@Bean|用于标注一个方法为Spring的Bean，通常用于在@Configuration注解的类中定义Bean。|
|@Autowired|用于实现自动装配，将Spring容器中的Bean自动注入到需要的地方。|
|@Qualifier|用于指定自动装配时的Bean名称，当存在多个同类型的Bean时，可以使用@Qualifier注解来指定需要注入的Bean的名称。|
|@Value|用于注入配置文件中的属性值，可以用于注入基本类型、String类型、数组、List、Map等数据类型。|
|@Scope|用于指定Bean的作用域，包括singleton、prototype、request、session等作用域。|
|@PostConstruct|用于标注一个方法在Bean创建完成后需要执行的操作，通常用于初始化操作。|
|@PreDestroy|用于标注一个方法在Bean销毁前需要执行的操作，通常用于清理资源。|
|@Lazy|用于指定Bean的延迟加载，只有在需要使用时才会创建Bean。|
|@Conditional|用于根据条件来判断是否创建Bean，可以根据配置文件中的属性、系统环境变量等条件来判断是否创建Bean。|

### 依赖注入相关的注解
以下是将给定文本转换为Markdown表格的结果：

| 注解名称 | 注解的说明 |
| --- | --- |
| @Autowired | 自动装配，可用于构造方法、属性、方法和参数上，Spring会自动寻找匹配的Bean进行注入。 |
| @Resource | 与@Autowired类似，也是进行自动装配，但是可以指定具体的Bean名称进行注入。 |
| @Inject | 与@Autowired类似，也是进行自动装配，但是是Java EE 6引入的标准注解，需要在pom.xml中引入javax.inject依赖。 |
| @Qualifier | 指定具体的Bean名称进行注入，与@Autowired一起使用。 |
| @Value | 注入属性值，可以用于基本类型、String、数组、集合等类型的属性。 |
| @Lazy | 延迟初始化，只有在第一次使用时才会进行Bean的创建。 |
| @Primary | 当有多个Bean满足自动装配条件时，优先选择被@Primary标注的Bean进行注入。 |
| @DependsOn | 指定Bean的创建依赖关系，即指定某些Bean需要在当前Bean之前创建。 |
| @Import | 引入其他配置类，可以将其他配置类中的Bean引入到当前配置类中。 |
| @Profile | 根据不同的环境选择不同的Bean进行注入，可以在配置类或Bean上使用。 |
| @Conditional | 根据某个条件选择是否创建Bean，可以在配置类或Bean上使用。 |
