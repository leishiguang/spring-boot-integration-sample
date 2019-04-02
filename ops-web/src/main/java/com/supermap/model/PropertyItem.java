package com.supermap.model;


import lombok.Data;

@Data
public class PropertyItem {
    /**
     * 配置名称
     */
    private String name;
    /**
     * 配置默认值
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

}