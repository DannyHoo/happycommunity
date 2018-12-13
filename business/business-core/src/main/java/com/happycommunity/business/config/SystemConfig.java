package com.happycommunity.business.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Danny
 * @Title: SystemConfig
 * @Description: 系统配置
 * https://blog.csdn.net/zsxlbr1314/article/details/82556310
 * @Created on 2018-12-03 16:01:50
 */
@Component
@ConfigurationProperties(prefix = "system")
public class SystemConfig {

    /*@Value("${com.happycommunity.business.config.rsaPrivateKey}")*/
    private String rsaPrivateKey;

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public SystemConfig setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
        return this;
    }
}
