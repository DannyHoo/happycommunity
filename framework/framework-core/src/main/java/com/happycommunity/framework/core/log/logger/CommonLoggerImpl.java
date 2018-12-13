package com.happycommunity.framework.core.log.logger;

import org.slf4j.Logger;

/**
 * @author Danny
 * @Title: CommonLoggerImpl
 * @Description:
 * @Created on 2018-12-06 20:41:28
 */
public class CommonLoggerImpl implements CommonLogger{
    private Logger logger;

    public CommonLoggerImpl(Logger logger) {
        this.logger = logger;
    }

    public void info(String message) {
        logger.info(message);
    }

    public void info(String format, Object... params) {
        logger.info(format,params);
    }
}
