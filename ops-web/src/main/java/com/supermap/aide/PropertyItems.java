package com.supermap.aide;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 需要对运维人员暴露的配置项集合，从 application-common.yml 配置的 property 中自动装配
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "property")
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
