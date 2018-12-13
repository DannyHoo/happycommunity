package com.happycommunity.user.config;

import com.alibaba.dubbo.rpc.*;
import com.happycommunity.framework.common.model.model.GlobalTraceData;
import com.happycommunity.framework.core.log.trace.GlobalTraceDataHandler;

import java.util.Map;

/**
 * @author Danny
 * @Title: DubboContextFilter
 * @Description:
 * @Created on 2018-12-08 22:25:54
 */
public class DubboContextFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Map<String, String> context = RpcContext.getContext().getAttachments();
        GlobalTraceData globalTraceData = getGlobalTraceData(context);
        GlobalTraceDataHandler.setGlobalTraceData(globalTraceData);
        return invoker.invoke(invocation);
    }

    private GlobalTraceData getGlobalTraceData(Map<String, String> context) {
        GlobalTraceData globalTraceData = new GlobalTraceData();
        if (context != null) {
            globalTraceData.setgRequestId(context.get("gRequestId"))
                    .setgBusinessId(context.get("gBusinessId"))
                    .setgDeviceId(context.get("gDeviceId"))
                    .setgRequestSource(context.get("gRequestSource"))
                    .setgUserAgent(context.get("gUserAgent"))
                    .setgRequestUrl(context.get("gRequestUrl"))
                    .setgRequestIp(context.get("gRequestIp"))
                    .setgAppClientVersion(context.get("gAppClientVersion"));
        }
        return globalTraceData;
    }

}
