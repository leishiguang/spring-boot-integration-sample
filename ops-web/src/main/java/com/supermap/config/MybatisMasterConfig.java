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
 * Mybatis master 配置类
 *
 * @author leishiguang
 * @date 2019/04/03
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

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        factoryBean.setDataSource(masterDataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate masterSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(masterSqlSessionFactory());
    }
}
