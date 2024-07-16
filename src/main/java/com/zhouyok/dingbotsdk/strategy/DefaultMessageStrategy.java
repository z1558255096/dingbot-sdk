/*
 * 51qbiz.com
 * Copyright (C) 2014-2024 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk.strategy;

/**
 * @author zhouyok * @version DefaultMessageStrategy.java, v 0.1 2024-07-16 16:17 zhouyok
 */
public class DefaultMessageStrategy implements DingDingRobotStrategy<DefaultMessageType> {

    @Override
    public DefaultMessageType getDingDingRobotMessageType() {
        return DefaultMessageType.DEFAULT;
    }

    @Override
    public String handleDingDingRobot(String content) {
        return "Handled text message: " + content;
    }
}