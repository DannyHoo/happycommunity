package com.happycommunity.framework.core.log.enums;

/**
 * @author dannyhoo
 * @Title: MethodLogKeysEnum
 * @Description:
 * @Created on 2018-06-11 23:13:15
 */
public enum MethodLogKeysEnum {
    BizSystem("业务系统", "bizSystem"),
    BizService("业务服务", "bizService"),
    BizUserId("业务用户ID", "gbizUserId"),
    AppServerIp("业务应用部署ip", "appServerIp"),
    RequestID("每次请求的唯一id,通过该uuid串联起一个请求的所有日志", "gRequestID"),
    FClassName("类全名(包括所属包)", "fClassName"),
    SClassName("类名", "sClassName"),
    MethodName("方法名", "methodName"),
    ClassMethodParametherName("类名+方法名+请求参数个数(保证能够区分重载的方法)", "cmpName"),
    ConsumeTime("执行耗时", "consumeTime"),
    ThreadName("线程名称", "threadName"),
    Request("请求参数", "request"),
    Response("返回结果", "response"),
    RequestIp("请求访问ip", "gRequestIp"),
    RequestUrl("入口请求url", "gRequestUrl"),
    DeviceId("设备id", "gDeviceId"),
    AppClientVersion("客户端版本号", "gAppClientVersion"),
    RequestSource("请求来源,android,ios,weiXin", "gRequestSource"),
    UserAgent("请求的userAgent", "gUserAgent"),
    CodeLayer("代码层位置,如controller,service,domain,dao", "codeLayer"),
    LogCategory("日志分类", "logCategory"),
    LogTimeLong("日志记录时间(毫秒)", "logTimeLong"),
    LogTimeString("日志记录时间(字符串)", "logTimeString"),
    AlarmType("报警类型", "alarmType"),
    ResultType("结果类型", "resultType"),
    ExceptionClass("异常类", "exceptionClass"),
    ExceptionMessage("异常信息", "exceptionMessage"),;


    private String name;
    private String key;

    MethodLogKeysEnum(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public MethodLogKeysEnum setName(String name) {
        this.name = name;
        return this;
    }

    public String getKey() {
        return key;
    }

    public MethodLogKeysEnum setKey(String key) {
        this.key = key;
        return this;
    }
}
