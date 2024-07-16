/*
 * 51qbiz.id
 * Copyright (C) 2021-${YEAR} All Rights Reserved.
 *
 */
package com.zhouyok.dingbotsdk.util;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;

/**
 * 日志工具类
 *
 * @author buhao
 * @version LogUtil.java, v 0.1 2021-09-29 11:35 buhao
 */
public class LogUtil {
    /**
     * [ERROR]级别日志输出
     *
     * @param logger
     * @param msg    描述信息
     */
    public static void error(Logger logger, String msg) {
        logger.error(msg);
    }

    /**
     * [ERROR]级别日志输出(带异常堆栈信息)
     *
     * @param logger
     * @param msg    描述信息
     * @param t      异常信息
     */
    public static void error(Logger logger, String msg, Throwable t) {
        logger.error(msg, t);
    }

    /**
     * [ERROR]级别日志输出（支持模板字符串）
     *
     * @param logger
     * @param format 描述信息模版字符串（占位符为：{}）
     * @param args   模版字符串的替换变量
     */
    public static void error(Logger logger, String format, Object... args) {
        logger.error(format, args);
    }

    /**
     * [ERROR]级别日志输出（支持模板字符串，且打印异常堆栈信息）
     *
     * @param logger
     * @param format 描述信息模版字符串（占位符为：{}）
     * @param e      异常信息
     * @param args   模版字符串的替换变量
     */
    public static void error(Logger logger, String format, Throwable e, Object... args) {
        if (logger.isErrorEnabled()) {
            logger.error(StrUtil.format(format, args), e);
        }
    }

    /**
     * [INFO]级别日志输出
     *
     * @param logger
     * @param msg    描述信息
     */
    public static void info(Logger logger, String msg) {
        logger.info(msg);
    }

    /**
     * [INFO]级别日志输出(带异常堆栈信息)
     *
     * @param logger
     * @param msg    描述信息
     * @param t      异常信息
     */
    public static void info(Logger logger, String msg, Throwable t) {
        logger.info(msg, t);
    }

    /**
     * [INFO]级别日志输出（支持模板字符串）
     *
     * @param logger
     * @param format 描述信息（支持模版字符串，占位符为：{}）
     * @param args   模版字符串的替换变量
     */
    public static void info(Logger logger, String format, Object... args) {
        logger.info(format, args);
    }

    /**
     * [INFO]级别日志输出（支持模板字符串，且打印异常堆栈信息）
     *
     * @param logger
     * @param format 描述信息（支持模版字符串，占位符为：{}）
     * @param e      异常信息
     * @param args   模版字符串的替换变量
     */
    public static void info(Logger logger, String format, Throwable e, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(StrUtil.format(format, args), e);
        }
    }

    /**
     * [WARN]级别日志输出
     *
     * @param logger
     * @param msg    描述信息
     */
    public static void warn(Logger logger, String msg) {
        logger.warn(msg);
    }

    /**
     * [WARN]级别日志输出(带异常堆栈信息)
     *
     * @param logger
     * @param msg    描述信息
     * @param t      异常信息
     */
    public static void warn(Logger logger, String msg, Throwable t) {
        logger.warn(msg, t);
    }

    /**
     * [WARN]级别日志输出（支持模板字符串）
     *
     * @param logger
     * @param format 描述信息（支持模版字符串，占位符为：{}）
     * @param args   模版字符串的替换变量
     */
    public static void warn(Logger logger, String format, Object... args) {
        logger.warn(format, args);
    }

    /**
     * [WARN]级别日志输出（支持模板字符串，且打印异常堆栈信息）
     *
     * @param logger
     * @param format 描述信息（支持模版字符串，占位符为：{}）
     * @param t      异常信息
     * @param args   模版字符串的替换变量
     */
    public static void warn(Logger logger, String format, Throwable t, Object... args) {
        if (logger.isWarnEnabled()) {
            logger.warn(StrUtil.format(format, args), t);
        }
    }

    /**
     * [DEBUG]级别日志输出
     *
     * @param logger
     * @param msg    描述信息
     */
    public static void debug(Logger logger, String msg) {
        logger.debug(msg);
    }

    /**
     * [DEBUG]级别日志输出(带异常堆栈)
     *
     * @param logger
     * @param msg    描述信息
     * @param t      异常信息
     */
    public static void debug(Logger logger, String msg, Throwable t) {
        logger.debug(msg, t);
    }

    /**
     * [DEBUG]级别日志输出（支持模板字符串）
     *
     * @param logger
     * @param format 描述信息（支持模版字符串，占位符为：{}）
     * @param args   模版字符串的替换变量
     */
    public static void debug(Logger logger, String format, Object... args) {
        logger.debug(format, args);
    }

    /**
     * [DEBUG]级别日志输出（支持模板字符串，且打印异常堆栈信息）
     *
     * @param logger
     * @param format 描述信息（支持模版字符串，占位符为：{}）
     * @param t      异常信息
     * @param args   模版字符串的替换变量
     */
    public static void debug(Logger logger, String format, Throwable t, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(StrUtil.format(format, args), t);
        }
    }
}