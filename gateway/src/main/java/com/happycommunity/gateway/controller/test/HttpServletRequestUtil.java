package com.happycommunity.gateway.controller.test;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: HttpServletRequestUtil
 * @Description: <br>
 */
public class HttpServletRequestUtil {


    public static Map<String, String> getRequestParameters(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> result = new HashMap<>();
        for (String key : map.keySet()) {
            result.put(key, map.get(key)[0]);
        }
        return result;
    }

    public static String getRequestBody(HttpServletRequest request) throws IOException {

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        return data;
    }

    public static Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String)headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}
