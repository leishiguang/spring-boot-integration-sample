package com.supermap.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 需要对运维人员暴露的配置项集合，从 property 配置中自动装配
 *
 * @author leishiguang
 * @date 2019/04/03
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
