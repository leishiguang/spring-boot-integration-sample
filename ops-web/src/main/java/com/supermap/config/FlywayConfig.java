package com.supermap.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * flyway 的配置类，使用 masterDataSource 的url、username、password；
 */
@Configuration
@AutoConfigureAfter({DruidDataSourceConfig.class,MybatisMasterConfig.class})
public class FlywayConfig {

    private final DataSource masterDataSource;

    @Autowired
    public FlywayConfig(DataSource masterDataSource) {
        this.masterDataSource = masterDataSource;
    }

    @Bean("masterFlyway")
    public Flyway masterFlyway(){
        DruidDataSource masterDataSource = (DruidDataSource) this.masterDataSource;
        return Flyway.configure()
                .dataSource(masterDataSource.getUrl(), masterDataSource.getUsername(), masterDataSource.getPassword())
                .locations("filesystem:db/master/migration")
                .load();
    }
}
