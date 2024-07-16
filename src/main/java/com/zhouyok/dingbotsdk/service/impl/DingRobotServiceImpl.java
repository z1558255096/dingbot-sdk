/*
 * 51qbiz.com
 * Copyright (C) 2014-2024 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zhouyok.dingbotsdk.domain.model.DingDingMessageReceiveModel;
import com.zhouyok.dingbotsdk.domain.result.DingDingTextMessageResult;
import com.zhouyok.dingbotsdk.service.DingDingWhitelistService;
import com.zhouyok.dingbotsdk.service.DingRobotService;
import com.zhouyok.dingbotsdk.strategy.DingDingRobotStrategy;
import com.zhouyok.dingbotsdk.strategy.DingDingRobotStrategyFactory;
import com.zhouyok.dingbotsdk.util.LogUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouyok * @version DingRobotServiceImpl.java, v 0.1 2024-07-16 17:41 zhouyok
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DingRobotServiceImpl implements DingRobotService {
    final DingDingRobotStrategyFactory dingRobotStrategyFactory;
    final DingDingWhitelistService dingDingWhitelistService;
    /**
     * 接受钉钉机器人消息
     *
     * @param requestBody
     * @return
     */
    @Override
    public DingDingTextMessageResult receiveDingDingRobot(String requestBody) {
        DingDingMessageReceiveModel dingDingMessageReceiveModel = JSONUtil.toBean(requestBody, DingDingMessageReceiveModel.class);
        String dingtalkContent = dingDingMessageReceiveModel.getText().getContent();
        // 判断发送的人是否在白名单内
        if(!dingDingWhitelistService.isUserInWhitelist(dingDingMessageReceiveModel.getSenderId())) {
            LogUtil.error(log, "其他人@了钉钉机器人,senderStaffId:{}", dingDingMessageReceiveModel.getSenderStaffId());
            return DingDingTextMessageResult.builder()
                    .msgtype("text")
                    .requestContent(dingtalkContent)
                    .originalProcessQueryKey(dingDingMessageReceiveModel.getOriginalProcessQueryKey())
                    .text(
                            DingDingTextMessageResult.TextDTO.builder()
                                    .content("你还没有权限。请联系相关人员给你配置，你的id是"+dingDingMessageReceiveModel.getSenderId())
                                    .build()
                    ).build();
        }
        LogUtil.info(log, "receiveDingDingRobot >> 接收到钉钉消息   >> requestBody = {}, dingtalkContent = {}", requestBody, dingtalkContent);
        String content = delimiterProcessing(StrUtil.trim(dingtalkContent));
        LogUtil.info(log, "receiveDingDingRobot >> 接收到钉钉消息(处理后)   >> requestBody = {}, content = {}", requestBody, content);
        String trim = StrUtil.isBlank(StrUtil.trim(content)) ? "0|" : StrUtil.trim(content);
        // 根据逗号分割获取参数
        List<String> split = StrUtil.split(trim, "|", true, false);
        String strategyType = StrUtil.trim(split.get(0));
        DingDingRobotStrategy dingDingRobotStrategy = dingRobotStrategyFactory.get(strategyType);
        if (dingDingRobotStrategy == null) {
            return DingDingTextMessageResult.builder()
                    .msgtype("text")
                    .requestContent(dingtalkContent)
                    .originalProcessQueryKey(dingDingMessageReceiveModel.getOriginalProcessQueryKey())
                    .text(
                            DingDingTextMessageResult.TextDTO.builder()
                                    .content("")
                                    .build()
                    ).build();
        }
        // 补齐分割符
        if (split.get(0).equals(trim)) {
            trim = trim + "|";
        }
        String response = dingDingRobotStrategy.handleDingDingRobot(ObjectUtil.defaultIfNull(StrUtil.subAfter(trim, split.get(0) + "|", false), ""));
        return DingDingTextMessageResult.builder()
                .msgtype("text")
                .userId(dingDingMessageReceiveModel.getSenderStaffId())
                .text(
                        DingDingTextMessageResult.TextDTO.builder()
                                .content(response)
                                .build()
                )
                .dingDingMessageReceiveModel(dingDingMessageReceiveModel).build();
    }
    /**
     * 分隔符处理
     *
     * @param message 消息
     * @return {@link String }
     * @author 卜浩
     */
    private static String delimiterProcessing(String message) {
        if (StrUtil.isBlank(message)) {
            return "";
        }
        return StrUtil.replaceIgnoreCase(message, getDelimiter(message), "|");
    }

    /**
     * 获得分隔符
     * 遍历字符串，找到第一位不为数字的字符
     *
     * @param message 消息
     * @return {@link String }
     * @author 卜浩
     */
    private static String getDelimiter(String message) {
        if (StrUtil.isBlank(message)) {
            return "";
        }
        return StrUtil.sub(ReUtil.delPre("^\\d+", message), 0, 1);
    }
}