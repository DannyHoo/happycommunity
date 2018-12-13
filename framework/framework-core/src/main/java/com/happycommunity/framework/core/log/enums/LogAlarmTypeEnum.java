package com.happycommunity.framework.core.log.enums;

/**
 * @author dannyhoo
 * @Title: LogAlarmTypeEnum
 * @Description:
 * @Created on 2018-06-12 00:07:27
 */
public enum  LogAlarmTypeEnum {
    None("不需要报警","none"),
    Email("通过邮箱报警","email"),
    Sms("通过短信报警","sms"),
    WeiXin("通过微信报警","weixin"),
    EmailAndWeiXin("通过邮箱和微信报警","emailAndWeixin"),
    All("所有报警通道","all"),
            ;

    private String name;
    private String key;

    private LogAlarmTypeEnum(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
