package com.happycommunity.business.config;

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
 * @Description: 系统日志AOP
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
        List<String> bizSuccessConfigs = new ArrayList<String>();
        bizSuccessConfigs.add("service=\"businessResult\":\"SUCCESS\"");
        bizSuccessConfigs.add("dubbo=\"businessResult\":\"SUCCESS\"");
        logTraceAspect.setCodeLayerConfigs(codeLayerConfigs);
        logTraceAspect.setBizSuccessConfigs(bizSuccessConfigs);
        logTraceAspect.setBizSystem("happycommunity");
        logTraceAspect.setBizService("business");
        logTraceAspect.setLogger("com.happycommunity.business.service");
    }

    /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com.happycommunity.business.service..*.*(..))")
    public void executeService() {
    }

    @Around("executeService()")
    public Object logTrace(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return logTraceAspect.logTrace(proceedingJoinPoint);
    }
}
