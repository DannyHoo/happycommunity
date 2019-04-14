package com.happycommunity.business.config;

import com.alibaba.dubbo.config.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Danny
 * @Title: DubboConfig
 * @Description:
 * @Created on 2019-04-09 22:55:01
 */
@Configuration
public class DubboConfig {

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(false);
        return consumerConfig;
    }

}
