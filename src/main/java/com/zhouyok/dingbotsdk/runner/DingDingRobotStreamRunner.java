/*
 * 51qbiz.com
 * Copyright (C) 2014-2024 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk.runner;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import com.zhouyok.dingbotsdk.config.DingDingRobotProperties;
import com.zhouyok.dingbotsdk.domain.model.DingDingMessageReceiveModel;
import com.zhouyok.dingbotsdk.domain.result.DingDingTextMessageResult;
import com.zhouyok.dingbotsdk.service.DingDingWhitelistService;
import com.zhouyok.dingbotsdk.service.DingRobotService;
import com.zhouyok.dingbotsdk.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author zhouyok * @version DingDingRobotStreamRunner.java, v 0.1 2024-07-16 16:33 zhouyok
 */
@Component
@Slf4j
public class DingDingRobotStreamRunner implements ApplicationRunner {
    @Resource
    DingDingRobotProperties dingDingRobotProperties;
    @Resource
    DingRobotService dingRobotService;
    public static final String TOPIC = "/v1.0/im/bot/messages/get";
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("启动DingDingStreamRunner");
        OpenDingTalkStreamClientBuilder
                .custom()
                .credential(new AuthClientCredential(dingDingRobotProperties.getAppKey(), dingDingRobotProperties.getAppSecret()))
                //注册机器人监听器
                .registerCallbackListener(TOPIC, robotMessage -> {
                    log.info("receive robotMessage, {}", robotMessage);
                    //处理机器人回调
                    DingDingTextMessageResult dingDingTextMessageResult = dingRobotService.receiveDingDingRobot(JSONUtil.toJsonStr(robotMessage));
                    OapiRobotSendRequest robotSendRequest = new OapiRobotSendRequest();
                    OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
                    at.setAtUserIds(Collections.singletonList(dingDingTextMessageResult.getUserId()));
                    robotSendRequest.setAt(at);
                    robotSendRequest.setMsgtype("text");
                    OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
                    text.setContent(dingDingTextMessageResult.getText().getContent());
                    robotSendRequest.setText(text);
                    if (StrUtil.isNotBlank(text.getContent())) {
                        replyToDingtalk("TEXT", dingDingTextMessageResult.getText()
                                .getContent(), dingDingTextMessageResult.getDingDingMessageReceiveModel());
                    }
                    return new JSONObject();
                })
                .build().start();
    }
    public void replyToDingtalk(String msgType, String msg, DingDingMessageReceiveModel r)  {
        String atUser = r.getSenderStaffId();
        if (StrUtil.isEmpty(atUser)) {
            msg = msg + "\n\n@" + r.getSenderNick();
        }

        JSONObject msgTmp;
        switch (msgType) {
            case "TEXT":
                msgTmp = createTextMessage(msg, atUser);
                break;
            case "MARKDOWN":
                if (!StrUtil.isEmpty(atUser) && !"1".equals(r.getConversationType())) {
                    msg = msg + "\n\n@" + atUser;
                }
                msgTmp = createMarkDownMessage("Markdown Msg", msg, atUser);
                break;
            default:
                msgTmp = createTextMessage(msg, atUser);
        }

        try {
            String jsonData = msgTmp.toString();
            String response = HttpUtil.post(r.getSessionWebhook(), jsonData);

            // 处理响应
            JSONObject jsonResponse = JSONUtil.parseObj(response);
            LogUtil.info( log,"钉钉机器人webhook响应: ",jsonResponse.toString());
        } catch (Exception e) {
            LogUtil.error(log,"钉钉机器人webhook响应异常:",e);
        }
    }

    private JSONObject createTextMessage(String content, String atUser){
        JSONObject textMessage = new JSONObject();
        textMessage.put("msgtype", "text");

        JSONObject text = new JSONObject();
        text.put("content", content);
        textMessage.put("text", text);

        JSONObject at = new JSONObject();
        at.put("atUserIds", new String[]{atUser});
        textMessage.put("at", at);

        return textMessage;
    }

    private JSONObject createMarkDownMessage(String title, String text, String atUser)  {
        JSONObject markDownMessage = new JSONObject();
        markDownMessage.put("msgtype", "markdown");

        JSONObject markDown = new JSONObject();
        markDown.put("title", title);
        markDown.put("text", text);
        markDownMessage.put("markdown", markDown);

        JSONObject at = new JSONObject();
        at.put("atUserIds", new String[]{atUser});
        markDownMessage.put("at", at);

        return markDownMessage;
    }
}