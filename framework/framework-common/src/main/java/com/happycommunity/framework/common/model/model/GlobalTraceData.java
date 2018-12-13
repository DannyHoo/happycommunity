package com.happycommunity.framework.common.model.model;


/**
 * @author dannyhoo
 * @Title: GlobalTraceData
 * @Description: 当次请求所带的关键信息
 * @Created on 2018-06-12 09:16:41
 */
public class GlobalTraceData extends BaseModel {

    /**
     * 一次请求的唯一标识
     **/
    private String gRequestId;
    /**
     * 用户业务id
     **/
    private String gBusinessId;
    /**
     * 请求的设备id
     **/
    private String gDeviceId;
    /**
     * 请求来源
     **/
    private String gRequestSource;
    /**
     * 请求的userAgent
     **/
    private String gUserAgent;
    /**
     * 请求url
     **/
    private String gRequestUrl;
    /**
     * 请求ip
     **/
    private String gRequestIp;
    /**
     * 客户端app版本号
     **/
    private String gAppClientVersion;

    public static GlobalTraceData newInstance() {
        return new GlobalTraceData();
    }

    public GlobalTraceData copyProperties(GlobalTraceData globalTraceData) {
        if (globalTraceData == null) return this;
        return this.setgRequestId(globalTraceData.getgRequestId())
                .setgBusinessId(globalTraceData.getgBusinessId())
                .setgDeviceId(globalTraceData.getgDeviceId())
                .setgRequestSource(globalTraceData.getgRequestSource())
                .setgUserAgent(globalTraceData.getgUserAgent())
                .setgRequestUrl(globalTraceData.getgRequestUrl())
                .setgRequestIp(globalTraceData.getgRequestIp())
                .setgAppClientVersion(globalTraceData.getgAppClientVersion());
    }

    public String getgRequestId() {
        return gRequestId;
    }

    public GlobalTraceData setgRequestId(String gRequestId) {
        this.gRequestId = gRequestId;
        return this;
    }

    public String getgBusinessId() {
        return gBusinessId;
    }

    public GlobalTraceData setgBusinessId(String gBusinessId) {
        this.gBusinessId = gBusinessId;
        return this;
    }

    public String getgDeviceId() {
        return gDeviceId;
    }

    public GlobalTraceData setgDeviceId(String gDeviceId) {
        this.gDeviceId = gDeviceId;
        return this;
    }

    public String getgRequestSource() {
        return gRequestSource;
    }

    public GlobalTraceData setgRequestSource(String gRequestSource) {
        this.gRequestSource = gRequestSource;
        return this;
    }

    public String getgUserAgent() {
        return gUserAgent;
    }

    public GlobalTraceData setgUserAgent(String gUserAgent) {
        this.gUserAgent = gUserAgent;
        return this;
    }

    public String getgRequestUrl() {
        return gRequestUrl;
    }

    public GlobalTraceData setgRequestUrl(String gRequestUrl) {
        this.gRequestUrl = gRequestUrl;
        return this;
    }

    public String getgRequestIp() {
        return gRequestIp;
    }

    public GlobalTraceData setgRequestIp(String gRequestIp) {
        this.gRequestIp = gRequestIp;
        return this;
    }

    public String getgAppClientVersion() {
        return gAppClientVersion;
    }

    public GlobalTraceData setgAppClientVersion(String gAppClientVersion) {
        this.gAppClientVersion = gAppClientVersion;
        return this;
    }
}
