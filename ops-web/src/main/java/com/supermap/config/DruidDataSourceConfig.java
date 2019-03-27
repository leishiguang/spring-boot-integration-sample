package com.supermap.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Configuration
public class DruidDataSourceConfig {
    @Value("${spring.profiles.active}")
    private String debugType;

    @Bean(name = "masterDataSource")
    @Qualifier("masterDataSource")
    public DataSource masterDataSource() {
        String url = "dev".equals(debugType) ? "jdbc:h2:file:./db/h2/database-dev" : "jdbc:h2:file:./db/h2/database-product";
        String driverClass = "org.h2.Driver";
        String username = "opsmaster";
        String password = "masterdatasource";
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClass);
        //连接池初始化连接数量
        druidDataSource.setInitialSize(5);
        //连接池最大活跃连接数
        druidDataSource.setMaxActive(100);
        //最小空闲数
        druidDataSource.setMinIdle(5);
        //最大等待时间
        druidDataSource.setMaxWait(60000);
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        //配置一个连接在池中最小生存的时间，单位是毫秒
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        //连接是否有效的查询语句
        druidDataSource.setValidationQuery("SELECT 1 FROM DUAL");
        druidDataSource.setTestOnBorrow(true);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestWhileIdle(false);
        //打开PSCache，并且指定每个连接上PSCache的大小
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(50);
        druidDataSource.setRemoveAbandoned(true);
        //通过connectProperties属性来打开mergeSql功能；慢SQL记录
        //druidDataSource.setConnectProperties(this.connectionProperties);
        //合并多个DruidDataSource的监控数据
        druidDataSource.setUseGlobalDataSourceStat(true);
        try {
            //配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
            druidDataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        return druidDataSource;
    }

}
