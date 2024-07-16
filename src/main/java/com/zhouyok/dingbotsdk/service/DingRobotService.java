/*
 * 51qbiz.com
 * Copyright (C) 2014-2024 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk.service;

import com.zhouyok.dingbotsdk.domain.result.DingDingTextMessageResult;

/**
 * @author zhouyok * @version DingRobotService.java, v 0.1 2024-07-16 17:39 zhouyok
 */
public interface DingRobotService {
    /**
     * 接受钉钉机器人消息
     *
     * @param requestBody
     * @return
     */
    DingDingTextMessageResult receiveDingDingRobot(String requestBody);
}