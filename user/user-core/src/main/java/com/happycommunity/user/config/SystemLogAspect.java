package com.happycommunity.user.config;

import com.happycommunity.framework.core.log.trace.LogTraceAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Danny
 * @Title: SystemLogAspect
 * @Description:
 * @Created on 2018-12-06 21:14:52
 */
@Configuration
@Aspect
public class SystemLogAspect {

    private LogTraceAspect logTraceAspect;

    public SystemLogAspect() {
        logTraceAspect = new LogTraceAspect();
        List<String> codeLayerConfigs = new ArrayList<String>();
        codeLayerConfigs.add(".service.=service");
        codeLayerConfigs.add(".dao.=dao");
        List<String> bizSuccessConfigs = new ArrayList<String>();
        bizSuccessConfigs.add("service=\"businessResult\":\"SUCCESS\"");
        bizSuccessConfigs.add("dubbo=\"businessResult\":\"SUCCESS\"");
        logTraceAspect.setCodeLayerConfigs(codeLayerConfigs);
        logTraceAspect.setBizSuccessConfigs(bizSuccessConfigs);
        logTraceAspect.setBizSystem("happycommunity");
        logTraceAspect.setBizService("com.happycommunity.user");
        logTraceAspect.setLogger("com.happycommunity.user.service");
    }

    /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com.happycommunity.user.service..*.*(..)) or execution(* com.happycommunity.user.dao..*.*(..))")
    public void executeService() {
    }

    @Around("executeService()")
    public Object logTrace(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return logTraceAspect.logTrace(proceedingJoinPoint);
    }
}
