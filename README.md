***SSM***
---
[toc]

---
# 1. Spring

分层的Java SE应用全栈轻量级开发框架

- 内核：**IoC反转控制 AoP面向切面编程**

- 体系结构：
![体系结构](https://tse1-mm.cn.bing.net/th/id/OIP-C.Qq2w50khDS0_FxtMdsANBAHaFd?pid=ImgDet&rs=1)

**IoC 反转控制**

把对象的 创建 赋值 管理 交给 **代码之外的容器** 来完成（外部资源）

> **容器** ：服务器软件，Spring框架就是容器
> 
> **Java中创建对象的方式** ：
>   1. 构造方法
>   2. 反射
>   3. 序列化
>   4. 克隆
>   5. IoC 容器创建对象
>   6. 动态代理

**IoC的技术实现： DI**

DI（Dependency Injection）依赖注入，其底层使用的是反射机制

---

## 1.1 简单使用

1. 创建Maven项目 maven-quickstart
2. 在pom.xml中导入Spring依赖

```xml
<dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
```

3. 创建自定义类，以 `App1` 为例
4. 在 Resources 包下新建Spring配置文件，文件名自定义，以 `beans.xml`为例
5. 在该配置文件添加需要创建的对象（即 `bean` ） 其中属性： `id`为自定义唯一标识符， `class` 为自定义类的全名

```xml
<bean id="t1" class="hzx.App1" />
```

6. 在测试类中使用Spring创建对象

```java
@Test
    public void f() {
        // 1. 指定Spring配置文件名称
        String config="beans.xml";
        // 2. 创建容器
        ApplicationContext ac=new ClassPathXmlApplicationContext(config);
        // 3. 获取对象 getBean("id")方法
        App1 app1=(App1) ac.getBean("t1");
        // 4. 使用对象
        app1.func();
    }
```

> 在获取Spring容器时，Spring将会一次性创建配置文件中的所有对象 

> **获取容器对象信息的api**
```java
String conf="beans.xml";
ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
/* 获取对象数量 */
int cnt=ac.getBeanDefinitionCount();
System.out.println("> cnt: "+cnt);
/* 获取对象在配置文件中的id */
String names[] = ac.getBeanDefinitionNames();
System.out.print("> name: ");
for(String name:names) {
    System.out.print(" "+name);
}
```

----

## 1.2 IoC 控制反转

依赖注入(DI)的实现方式有两种：
- 配置文件，即基于XML
- 基于注解，为属性赋值

使用依赖注入为对象设置属性值的实现方式有两种：
- set注入：Spring调用set方法
- 构造注入：Spring调用有参构造方法

### 基于XML的DI

**1). 在Spring配置文件中为对象属性赋值（set注入）**
- 简单类型
  
> 每个`<property>`用来为一个属性赋值，name=属性名，value=属性值
> **注意：**
> 由于该方法的原理是调用set方法，故在该类中必须有set方法

```xml
    <bean id="t1" class="hzx.App1">
        <property name="a" value="1"></property>
        <property name="b" value="2"></property>
    </bean>
```
  
- 引用类型
> 引用类型使用属性`ref`对一个类对象进行赋值，ref的值在另一个bean标签进行赋值
```xml
    <bean id="t1" class="hzx.App1">
        <property name="a" value="1"></property>
        <property name="b" value="2"></property>
        <property name="app1" ref="myt1"></property>
    </bean>

    <bean id="myt1" class="hzx.App1">
        <property name="a" value="3"></property>
        <property name="b" value="4"></property>
    </bean>
```

**2). 在Spring配置文件中为对象属性赋值（构造注入）**

> 写法与上一致，将标签`<property>` 换成 `<constructor-arg>`，参数不变

**3). 自动注入**
Spring根据某些规则自动给**引用类型**赋值
常用规则：
- byName
要求：bean的id 与 属性名 一致

```xml
    <bean id="t1" class="hzx.App1" autowire="byName">
        <property name="a" value="1"></property>
        <property name="b" value="2"></property>
    </bean>

    <bean id="myt1" class="hzx.App1">
        <property name="a" value="3"></property>
        <property name="b" value="4"></property>
    </bean>
```

- btType 
要求：引用数据类型与class标签的类是同源的

> **同源**
> 1. 同一个类
> 2. 互为父子类
> 3. 是接口与实现类的关系

