package com.happycommunity.framework.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author huyuyang@lxfintech.com
 * @Title: ExceptionUtils
 * @Description:
 * @Created on 2018-06-12 10:38:34
 */
public class ExceptionUtils {

    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
        return null;
    }
}
