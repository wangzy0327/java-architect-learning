## 使用Mybatis数据库逆向生成工具注意事项

mapper相关类及其配置文件引入后 需要在Application启动类下添加扫描的配置注解 @MapperScan 

注意 @MapperScan 注解需要引入的包是 tk.mybatis.spring.annotation.MapperScan

```java
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//需要引入tk.mybatis.spring.annotation
//扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "com.imooc.mapper")
//扫描所有包以及相关组件包，默认不写
//这里需要引入其他的包org.n3r.idworkder 所以要加 扫描的包com.imooc下 
//org.n3r.idworker包为一些常用的工具类包
//如Sid类生成 唯一的主键id号
@ComponentScan(basePackages = {"com.imooc","org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
```



解决IDEA中mapper接口类 波浪红线 警告 问题

在设置中将其自动注入提示 从 Error等级设置为Warning即可

![IDEA自动注入警告配置](../imgs/autowired-mapper.png)

### 通用Mapper接口所封装的常用方法

1.首先先来看一下 MyMapper 所继承的父类，如：

```java
interface MyMapper<T> extends Mapper<T>,MySqlMapper<T>
```

这里有两个父类， Mapper 与 MySqlMapper ，我们可以打开 MySqlMapper 看一下：

```java
interface MySqlMapper<T> extends InsertListMapper<T>,InsertUseGeneratedKeysMapper<T>{}
```

这里面又继承了了两个mapper，从类名上可以看得出来，是用于操作数据库的，这两个类里又分别包含了如下方法，简单归类一下：

| 方法名                         | 操作         | 备注       |
| ------------------------------ | ------------ | ---------- |
| insertList(list)               | 数据批量插入 | 主键须自增 |
| insertUseGeneratedKeys(record) | 插入表数据   | 主键须自增 |

很明显，在传统JavaWeb开发，这两个方法使用是没有问题的，但是我们的数据库表主键设计肯定是全局唯一的，所以不可能使用自增长id（如何设计全局唯一分在后续课程里有具体的讲解），所以这两个方法在我们开发过程中是不会使用的，这一点需要注意噢~！

2.随后再来看一下 Mapper 中所继承的父类，如下：

```java
interface Mapper<T> extends BaseMapper<T>,ExampleMapper<T>,RowBoundsMapper<T>
```

分别来看一下各个父类中的方法有些啥？

```
BaseMapper<T>
```

| 类               | 方法                                      | 操作                                             |
| ---------------- | ----------------------------------------- | ------------------------------------------------ |
| BaseSelectMapper | T selectOne(T record)                     | 根据实体类中的属性查询表数据，返回单个实体       |
|                  | List select(T record)                     | 根据实体类中的属性查询表数据，返回符合条件的list |
|                  | List selectAll()                          | 返回该表所有记录                                 |
|                  | int selectCount(T record)                 | 根据条件查询记录数                               |
|                  | T selectByPrimaryKey(Object key)          | 根据主键查询单挑记录                             |
|                  | boolean existsWithPrimaryKey(Object key)  | 查询主键是否存在，返回true或false                |
| BaseInsertMapper | int insert(T record)                      | 插入一条记录，属性为空也会保存                   |
|                  | int insertSelective(T record)             | 插入一条记录，属性为空不保存，会使用默认值       |
| BaseUpdateMapper | int updateByPrimaryKey(T record)          | 根据实体类更新数据库，属性有null会覆盖原记录     |
|                  | int updateByPrimaryKeySelective(T record) | 根据实体类更新数据库，属性有null改属性会忽略     |
| BaseDeleteMapper | int delete(T record)                      | 根据实体类中属性多条件删除记录                   |
|                  | int deleteByPrimaryKey(Object key)        | 根据主键删除记录                                 |

ExampleMapper ，Example类是用于提供给用户实现自定义条件的，也就是 where 条件，主要方法见如下表格：

| 类                             | 方法                                                         | 操作                         |
| ------------------------------ | ------------------------------------------------------------ | ---------------------------- |
| SelectByExampleMapper          | List selectByExample(Object example)                         | 根据条件查询记录list         |
| SelectOneByExampleMapper       | T selectOneByExample(Object example)                         | 根据条件查询单条记录         |
| SelectCountByExampleMapper     | int selectCountByExample(Object example)                     | 根据条件查询记录数           |
| DeleteByExampleMapper          | int deleteByExample(Object example)                          | 根据条件删除记录             |
| UpdateByExampleMapper          | int updateByExample(T record, @Param(“example”) Object example) | 根据条件更新数据，null会覆盖 |
| UpdateByExampleSelectiveMapper | int updateByExampleSelective(T record, Object example)       | 根据条件更新数据，null会忽略 |

RowBoundsMapper 这个是用于做分页的，我们在后续阶段中会使用page-helper这个组件来替代这个分页实现

### **总结**

通用mapper所提供的CRUD方法对单表操作，大大提高开发效率，当然复杂的多表操作还是需要在mapper.xml中自己去编写sql代码实现。

本小节列举了通用mapper中常用的一些方法，在后续阶段课程里我们也都会去使用的