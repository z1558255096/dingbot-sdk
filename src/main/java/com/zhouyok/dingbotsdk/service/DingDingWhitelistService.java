/*
 * 51qbiz.com
 * Copyright (C) 2014-2024 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk.service;

import java.util.Set;

/**
 * @author zhouyok * @version DingDingWhitelistService.java, v 0.1 2024-07-16 16:25 zhouyok
 */
public interface DingDingWhitelistService {

    /**
     * 判断用户是否在白名单中
     *
     * @param userId 用户ID
     * @return true 如果用户在白名单中，否则返回false
     */
    boolean isUserInWhitelist(String userId);

    /**
     * 获取当前白名单中的用户ID
     *
     * @return 白名单用户ID集合
     */
    Set<String> getWhitelist();

    /**
     * 初始化白名单
     *
     * @param whitelist 白名单用户ID集合
     */
    void initWhitelist(Set<String> whitelist);

    /**
     * 添加用户到白名单
     *
     * @param userId 用户ID
     */
    void addToWhitelist(String userId);

    /**
     * 从白名单移除用户
     *
     * @param userId 用户ID
     */
    void removeFromWhitelist(String userId);
}
