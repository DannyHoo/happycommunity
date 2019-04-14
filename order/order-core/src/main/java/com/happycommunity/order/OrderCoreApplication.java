package com.happycommunity.order;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Danny
 * @Title: OrderCoreApplication
 * @Description:
 * @Created on 2019-01-04 10:36:48
 */
@SpringBootApplication(scanBasePackages ="com.happycommunity.order")
@ImportResource(value = {"classpath:dubbo/dubbo-provider.xml","classpath:dubbo/dubbo-consumer.xml"})
//@PropertySource(value= {"classpath:config/test.properties"})
public class OrderCoreApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OrderCoreApplication.class, args);
    }
}
