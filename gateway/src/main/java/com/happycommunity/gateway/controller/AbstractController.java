package com.happycommunity.gateway.controller;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Danny
 * @Title: AbstractController
 * @Description:
 * @Created on 2018-11-26 22:37:04
 */
public class AbstractController {

    protected String getRequestBody(HttpServletRequest request) throws IOException {

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        return data;
    }

}
