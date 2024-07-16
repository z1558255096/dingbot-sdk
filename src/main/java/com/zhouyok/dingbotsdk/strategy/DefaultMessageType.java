package com.zhouyok.dingbotsdk.strategy;

/**
 * 默认消息类型
 *
 * @author zhouyok
 * @date 2024/07/16
 */
public enum DefaultMessageType implements DingDingRobotMessageType {
    /**
     * 默认信息
     */
    DEFAULT("默认消息", "00"),
    ;
    /**
     * -- GETTER --
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    private final String name;
    /**
     * -- GETTER --
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    private final String value;

    DefaultMessageType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    @Override
    public String getType() {
        return this.value;
    }
}
