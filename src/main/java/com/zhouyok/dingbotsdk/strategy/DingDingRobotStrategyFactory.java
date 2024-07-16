/*
 * 51qbiz.com
 * Copyright (C) 2014-2024 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk.strategy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouyok * @version DingDingRobotStrategyFactory.java, v 0.1 2024-07-06 13:18 zhouyok
 */
@Service
public class DingDingRobotStrategyFactory implements InitializingBean, ApplicationContextAware {

    /**
     * 钉钉机器人策略
     */
    private static final Map<String, DingDingRobotStrategy<? extends DingDingRobotMessageType>> DING_DING_ROBOT_STRATEGY_MAP = new HashMap<>();

    private ApplicationContext applicationContext;

    public DingDingRobotStrategy<? extends DingDingRobotMessageType> get(String messageType) {
        return DING_DING_ROBOT_STRATEGY_MAP.get(messageType);
    }

    @Override
    public void afterPropertiesSet() {
        applicationContext.getBeansOfType(DingDingRobotStrategy.class)
                .values()
                .forEach(strategy -> DING_DING_ROBOT_STRATEGY_MAP.put(strategy.getDingDingRobotMessageType().getType(), strategy));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}