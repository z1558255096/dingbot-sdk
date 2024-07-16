/*
 * 51qbiz.id
 * Copyright (C) 2021-2023 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 叮叮消息接收模型
 *
 * @author zhouyok
 * @version DingDingMessageReceiveModel.java, v 0.1 2023-05-30 11:23 zhouyok
 * @date 2023/05/30
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class DingDingMessageReceiveModel {

    /**
     * msgtype 目前只支持text。
     */
    private String msgtype;
    /**
     * 消息文本。
     */
    private TextDTO text;
    /**
     *
     * 加密的消息ID。
     */
    private String msgId;
    /**
     * 消息的时间戳，单位ms。
     */
    private Long createAt;
    /**
     * 谈话类型 1：单聊2：群聊
     */
    private String conversationType;
    /**
     * 加密的会话ID。
     */
    private String conversationId;
    /**
     * 谈话标题
     * 群聊时才有的会话标题。
     */
    private String conversationTitle;
    /**
     * 加密的发送者ID。
     */
    private String senderId;
    /**
     *
     * 发送者昵称。
     */
    private String senderNick;
    /**
     * 企业内部群有的发送者当前群的企业corpId。
     */
    private String senderCorpId;
    /**
     * 企业内部群有的发送者在企业内的userid。
     */
    private String senderStaffId;
    /**
     * 加密的机器人ID。
     */
    private String chatbotUserId;
    /**
     * 被@人的信息dingtalkId：加密的发送者IDstaffId：企业内部群有的发送者在企业内的userid。
     */
    private List<AtUsersDTO> atUsers;
    private String  sessionWebhook;
    private String originalProcessQueryKey;

    /**
     * 文本dto
     *
     * @author zhouyok
     * @date 2023/05/30
     */
    @NoArgsConstructor
    @Data
    public static class TextDTO {
        /**
         * 内容
         */
        private String content;
    }

    /**
     * 在用户dto
     *
     * @author zhouyok
     * @date 2023/05/30
     */
    @NoArgsConstructor
    @Data
    public static class AtUsersDTO {
        /**
         * dingtalk id
         */
        private String dingtalkId;
        /**
         * 员工id
         */
        private String staffId;
    }
}