package com.supermap.model;

import lombok.Data;

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

    public PropertyItem(){

    }

    public PropertyItem(String key, String value){
        this.name = key;
        this.value = value;
        this.command = key;
    }
}