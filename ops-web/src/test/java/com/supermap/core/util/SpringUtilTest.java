package com.supermap.core.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.supermap.Application;
import com.supermap.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * SpringUtil 测试类
 *
 * @author leishiguang
 * @date 2019/04/05
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringUtilTest {


    @Test
    public void getBean() {
        assertNotNull(SpringUtil.getBean("masterDataSource"));
    }

    @Test(expected = NoUniqueBeanDefinitionException.class)
    public void couldNotGetQualifyingBean() {
        assertNotNull(SpringUtil.getBean(DruidDataSource.class));
    }

    @Test
    public void getBean2() {
        assertNotNull(SpringUtil.getBean("masterDataSource", DruidDataSource.class));
    }
}