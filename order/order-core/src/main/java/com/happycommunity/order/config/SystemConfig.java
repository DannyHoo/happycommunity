package com.happycommunity.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Danny
 * @Title: SystemConfig
 * @Description: 系统配置
 * https://blog.csdn.net/zsxlbr1314/article/details/82556310
 * @Created on 2018-12-03 16:01:50
 */
@Configuration
public class SystemConfig {

    @Value("${system.mqConsumerNamesrvAddr}")
    private String mqConsumerNamesrvAddr;

    public String getMqConsumerNamesrvAddr() {
        return mqConsumerNamesrvAddr;
    }

    public SystemConfig setMqConsumerNamesrvAddr(String mqConsumerNamesrvAddr) {
        this.mqConsumerNamesrvAddr = mqConsumerNamesrvAddr;
        return this;
    }

}
