package com.supermap.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 需要对外暴露的配置，可以设置默认值，设置是否加密。
 */
@Data
@Component
@ConfigurationProperties(prefix="property")
public class PropertyItems {
    /**
     * 是否需要更新
     */
    private boolean needUpdate;
    /**
     * 在配置文件中定义
     */
    private List<PropertyItem> items;

}
