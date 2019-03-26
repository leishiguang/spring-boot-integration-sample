package com.supermap.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {


    //此处使用自动装配的 H2 数据源,可自定义数据源
    @Bean("masterDS")
    @Qualifier("masterDS")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dataSourceMaster() {
        //return new DataSourceProperties().initializeDataSourceBuilder().build();
        return DataSourceBuilder.create().build();
    }
}
