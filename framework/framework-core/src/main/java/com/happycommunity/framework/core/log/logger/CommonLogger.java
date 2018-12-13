package com.happycommunity.framework.core.log.logger;

/**
 * @author Danny
 * @Title: CommonLogger
 * @Description:
 * @Created on 2018-12-06 17:57:17
 */
public interface CommonLogger {

    public void info(String message);

    public void info(String format, Object... params);
}
