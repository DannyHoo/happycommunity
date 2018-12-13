package com.happycommunity.framework.core.log.trace;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.happycommunity.framework.common.model.model.GlobalTraceData;
import com.happycommunity.framework.core.log.enums.LogAlarmTypeEnum;
import com.happycommunity.framework.core.log.enums.LogCategoryEnum;
import com.happycommunity.framework.core.log.enums.MethodLogKeysEnum;
import com.happycommunity.framework.core.log.enums.ResultTypeEnum;
import com.happycommunity.framework.core.util.DateUtils;
import com.happycommunity.framework.core.util.StringJsonHandler;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dannyhoo
 * @Title: LogTraceAspect
 * @Description:
 * @Created on 2018-06-09 18:08:10
 */
public class LogTraceAspect extends AbstractLog {
    private static Gson gson = new Gson();

    /**
     * 代码层配置(如controller,service,domain,dao)
     **/
    private List<String> codeLayerConfigs = new ArrayList<String>();
    /**
     * 业务成功标识配置
     **/
    private List<String> bizSuccessConfigs = new ArrayList<String>();
    /**
     * 需要过滤掉的请求参数类
     **/
    private List<String> ignoreRequestClass = new ArrayList<String>();
    /**
     * 业务系统
     **/
    private String bizSystem = "noSet";
    /**
     * 业务服务
     **/
    private String bizService = "noset";

