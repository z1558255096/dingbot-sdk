/*
 * 51qbiz.com
 * Copyright (C) 2014-2024 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk;

import com.zhouyok.dingbotsdk.config.DingDingRobotProperties;
import com.zhouyok.dingbotsdk.strategy.DingDingRobotStrategyFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhouyok * @version DingbotAutoConfiguration.java, v 0.1 2024-07-06 13:16 zhouyok
 */
@Configuration
@ComponentScan(basePackages = "com.zhouyok.dingbotsdk")
@EnableAutoConfiguration
public class DingbotAutoConfiguration {
}