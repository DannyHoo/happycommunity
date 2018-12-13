package com.happycommunity.framework.core.log.trace;

import com.happycommunity.framework.common.model.model.GlobalTraceData;
import com.happycommunity.framework.core.log.logger.CommonLogger;
import com.happycommunity.framework.core.log.logger.CommonLoggerFactory;
import com.happycommunity.framework.core.util.ExceptionUtils;
import com.happycommunity.framework.core.util.IPUtils;

/**
 * @author dannyhoo
 * @Title: AbstractLog
 * @Description:
 * @Created on 2018-06-11 23:47:49
 */
public class AbstractLog {
    /* 是否打开system.out日志输出，默认不打开，本地开发环境可以配置打开 */
    public static final boolean isOpenSystemOutLog = Boolean.parseBoolean(System.getProperty("isOpenSystemOutLog", "false"));
    private static String newLineFlag = "\n";
    private static String replaceNewLineFlag = "<br>";
    private static String tableFlag = "\t";
    private static String replaceTableFlag = "  ";

    protected CommonLogger commonLogger;

    private GlobalTraceData getGlobalTraceData() {
        GlobalTraceData globalTraceData = GlobalTraceDataHandler.getGlobalTraceData();
        if (globalTraceData == null) {
            globalTraceData = new GlobalTraceData();
        }
        return globalTraceData;
    }


    /**
     * 业务id
     *
     * @return
     */
    protected Object getUserBizId() {
        return GlobalTraceDataHandler.getGlobalTraceData();
    }

    protected String getRequestUrl() {
        return this.getGlobalTraceData().getgRequestUrl();
    }

    public String getRequestIp() {
        return this.getGlobalTraceData().getgRequestIp();
    }

    public String getThreadName() {
        return Thread.currentThread().getName();
    }

    public String getLocalIp() {
        return IPUtils.getLocalIP();
    }

    public Object getRequestID() {
        return this.getGlobalTraceData().getgRequestId();
    }

    public Object getDeviceId() {
        return this.getGlobalTraceData().getgDeviceId();
    }

    public Object getAppClientVersion() {
        return this.getGlobalTraceData().getgAppClientVersion();
    }

    public Object getRequestSource() {
        return this.getGlobalTraceData().getgRequestSource();
    }

    public Object getUserAgent() {
        return this.getGlobalTraceData().getgUserAgent();
    }

    protected String getExceptionClass(Throwable t) {
        if (t == null) {
            return null;
        }
        return t.getClass().getName();
    }

    protected String getExceptionMessage(Throwable t) {
        if (t == null) {
            return null;
        }
        String exceptionMessage = ExceptionUtils.getStackTrace(t);
        exceptionMessage = exceptionMessage.replaceAll(newLineFlag, replaceNewLineFlag);
        exceptionMessage = exceptionMessage.replaceAll(tableFlag, replaceTableFlag);
        return exceptionMessage;
    }

    public void setLogger(String name) {
        commonLogger = CommonLoggerFactory.getCommonLogger(name);
    }

}
