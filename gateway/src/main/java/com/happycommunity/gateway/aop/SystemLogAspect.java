package com.happycommunity.gateway.aop;

import com.happycommunity.framework.core.log.trace.LogTraceAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Danny
 * @Title: SystemLogAspect
 * @Description:
 * @Created on 2018-12-09 16:31:04
 */
@Component
public class SystemLogAspect {

    private LogTraceAspect logTraceAspect;

    public SystemLogAspect() {
        logTraceAspect = new LogTraceAspect();
        List<String> codeLayerConfigs = new ArrayList<String>();
        codeLayerConfigs.add(".controller.=controller");
        List<String> bizSuccessConfigs = new ArrayList<String>();
        bizSuccessConfigs.add("controller=\"code\":10000");
        logTraceAspect.setCodeLayerConfigs(codeLayerConfigs);
        logTraceAspect.setBizSuccessConfigs(bizSuccessConfigs);
        logTraceAspect.setBizSystem("happycommunity");
        logTraceAspect.setBizService("gateway");
        logTraceAspect.setLogger("com.happycommunity.gateway.controller");
    }

    public Object logTrace(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return logTraceAspect.logTrace(proceedingJoinPoint);
    }

    public void afterThrowing(JoinPoint joinPoint){

    }
}
