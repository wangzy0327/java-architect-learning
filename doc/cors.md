## 前后端使用不同端口联调的跨域问题

前端的启动端口号为8080，后端的启动端口为8088

前端js的配置

```js
window.app = {
/* 开发环境 */
    serverUrl: "http://localhost:8088", // 接口服务接口地址
    paymentServerUrl: "http://192.168.1.3:8089", // 支付中心服务地址
    shopServerUrl: "http://localhost:8080/foodie-shop/", // 门户网站地址
    centerServerUrl: "http://localhost:8080/foodie-center/", // 用户中心地址
    cookieDomain: "", // cookie 域
}
```

后端接收跨域配置

```java
package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig{

    public CorsConfig(){}

    @Bean
    public CorsFilter corsFilter(){
        // 1. 添加cors配置信息  设置跨域联调接收localhost:8080访问
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
//        config.addAllowedOrigin("http://shop.z.mukewang.com:8080");
//        config.addAllowedOrigin("http://center.z.mukewang.com:8080");
//        config.addAllowedOrigin("http://shop.z.mukewang.com");
//        config.addAllowedOrigin("http://center.z.mukewang.com");
//        config.addAllowedOrigin("*");

        // 设置是否发送cookie信息
        config.setAllowCredentials(true);

        // 设置允许请求的方式
        config.addAllowedMethod("*");

        // 设置允许的header
        config.addAllowedHeader("*");

        // 2. 为url添加映射路径 设置请求进来可以访问后端所有路由
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);

        // 3. 返回重新定义好的corsSource
        return new CorsFilter(corsSource);
    }

}

```

然后就可以前后端数据进行交互