**4). 多配置文件**
为了方便开发，一般使用多个Spring配置文件，同时创建一个Spring配置目录文件来统一访问与管理
在Spring配置目录文件中，需要加载其他所有配置文件，语法：
```xml
<import resource="classpath:类路径">
```

> **类路径**
> 配置文件在 target/classs 包下的路径
> 采用**通配符**可以一次性读取符合的配置文件

### 基于注解的DI

**使用步骤**：
1. 加入maven依赖 spring-context (使用该依赖中的spring-aop实现注解)
2. 在类中加入注解
3. 在spring配置文件中加入组件扫描器，说明注解位置

```xml
 <context:component-scan base-package="holan" />
 <!-- base-package:要扫描的包 
        如果需要扫描多个包： 1. 在 "" 内使用 ; 或 , 分隔包名
                            2. 指定父包
 -->
```

**1). `@Component` 创建对象** 
等同于 `<bean>` ，其属性 `value` 等同于 `id`,其创建的对象也仅此一个
位置：类的上面
下面代码表示：创建类App的对象，对象名为app
```java
// 只写value的情况下，'value=' 可以省略 
// 如果()内没有参数，则会由spring给一个默认的名称：类名的首字母小写
@Component(value = "app")
public class App {
 ...
}
```

以下注解与@Component效果一致、语法一致，但是具有额外的功能：

- `@Repository` 实现层，访问数据库
- `@Service` 业务层，具有事务功能
- `@Controller` 控制器，接受参数显示结果

使用这三个注解，就能将项目的对象分层。当一个类不属于上述三个层，则使用`@Component`

**2). `@Value()` 为简单属性赋值**

注解位置：
- 属性定义上方，此种方法无需定义set方法 （推荐）
- set方法上

```java
public class App {
    @Value(value = "1")
    int a;
    @Value(value = "2")
    int b;
    ...
}
```

**3). `@Autowired()` 为引用属性赋值**

原理：自动注入。默认使用byType自动注入
注解位置：属性定义上方
注意：需要先在该引用属性的类上@Component

```java
@Component(value = "app")
public class App {
    @Value(value = "1")
    int a;
    @Value(value = "2")
    int b;
    @Autowired
    stu mystu;
    ...
}

@Component("MyStu")
public class stu {
    @Value("123")
    int a;
    ...
}
```

> **使用byName方式自动注入**
> @Autowired
> @Qualifier("id_name")
> 其中，id_name是引用属性对象的id名
> **或者**：
> @Resource(name="id_name")

---

## 1.3 AOP 面向切面编程

### 动态代理

体现在：不修改一个类本身的代码的前提下，增加这个类的功能.

- 底层实现原理：**反射**
- 实现方式：
  - 基于接口：**JDK动态代理**
  - 基于实现类：cglib
  - 基于Java字节码：javasist
- 主要了解：
  - Proxy类 代理
  - InvocationHandler 调用代理类的方法

> **实例：**
> 两种角色：房东Host类 和 租户Client类 都实现了接口Rent中的 f() 方法，采用动态代理的方法，实现通过调用接口来动态代理两种不同的角色类，输出同一种方法的不同实现结果

``` java
public class MyProxyInH implements InvocationHandler {

    // 被代理的接口
    Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    // 得到代理类
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }

    // 处理代理对象 返回结果
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return method.invoke(object,objects);
    }
}
```

测试类：

```java
@Test
public void t1() {
    Host host=new Host();
    // 代理角色
    MyProxyInH myPI = new MyProxyInH();
    // 调用程序处理角色来实现接口调用
    myPI.setRent(host);
    Rent rent=(Rent)myPI.getProxy();
    rent.f();
    // 输出：host的输出内容
}
```

### AOP介绍

![aop](/img/aop.png)

Spring提供声明式事务，允许用户自定义切面。在不改变源码的情况下，添加新的功能。

### 使用原生Spring实现AOP

**1. 导入依赖**

```xml
<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjweaver</artifactId>
  <version>1.9.4</version>
</dependency>
```

**2. 创建接口与实现类,创建日志类（即新增方法）**

```java
// 接口 Service
public interface Service {
    public void add();
    public void del();
    public void upd();
    public void que();
}
```

```java
// 实现类： UserService
@Component("user")
public class UserService implements Service {

    @Override
    public void add() {
        System.out.println("> User add...");
    }

    @Override
    public void del() {
        System.out.println("> User del...");
    }

    @Override
    public void upd() {
        System.out.println("> User upd...");
    }

    @Override
    public void que() {
        System.out.println("> User que...");
    }
}
```

