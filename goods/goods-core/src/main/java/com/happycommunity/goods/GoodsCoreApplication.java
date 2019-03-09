package com.happycommunity.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Danny
 * @Title: GoodsCoreApplication
 * @Description:
 * @Created on 2018-12-19 14:13:31
 */
@SpringBootApplication(scanBasePackages = "com.happycommunity.goods")
public class GoodsCoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GoodsCoreApplication.class, args);
    }

}
