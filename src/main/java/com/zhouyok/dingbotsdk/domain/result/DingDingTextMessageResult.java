/*
 * 51qbiz.id
 * Copyright (C) 2021-2023 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk.domain.result;

import com.zhouyok.dingbotsdk.domain.model.DingDingMessageReceiveModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 钉钉文本消息推送响应类
 *
 * @author zhouyok
 * @version DingDingMessageBaseResponse.java, v 0.1 2023-05-30 11:04 zhouyok
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class DingDingTextMessageResult {

    private String msgtype;
    private TextDTO text;
    private String userId;
    /**
     * 请求原文
     */
    private String requestContent;
    /**
     * 原始进程查询键
     */
    private String originalProcessQueryKey;
    private DingDingMessageReceiveModel dingDingMessageReceiveModel;
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    @Data
    public static class TextDTO {
        private String content;
    }
}