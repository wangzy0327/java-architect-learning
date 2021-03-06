# SpringBoot项目中application.yml及其日志配置文件

#### foodie-dev-api模块中的src/main/resources/下新建application.yml，并在其中配置访问端口、数据源、mybatis、日志配置信息

```yml
############################################################
#
# web访问端口号  约定：8088
#
############################################################
server:
  port: 8088
  # 内置 tomcat
  tomcat:
    uri-encoding: UTF-8
  max-http-header-size: 80KB
############################################################
#
# 配置数据源信息
#
############################################################
spring:
  datasource: # 数据源的相关配置
    type: com.zaxxer.hikari.HikariDataSource # 数据源类型：HikariCP
    driver-class-name: com.mysql.jdbc.Driver # mysql驱动
    #driver-class-name: com.mysql.jdbc.Driver # mysql驱动
    url: jdbc:mysql://localhost:3306/foodie-shop?useUnicode=true&characterEncoding=UTF-8&autoReconnect&useSSL=false
    username: root
    password: root
    hikari:
      connection-timeout: 30000 # 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQ
      minimum-idle: 5 # 最小连接数
      maximum-pool-size: 20 # 最大连接数
      auto-commit: true # 自动提交
      idle-timeout: 600000 # 连接超时的最大时长（毫秒），超时则被释放（retired），默认:10分钟
      pool-name: DateSourceHikariCP # 连接池名字
      max-lifetime: 1800000 # 连接的生命时长（毫秒），超时而且没被使用则被释放（retired），默认:30分钟
      connection-test-query: SELECT 1
############################################################
#
# mybatis 配置
#
############################################################
mybatis:
  type-aliases-package: com.imooc.pojo # 所有POJO类所在包路径
  mapper-locations: classpath:mapper/*.xml # mapper映射文件
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  # 控制台输出sql的配置实现

############################################################
#
# mybatis mapper 配置
#
############################################################
# 通用 Mapper 配置
mapper:
  mappers: com.imooc.my.mapper.MyMapper
  not-empty: false  # 在进行数据库操作的时候，判断表达式 username != null, 是否追加 username != ''
  identity: MYSQL  # 数据库方言
# 分页插件配置
pagehelper:
  helperDialect: mysql
  supportMethodsArguments: true
```

