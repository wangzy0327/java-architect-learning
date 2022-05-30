# SpringBoot依赖

项目整体采用聚合maven工程构建

工程根目录为foodie-dev，根目录下包含5个子目录分别是foodie-dev-api,foodie-dev-common,foodie-dev-mapper,foodie-dev-pojo,foodie-dev-service

外层foodie-dev的pom.xml

```xml
<!--
默认maven打包方式为jar
根目录需要聚合这里采用pom
-->
<packaging>pom</packaging>

<modules>
    <module>foodie-dev-common</module>
    <module>foodie-dev-pojo</module>
    <module>foodie-dev-mapper</module>
    <module>foodie-dev-service</module>
    <module>foodie-dev-api</module>
</modules>

<properties>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
</properties>
```

子模块foodie-dev-api的pom.xml

```xml
    <parent>
        <artifactId>foodie-dev</artifactId>
        <groupId>com.imooc</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <artifactId>foodie-dev-api</artifactId>
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
     <!--
    api -> service -> mapper -> pojo -> common
        -> common-mail
    -->
    <dependencies>
        <dependency>
            <groupId>com.imooc</groupId>
            <artifactId>foodie-dev-service</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

子模块foodie-dev-service的pom.xml

```xml
    <parent>
        <artifactId>foodie-dev</artifactId>
        <groupId>com.imooc</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>  
    <artifactId>foodie-dev-service</artifactId>
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
<!--
    service -> mapper ->pojo -> common
    -->
    <dependencies>
        <dependency>
            <groupId>com.imooc</groupId>
            <artifactId>foodie-dev-mapper</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

子模块foodie-dev-mapper

```xml
 <parent>
        <artifactId>foodie-dev</artifactId>
        <groupId>com.imooc</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
        <artifactId>foodie-dev-mapper</artifactId>
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
 <!--
    mapper ->pojo -> common
    -->
    <dependencies>
        <dependency>
            <groupId>com.imooc</groupId>
            <artifactId>foodie-dev-pojo</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>        
```

子模块foodie-dev-pojo的pom.xml

```xml
    <parent>
        <artifactId>foodie-dev</artifactId>
        <groupId>com.imooc</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>foodie-dev-pojo</artifactId>
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.imooc</groupId>
            <artifactId>foodie-dev-common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

子模块foodie-dev-common的pom.xml

```xml
    <parent>
        <artifactId>foodie-dev</artifactId>
        <groupId>com.imooc</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
     <artifactId>foodie-dev-common</artifactId>
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
```



#### **1.根目录foodie-dev引入parent依赖**

```xml
<parent>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>2.1.5.RELEASE</version>
<relativePath />
</parent>
```

#### **2.设置资源属性**

```xml
<properties>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
<java.version>1.8</java.version>
</properties>
```

#### **3.引入依赖dependency**

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <!--移除默认日志 后面使用log4j 和 -->
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    <!--切面依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- mysql驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.41</version>
        </dependency>
        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.4</version>
        </dependency>


        <!--
        mybatis 逆向生成工具
        -->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>2.1.5</version>
        </dependency>

        <!--mybatis pagehelper -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.12</version>
        </dependency>

        <!-- apache 工具类 -->
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>1.11</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.4</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-io</artifactId>
            <version>1.3.2</version>
        </dependency>

        <!--
        为了减少程序员撰写文档时间，提高生产力， Swagger2 应运而生，使用 Swagger2 可以减少编写过多的文档。
        只需要通过代码就能生成文档API,提供给前端人员，非常方便
        -->
        <!-- swagger2 配置 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.4.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.4.0</version>
        </dependency>
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>swagger-bootstrap-ui</artifactId>
            <version>1.6</version>
        </dependency>

        <!--引入日志依赖 抽象层 与 实现层-->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.21</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.21</version>
        </dependency>

    </dependencies>
```

#### 4.打包跳过单元测试

```xml
    <build>
        <plugins>
            <!--跳过单元测试进行 打包-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

#### 5.引入测试依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>
```

