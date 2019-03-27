package com.supermap.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {
    /**
     * 如果采用此装配，配置文件中的 url: jdbc:h2:mem:h2test 需要改成 jdbc-url: jdbc:h2:mem:h2test
     */
    @Bean("masterDS")
    @Qualifier("masterDS")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dataSourceMaster() {
        return DataSourceBuilder.create().build();
    }

}
