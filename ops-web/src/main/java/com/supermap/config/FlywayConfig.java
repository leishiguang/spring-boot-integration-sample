package com.supermap.config;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * flyway （数据库迁移）配置类
 *
 * @author leishiguang
 * @date 2019/03/15
 */
@Configuration
@AutoConfigureAfter({DruidDataSourceConfig.class,MybatisMasterConfig.class})
public class FlywayConfig {

    @Value("#{masterDataSource.getUrl()}")
    private String masterDbUrl;
    @Value("#{masterDataSource.getUsername()}")
    private String masterDbUsername;
    @Value("#{masterDataSource.getPassword()}")
    private String masterDbPassword;
    @Value("filesystem:db/master/migration")
    private String masterDbLocation;

    @Bean("masterFlyway")
    public Flyway masterFlyway(){
        return Flyway.configure()
                .dataSource(masterDbUrl, masterDbUsername, masterDbPassword)
                .locations(masterDbLocation)
                .load();
    }
}