    public Object logTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = getAndResetArgs(joinPoint);
        long startTime = System.currentTimeMillis();
        Object response = joinPoint.proceed(args);
        long endTime = System.currentTimeMillis();
        //commonLogger.info("执行方法：" + joinPoint.getSignature().getName() + "；执行结果：" + JSON.toJSONString(response));
        printRequestLog(joinPoint, args, response, startTime, endTime, null);
        return response;
    }

    private void printRequestLog(JoinPoint joinPoint, Object[] args, Object response, long startTime, long endTime, Throwable t) {
        long logTime = System.currentTimeMillis();
        String fClassName = this.getFClassName(joinPoint);
        String codeLayer = this.getCodeLayer(fClassName);
        String responseString = this.getResponse(response);

        StringJsonHandler stringJsonHandler = StringJsonHandler.newInstance();
        stringJsonHandler
                .appendString(MethodLogKeysEnum.LogTimeString.getKey(), DateUtils.getFullDateFormat(DateUtils.getDate(logTime)))
                .appendString(MethodLogKeysEnum.LogCategory.getKey(), this.getLogCategory())
                .appendString(MethodLogKeysEnum.ClassMethodParametherName.getKey(), this.getClassMethodParameter(joinPoint, args))
                .appendString(MethodLogKeysEnum.ConsumeTime.getKey(), this.getConsumerTime(startTime, endTime))
                .appendString(MethodLogKeysEnum.ResultType.getKey(), this.getResultType(codeLayer, responseString, t))
                .appendString(MethodLogKeysEnum.AlarmType.getKey(), this.getAlarmType(t))
                .appendString(MethodLogKeysEnum.CodeLayer.getKey(), codeLayer)
                .appendString(MethodLogKeysEnum.BizSystem.getKey(), this.getBizSystem())
                .appendString(MethodLogKeysEnum.BizService.getKey(), this.getBizService())
                .appendString(MethodLogKeysEnum.BizUserId.getKey(), this.getUserBizId())
                .appendLong(MethodLogKeysEnum.LogTimeLong.getKey(), logTime)
                .appendString(MethodLogKeysEnum.FClassName.getKey(), fClassName)
                .appendString(MethodLogKeysEnum.SClassName.getKey(), this.getSClassName(joinPoint))
                .appendString(MethodLogKeysEnum.MethodName.getKey(), this.getMethodName(joinPoint))
                .appendString(MethodLogKeysEnum.RequestUrl.getKey(), this.getRequestUrl())
                .appendString(MethodLogKeysEnum.RequestIp.getKey(), this.getRequestIp())
                .appendString(MethodLogKeysEnum.ThreadName.getKey(), this.getThreadName())
                .appendString(MethodLogKeysEnum.AppServerIp.getKey(), this.getLocalIp())
                .appendString(MethodLogKeysEnum.RequestID.getKey(), this.getRequestID())
                .appendString(MethodLogKeysEnum.DeviceId.getKey(), this.getDeviceId())
                .appendString(MethodLogKeysEnum.AppClientVersion.getKey(), this.getAppClientVersion())
                .appendString(MethodLogKeysEnum.RequestSource.getKey(), this.getRequestSource())
                .appendString(MethodLogKeysEnum.UserAgent.getKey(), this.getUserAgent())
                .appendNonString(MethodLogKeysEnum.Request.getKey(), this.getParameter(args));
        if (response instanceof String) {
            stringJsonHandler.appendString(MethodLogKeysEnum.Response.getKey(), responseString);
        } else {
            stringJsonHandler.appendNonString(MethodLogKeysEnum.Response.getKey(), responseString);
        }
        stringJsonHandler.appendString(MethodLogKeysEnum.ExceptionClass.getKey(), this.getExceptionClass(t))
                .appendString(MethodLogKeysEnum.ExceptionMessage.getKey(), this.getExceptionMessage(t))
        ;
        String logContent = stringJsonHandler.getJson();

        commonLogger.info(logContent);
        this.println(logContent);
    }

    /**
     * fClassName：方法所在类的全路径
     *
     * @param joinPoint
     * @return
     */
    private String getFClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }

    /**
     * codeLayer:码层位置,如controller,service,domain,dao
     *
     * @param fClassName
     * @return
     */
    private String getCodeLayer(String fClassName) {
        String codeLayer = "noset";
        String keyValueFlag = "=";
        for (String codeLayerConfig : this.getCodeLayerConfigs()) {
            if (StringUtils.isBlank(codeLayerConfig) || codeLayerConfig.indexOf(keyValueFlag) == -1) {
                continue;
            }
            int index = codeLayerConfig.indexOf(keyValueFlag);
            String codeLayerKey = codeLayerConfig.substring(0, index);
            String codeLayerValue = codeLayerConfig.substring(index + keyValueFlag.length());
            if (fClassName.indexOf(codeLayerKey) > -1) {
                codeLayer = codeLayerValue;
                break;
            }
        }
        return codeLayer;
    }

    /**
     * @param response
     * @return
     */
    private String getResponse(Object response) {
        if (response == null) {
            return null;
        }
        if (response instanceof String) {
            return response.toString();
        }
        String result = null;
        try {
            result = JSON.toJSONString(response);
        } catch (Exception e) {
            try {
                e.printStackTrace();
                result = gson.toJson(response);
            } catch (Exception e1) {
                e1.printStackTrace();
                result = response.toString();
            }
        }
        return StringJsonHandler.newInstance()
                .appendNonString(response.getClass().getName(), result)
                .getJson();
    }

    /**
     * logCategory：日志类别
     * method、business、security、limit
     *
     * @return
     */
    private String getLogCategory() {
        return LogCategoryEnum.Method.name();
    }

    /**
     * cmpName：类+方法+p参数个数
     * 举例：UserServiceImpl_login_p1
     *
     * @param joinPoint
     * @param args
     * @return
     */
    private Object getClassMethodParameter(JoinPoint joinPoint, Object[] args) {
        StringBuffer result = new StringBuffer();
        result.append(this.getSClassName(joinPoint)).append("_");
        result.append(this.getMethodName(joinPoint));
        if (args != null) {
            result.append("_").append("p").append(args.length);
        }
        return result.toString();
    }

    /**
     * consumeTime：方法耗时（单位：毫秒）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    private long getConsumerTime(long startTime, long endTime) {
        long consumeTime = endTime - startTime;
        if (consumeTime <= 0) {
            consumeTime = -1;
        }
        return consumeTime;
    }

    /**
     * resultType：方法结果类型（成功、失败、异常、未设置）
     * bizSuccess、bizFail、exception、noset
     *
     * @param codeLayer
     * @param response
     * @param t
     * @return
     */
    private String getResultType(String codeLayer, Object response, Throwable t) {
        if (t != null) {
            return ResultTypeEnum.Exception.getKey();
        }
        if (response == null) {
            return ResultTypeEnum.BizFail.getKey();
        }

        return this.getResultTypeByCodeLayer(codeLayer, response.toString());
    }

    /**
     * alarmType：报警类型（暂时通过异常来判断，也可以通过方法response来判断）
     * None、Email、Sms、WeiXin、EmailAndWeiXin、All
     *
     * @param t
     * @return
     */
    private String getAlarmType(Throwable t) {
        if (t != null) {
            return LogAlarmTypeEnum.All.name();
        }
        return LogAlarmTypeEnum.None.name();
    }

    /**
     * 类名
     *
     * @param joinPoint
     * @return
     */
    private String getSClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getSimpleName();
    }

    /**
     * 方法名称
     *
     * @param joinPoint
     * @return
     */
    private String getMethodName(JoinPoint joinPoint) {
        MethodSignature method = (MethodSignature) joinPoint.getSignature();
        return method.getName();
    }

    /**
     * 系统名称
     *
     * @return
     */
    public String getBizSystem() {
        return bizSystem;
    }

    public LogTraceAspect setBizSystem(String bizSystem) {
        this.bizSystem = bizSystem;
        return this;
    }

    /**
     * 服务名称
     *
     * @return
     */
    public String getBizService() {
        return bizService;
    }

    public LogTraceAspect setBizService(String bizService) {
        this.bizService = bizService;
        return this;
    }

    private String getParameter(Object[] args) {
        if (args == null) {
            return null;
        }
        StringJsonHandler stringJsonHandler = StringJsonHandler.newInstance();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                continue;
            }
            String name = arg.getClass().getName();

            Object tempArg = arg;
            if (arg instanceof HttpServletResponse) {
                arg = arg.toString();
            } else if (arg instanceof HttpServletRequest) {
                arg = arg.toString();
            } else if (this.isIgnoreRequestClass(name)) {
                arg = arg.toString();
            } else if (tempArg instanceof String || tempArg instanceof char[]) {
                arg = arg;
            } else {
                arg = JSON.toJSONString(arg);
            }

            if (tempArg instanceof String ||
                    tempArg instanceof char[] ||
                    tempArg instanceof HttpServletResponse ||
                    tempArg instanceof HttpServletRequest ||
                    this.isIgnoreRequestClass(name)) {
                stringJsonHandler.appendString(name, arg);
            } else {
                stringJsonHandler.appendNonString(name, arg);
            }
            continue;
        }
        return stringJsonHandler.getJson();
    }

    /**
     * 判断请求的类是否需要被过滤掉
     *
     * @param className
     * @return
     */
    private boolean isIgnoreRequestClass(String className) {
        List<String> defaultIIgnoreRequestClassList = getDefaultIgnoreRequestClass();
        for (String defaultIIgnoreRequestClass : defaultIIgnoreRequestClassList) {
            if (className.indexOf(defaultIIgnoreRequestClass) > -1) {
                return true;
            }
        }
        for (String ignoreClass : ignoreRequestClass) {
            if (className.indexOf(ignoreClass) > -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 默认过滤掉的请求类
     *
     * @return
     */
    private List<String> getDefaultIgnoreRequestClass() {
        List<String> defaultIIgnoreRequestClassList = new ArrayList<String>();
        defaultIIgnoreRequestClassList.add("org.springframework.web.servlet.mvc.method.annotation");

        return defaultIIgnoreRequestClassList;
    }

    private String getResultTypeByCodeLayer(String codeLayer, String response) {
        String resultType = ResultTypeEnum.Noset.getKey();
        String keyValueFlag = "=";
        for (String bizSuccessConfig : this.getBizSuccessConfigs()) {
            if (StringUtils.isBlank(bizSuccessConfig) || bizSuccessConfig.indexOf(keyValueFlag) == -1) {
                continue;
            }
            int index = bizSuccessConfig.indexOf(keyValueFlag);
            String bizSuccessKey = bizSuccessConfig.substring(0, index);
            String bizSuccessValue = bizSuccessConfig.substring(index + keyValueFlag.length());
            if (bizSuccessKey.equals(codeLayer)) {
                if (response.indexOf(bizSuccessValue) > -1) {
                    resultType = ResultTypeEnum.BizSuccess.getKey();
                } else if ("controller".equals(codeLayer) && response.indexOf("\"code\":") == -1) {
                    resultType = ResultTypeEnum.BizSuccess.getKey();
                } else {
                    resultType = ResultTypeEnum.BizFail.getKey();
                }
                break;
            }
        }
        return resultType;
    }

    private void println(String logContent) {
        if (isOpenSystemOutLog) {
            System.out.println(logContent);
        }
    }

    private Object[] getAndResetArgs(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            return null;
        }
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                continue;
            }
            /*if (arg instanceof GlobalTraceData) {
                setGlobalTraceData((GlobalTraceData) arg, joinPoint);//把参数中的信息放到本线程的GlobalTraceData中
                args[i] = arg;
            }*/
        }
        return args;
    }

    private void setGlobalTraceData(GlobalTraceData globalTraceData, JoinPoint joinPoint) {
        GlobalTraceData gtd = GlobalTraceDataHandler.getGlobalTraceData();
        //当前请求的请求id
        String currentUUID = globalTraceData.getgRequestId();

        //（请求经过的第一个日志节点）
        // 当前线程GlobalTraceData为空，且当前请求参数中的请求id不为空，用参数中的GlobalTraceData信息设置到线程缓存中
        if (gtd == null && globalTraceData.getgRequestId() != null) {
            GlobalTraceDataHandler.setGlobalTraceData(globalTraceData);
            return;
        }

        // TODO: 18/6/12
        //当前线程缓存不为空,同时缓存uuid和当前uuid不一致
        if (gtd != null && !gtd.getgRequestId().equals(currentUUID)) {
            //当前请求uuid为空,用缓存中的值设置当前值
            if (StringUtils.isBlank(currentUUID)) {
                globalTraceData.setgRequestId(gtd.getgRequestId());
                globalTraceData.setgBusinessId(gtd.getgBusinessId());
                globalTraceData.setgDeviceId(gtd.getgDeviceId());
                globalTraceData.setgAppClientVersion(gtd.getgAppClientVersion());
                globalTraceData.setgRequestSource(gtd.getgRequestSource());
                globalTraceData.setgUserAgent(gtd.getgUserAgent());
                globalTraceData.setgRequestUrl(gtd.getgRequestUrl());
                globalTraceData.setgRequestIp(gtd.getgRequestIp());
            }
            //当前请求uuid不为空,用当前值替换缓存中值
            else {
                GlobalTraceDataHandler.setGlobalTraceData(globalTraceData);
            }
        }
    }

    public void afterThrowing(JoinPoint joinPoint) {

    }

    public List<String> getCodeLayerConfigs() {
        return codeLayerConfigs;
    }

    public LogTraceAspect setCodeLayerConfigs(List<String> codeLayerConfigs) {
        this.codeLayerConfigs = codeLayerConfigs;
        return this;
    }

    public List<String> getBizSuccessConfigs() {
        return bizSuccessConfigs;
    }

    public LogTraceAspect setBizSuccessConfigs(List<String> bizSuccessConfigs) {
        this.bizSuccessConfigs = bizSuccessConfigs;
        return this;
    }

}
