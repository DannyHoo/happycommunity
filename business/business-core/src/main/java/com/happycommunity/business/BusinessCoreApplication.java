package com.happycommunity.business;

import com.alibaba.fastjson.JSON;
import com.happycommunity.business.config.SystemConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Danny
 * @Title: BusinessCoreApplication
 * @Description:
 * @Created on 2018-11-26 17:35:50
 */
@SpringBootApplication
public class BusinessCoreApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BusinessCoreApplication.class, args);
        SystemConfig systemConfig=(SystemConfig)context.getBean("systemConfig");
        System.out.println("BusinessCoreApplication started! SystemConfig："+JSON.toJSONString(systemConfig));
    }
}
