/*
 * 51qbiz.com
 * Copyright (C) 2014-2024 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk.strategy;

/**
 * @author zhouyok
 * @version DingDingRobotStrategy.java, v 0.1 2024-07-06 13:18 zhouyok
 */
public interface DingDingRobotStrategy<T extends DingDingRobotMessageType> {

    /**
     * 获取钉钉机器人消息类型
     *
     * @return {@link T }
     */
    T getDingDingRobotMessageType();

    /**
     * 处理机器人消息
     *
     * @param content 内容
     * @return {@link String }
     */
    String handleDingDingRobot(String content);
}