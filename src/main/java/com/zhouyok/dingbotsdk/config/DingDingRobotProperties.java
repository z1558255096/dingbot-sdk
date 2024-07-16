/*
 * 51qbiz.com
 * Copyright (C) 2014-2024 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhouyok
 * @version DingDingRobotProperties.java, v 0.1 2024-07-06 13:10 zhouyok
 */
@ConfigurationProperties(prefix = "dingding.robot")
public class DingDingRobotProperties {
    /**
     * 应用程序密钥
     */
    private String appKey;
    /**
     * 应用程序密钥
     */
    private String appSecret;
    /**
     * 是否开启自带机器人能力 默认为True。
     * 如果想自己定义接受逻辑的话可以关闭然后自己实现
     */
    private Boolean enabled = true;

    /**
     * 白名单 默认为空 配置在配置文件中用逗号分割
     * 如果为空则所有人都可以@，可以自定义白名单
     * @see com.zhouyok.dingbotsdk.service.DingDingWhitelistService#addToWhitelist(String)
     */
    private String whiteList = "";
    // getters and setters
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(String whiteList) {
        this.whiteList = whiteList;
    }
}