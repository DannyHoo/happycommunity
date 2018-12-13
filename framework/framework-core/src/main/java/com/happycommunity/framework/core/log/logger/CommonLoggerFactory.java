package com.happycommunity.framework.core.log.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Danny
 * @Title: CommonLoggerFactory
 * @Description:
 * @Created on 2018-12-06 17:59:29
 */
public class CommonLoggerFactory {

    public static CommonLogger getCommonLogger(Class clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
        return new CommonLoggerImpl(logger);
    }

    public static CommonLogger getCommonLogger(String name) {
        Logger logger = LoggerFactory.getLogger(name);
        return new CommonLoggerImpl(logger);
    }
}