```java
// 日志类
@Component("log")
public class Log implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        // 在切入点之前执行该方法
        System.out.println("! "+target.getClass().getName()+" "+method.getName()+" works...");
    }
}
```

**3. 配置注解组件扫描器，配置AOP**

修改配置文件：bean.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop
       https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--声明组件扫描器-->
    <context:component-scan base-package="holan.aopInSpring" />

    <!-- 配置AOP 导入aop约束   -->
    <aop:config>
        <!--  切入点 -->
        <aop:pointcut id="pointcut" expression="execution(* holan.aopInSpring.UserService.*(..))"/>
        <!--  执行环绕增加  -->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
    </aop:config>
</beans>
```

**4. 编写测试类并查看结果**

```java

public class test {
    @Test
    public void test1() {
        // 配置文件名
        String conf="bean.xml";
        ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
        // 获取实现类的bean，但是强制转换为接口的类型
        Service obj= (Service) ac.getBean("user");
        // 执行方法
        obj.add();
    }
}
```

**运行结果**

![](/img/aop_01.png)

### 使用自定义类实现AOP

1. 构建自定义类（代替上一种方法的日志类，类中是需要新添加的功能）

```java
@Component("after")
public class NewAct {
    public void after() {
        System.out.println("! After action...");
    }
}
```

2. 修改配置文件，添加新的切面（即该自定义类）

```xml
<!--  自定义切入面（使用自己的自定义类）      -->
        <aop:aspect ref="after">
            <aop:after method="after" pointcut-ref="pointcut"/>
        </aop:aspect>
```

运行结果

![](/img/aop_02.png)

### 使用注解实现AOP

1. 编写自定义类，添加切面注解 `@Aspect`

```java
@Component("annoLog")
@Aspect
public class annoLog {
    @Before("execution(* holan.aopInSpring.UserService.*(..))") // 切入点
    public void before()  {
        System.out.println("! annoLog");
    }
}
```

2. 在配置文件中添加注解支持

```xml
<!--开启注解支持 -->
    <aop:aspectj-autoproxy/>
```

## 1.4 整合Mybatis

### 导包、配置与测试

**导包**

```xml
<dependencies>

<!-- mybatis in spring   -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.34</version>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.5</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.1.9.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.1.9.RELEASE</version>
    </dependency>
    <!--    注意版本匹配-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>2.0.2</version>
    </dependency>
<!-- mybatis in spring   -->

<!-- aspectj 依赖 -->
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.9.4</version>
    </dependency>
<!-- aop 依赖 -->

<!-- spring  -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
    </dependency>
      <dependency>
          <groupId>org.jetbrains</groupId>
          <artifactId>annotations</artifactId>
          <version>RELEASE</version>
          <scope>compile</scope>
    </dependency>
</dependencies>
```

**配置Spring配置文件 (bean.xml)**

- 使用Spring数据源替代Mybatis数据源

```xml
 <!--    DataSource:使用 Spring 数据源替代 Mybatis 数据源-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql:///db_im_swpu"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>
```

- 设置SqlSessionFactory 绑定Mybatis配置文件

```xml
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!--  绑定Mybatis配置文件      -->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:mappers.xml"/>
    </bean>
```

- 创建 SqlSessionFactoryBean

```xml
    <!--    建立SqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--    只能通过构造器注入：没有 Set方法      -->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
<!--用户自定义类，封装数据库方法，实现mapper接口-->
    <bean id="session" class="holanswide.mapper.UserMapper">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>
```

- 创建自定义类实现mapper接口，写入bean

```java
// 实现Mappers接口
public class SqlSession implements Mappers {
    private SqlSessionTemplate session;
//必须要有Set方法
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.session = sqlSession;
    }
//实现数据库查询举例
    @Override
    public List<User> SearchAll() {
        return session.getMapper(SqlSession.class).SearchAll();
    }
}
```

## 1.5 声明性事务

### 事务

- 一组业务要么都成功，要么都失败
- 确保数据的完整性和一致性

**事务的ACID原则**

- 原子性
- 一致性
- 隔离性： 防止事务同时操作同一数据
- 持久性： 成功执行的事务将会写入存储器

### Spring中的事务

[Spring事务](https://blog.csdn.net/t_t2_3/article/details/114548914)

