package com.supermap.model;

import lombok.Data;

/**
 * 需要对运维人员暴露的配置项
 *
 * @author leishiguang
 * @date 2019/04/03
 */
@Data
public class PropertyItem {
    /**
     * 配置名称
     */
    private String name;
    /**
     * 配置值
     */
    private String value;
    /**
     * 配置的说明
     */
    private String command;
    /**
     * 配置是否需要加密
     */
    private boolean encrypt;

    /**
     * 初始化 name 、key
     */
    public PropertyItem edit(String key, String value) {
        this.name = key;
        this.value = value;
        this.command = key;
        return this;
    }
}