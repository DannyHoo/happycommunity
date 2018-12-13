package com.happycommunity.gateway.filter;

import com.alibaba.dubbo.rpc.RpcContext;
import com.happycommunity.framework.common.model.model.GlobalTraceData;
import com.happycommunity.framework.core.log.trace.GlobalTraceDataHandler;
import com.happycommunity.framework.core.util.IPUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Danny
 * @Title: GatewayRequestFilter
 * @Description:
 * @Created on 2018-12-07 23:44:29
 */
public class GatewayRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
        this.setGlobalTraceData(httpServletRequest);
        httpServletResponse.setHeader("gRequestId",GlobalTraceDataHandler.getGlobalTraceData().getgRequestId());
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    public void destroy() {

    }

    private GlobalTraceData setGlobalTraceData(HttpServletRequest httpServletRequest) {
        String userAgent = this.getUserAgent(httpServletRequest);
        GlobalTraceData globalTraceData = GlobalTraceData.newInstance()
                //.setgBusinessId("")
                .setgDeviceId(this.getDeviceId(httpServletRequest))
                .setgUserAgent(userAgent)
                .setgRequestSource(this.getRequestSource(userAgent))
                .setgRequestIp(this.getRemoteIp(httpServletRequest))
                .setgRequestUrl(this.getRequestUrl(httpServletRequest));
        GlobalTraceDataHandler.setGlobalTraceData(globalTraceData);
        setDubboContext(globalTraceData);
        return globalTraceData;
    }

    private void setDubboContext(GlobalTraceData globalTraceData) {
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

    protected String getDeviceId(HttpServletRequest request) {
        return UUID.randomUUID().toString();
    }

    protected String getRequestUrl(HttpServletRequest request) {
        String url = request.getRequestURI();
        return url;
    }

    protected String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    protected String getRequestSource(String userAgent) {
        return userAgent;
    }

    protected String getRemoteIp(HttpServletRequest request) {
        return IPUtils.getRemoteIp(request);
    }

}
