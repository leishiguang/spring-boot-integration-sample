package com.supermap.config;

import com.supermap.aide.YamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.annotation.Order;


/**
 * 引入自定义配置文件，包括版本与打包信息的读取
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@PropertySources(value = {
        @PropertySource(value = "classpath:version.properties"),
        @PropertySource(value = "classpath:application-common.yml", factory = YamlPropertySourceFactory.class)})
@Configuration
@Order(value = 1)
public class CustomPropertyConfig {
}
