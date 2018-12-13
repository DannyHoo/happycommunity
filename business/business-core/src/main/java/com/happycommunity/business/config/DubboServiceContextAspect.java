package com.happycommunity.business.config;

import com.alibaba.dubbo.rpc.RpcContext;
import com.happycommunity.framework.common.model.model.GlobalTraceData;
import com.happycommunity.framework.core.log.trace.GlobalTraceDataHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Danny
 * @Title: DubboServiceContextAspect
 * @Description: 调用Dubbo之前对RpcContext进行处理，Dubbo服务端用Dubbo Filter接收
 * @Created on 2018-12-08 22:07:38
 */
@Component
@Aspect
public class DubboServiceContextAspect {

    @Pointcut("execution(* com.happycommunity.business.service..*.*(..))")
    public void setContextBeforeInvokeDubbo() {
    }

    @Before("setContextBeforeInvokeDubbo()")
    public void setContext(JoinPoint joinPoint) {
        GlobalTraceData globalTraceData = GlobalTraceDataHandler.getGlobalTraceData();
        if (globalTraceData != null) {
            Map<String, String> context = new HashMap<String, String>();
            context.put("gRequestId", globalTraceData.getgRequestId());
            context.put("gBusinessId", globalTraceData.getgBusinessId());
            context.put("gDeviceId", globalTraceData.getgDeviceId());
            context.put("gRequestSource", globalTraceData.getgRequestSource());
            context.put("gUserAgent", globalTraceData.getgUserAgent());
            context.put("gRequestUrl", globalTraceData.getgRequestUrl());
            context.put("gRequestIp", globalTraceData.getgRequestIp());
            context.put("gAppClientVersion", globalTraceData.getgAppClientVersion());
            RpcContext.getContext().setAttachments(context);
        }
    }
}
