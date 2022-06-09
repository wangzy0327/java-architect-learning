## log4j日志整合

slf4j是日志抽象层

由于默认spring-boot是包含spring-boot-starter-logging日志的，这里我们可以将其剔除掉

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <!--移除默认日志-->
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

添加日志框架依赖

```xml
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
```

日志框架配置文件

```properties
#设置rootLogger设置为Debug
#设置了控制台和文件两种日志表现形式
log4j.rootLogger=DEBUG,stdout,file
log4j.additivity.org.apache=true

#控制台形式日志级别为INFO
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.threshold=INFO
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%-5p %c{1}:%L - %m%n

#文件日志形式日志级别为INFO
log4j.appender.file=org.apache.log4j.DailyRollingFileAppender
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.DatePattern='.'yyyy-MM-dd-HH-mm
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
log4j.appender.file.Threshold=INFO
log4j.appender.file.append=true
#E://Users//wzy//IdeaProjects//logs//java-architect-learning//foodie-api
log4j.appender.file.File=C://Users//wzy//IdeaProjects//logs//java-architect-learning//foodie-api//mylog.log
```



在代码中使用Logger进行日志控制打印

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HelloController {

    final static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hello")
    public Object hello(){
        logger.debug("debug: hello~");
        logger.info("info: hello~");
        logger.warn("warn: hello~");
        logger.error("error: hello~");
        return "Hello World~";
    }
}
```

