package com.happycommunity.framework.mq;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author Danny
 * @Title: AbstractSpringTest
 * @Description:
 * @Created on 2019-02-19 18:13:57
 */
@ContextConfiguration(locations = {"classpath:/framework-rocketmq-test.xml"})
public class AbstractSpringTest extends AbstractJUnit4SpringContextTests {

}
