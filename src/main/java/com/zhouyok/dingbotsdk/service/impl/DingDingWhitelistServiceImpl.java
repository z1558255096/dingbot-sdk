/*
 * 51qbiz.com
 * Copyright (C) 2014-2024 All Rights Reserved.
 */
package com.zhouyok.dingbotsdk.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zhouyok.dingbotsdk.config.DingDingRobotProperties;
import com.zhouyok.dingbotsdk.service.DingDingWhitelistService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author zhouyok
 * @version DingDingWhitelistServiceImpl.java, v 0.1 2024-07-16 16:26 zhouyok
 */
@Service
public class DingDingWhitelistServiceImpl implements DingDingWhitelistService {
    private final Set<String> whitelist = new CopyOnWriteArraySet<>();
    @Resource
    DingDingRobotProperties dingDingRobotProperties;

    @PostConstruct
    public void init() {
        String whiteList = dingDingRobotProperties.getWhiteList();
        if (!StrUtil.isBlank(whiteList)) {
            String[] split = whiteList.split(",");
            List<String> list = Arrays.asList(split);
            // 将List转换为Set
            Set<String> set = new CopyOnWriteArraySet<>(list);
            this.initWhitelist(set);
        }
    }

    @Override
    public boolean isUserInWhitelist(String userId) {
        return whitelist.contains(userId);
    }

    @Override
    public Set<String> getWhitelist() {
        return new HashSet<>(whitelist);
    }

    @Override
    public void initWhitelist(Set<String> whitelist) {
        this.whitelist.clear();
        this.whitelist.addAll(whitelist);
    }

    @Override
    public void addToWhitelist(String userId) {
        this.whitelist.add(userId);
    }

    @Override
    public void removeFromWhitelist(String userId) {
        this.whitelist.remove(userId);
    }

}