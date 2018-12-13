package com.happycommunity.user;

import com.alibaba.fastjson.JSON;
import com.happycommunity.user.config.SystemConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring Boot 应用启动类
 *
 */
@SpringBootApplication(scanBasePackages ="com.happycommunity.user")
public class UserCoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UserCoreApplication.class, args);
        SystemConfig systemConfig=(SystemConfig)context.getBean("systemConfig");
        System.out.println("UserCoreApplication started! SystemConfig："+ JSON.toJSONString(systemConfig));
    }
}
