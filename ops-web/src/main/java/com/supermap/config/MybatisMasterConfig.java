package com.supermap.config;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Configuration
@MapperScan(basePackages = "com.supermap.mapper", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MybatisMasterConfig {

    @Autowired
    @Qualifier("masterDS") //来自DataSourceConfig
    private DataSource masterDS;

    /**
     * 会话工厂
     */
    @Bean
    public SqlSessionFactory masterSqlSessionFactory() throws Exception {
        log.debug("开始装载 mastSqlSessionFactory");
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(masterDS); // 设置数据源
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate masterSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(masterSqlSessionFactory());
    }
}
