package com.happycommunity.framework.core.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author huyuyang@lxfintech.com
 * @Title: IPUtils
 * @Description:
 * @Created on 2018-06-12 10:14:22
 */
public class IPUtils {
    /**
     * 获取客户端访问ip
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(isIllegalIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(isIllegalIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(isIllegalIp(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if(isIllegalIp(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(isIllegalIp(ip)) {
            ip = request.getRemoteAddr();
        }
        //多个ip情况,获取第一个有效ip
        if(ip != null && ip.indexOf(",") > 0) {
            ip = getIpFromMulitIp(ip);
        }
        return ip;
    }

    /**
     * 从多个ip中获取第一个真实有效ip
     * @param ips
     * @return
     */
    private static String getIpFromMulitIp(String ips) {
        if(ips == null || ips.indexOf(",")==-1) {
            return ips;
        }
        String ipSplit[] = ips.split(",");
        for(String ip: ipSplit) {
            if(!isIllegalIp(ip)) {
                return ip;
            }
        }
        return ips;
    }

    /**
     * 非法ip
     * @param ip
     * @return
     */
    private static boolean isIllegalIp(String ip) {
        String unknown = "unknown";
        if(StringUtils.isBlank(ip) ||unknown.equalsIgnoreCase(ip)) {
            return true;
        }
        return false;
    }

    /**
     * 获取本地ip
     * @return
     */
    public static String getLocalIP() {
        String ip = "";
        try {
            Enumeration e1 = (Enumeration) NetworkInterface.getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) e1.nextElement();
                if (!ni.getName().equals("eth0") && !ni.getName().equals("en0")) {
                    continue;
                }
                Enumeration e2 = ni.getInetAddresses();
                while (e2.hasMoreElements()) {
                    InetAddress ia = (InetAddress) e2.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;
                    }
                    ip = ia.getHostAddress();
                    break;
                }
                if(StringUtils.isNotBlank(ip)) {
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
