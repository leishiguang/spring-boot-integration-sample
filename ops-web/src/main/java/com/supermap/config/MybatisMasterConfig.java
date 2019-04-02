package com.supermap.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 运维工具 master 数据源的配置类
 */
@Configuration
@MapperScan(basePackages = "com.supermap.mapper.master", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MybatisMasterConfig {

    private final DataSource masterDataSource;

    @Autowired
    public MybatisMasterConfig(@Qualifier("masterDataSource") DruidDataSource masterDataSource) {
        this.masterDataSource = masterDataSource;
    }

    /**
     * 会话工厂
     */
    @Bean("masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory() throws Exception {
        //log.debug("开始装载 mastSqlSessionFactory");
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(masterDataSource); // 设置数据源
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate masterSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(masterSqlSessionFactory());
    }
}
