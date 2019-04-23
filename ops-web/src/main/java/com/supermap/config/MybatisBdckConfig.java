package com.supermap.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * 不动产库的数据源配置类
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Configuration
@MapperScan(basePackages = "com.supermap.mapper.bdck", sqlSessionFactoryRef = "bdckSqlSessionFactory")
public class MybatisBdckConfig {

    private final DataSource bdckDataSource;

    @Autowired
    public MybatisBdckConfig(@Qualifier("bdckDataSource") DruidDataSource bdckDataSource) {
        this.bdckDataSource = bdckDataSource;
    }

    /**
     * bdck 会话工厂
     */
    @Bean("bdckSqlSessionFactory")
    public SqlSessionFactory bdckSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        factoryBean.setDataSource(bdckDataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/bdck/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate bdckSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(bdckSqlSessionFactory());
    }
}
