package com.supermap.core.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.supermap.util.SpringUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * SpringUtil 测试类
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */

@RunWith(JUnitPlatform.class)
@SpringBootTest
@DisplayName("SpringBean工具类")
class SpringUtilTest {

    @Test
    @DisplayName("使用名字，获取bean")
    void getBean() {
        assertNotNull(SpringUtil.getBean("masterDataSource"));
    }

    @Test
    @DisplayName("无法获取重名bean")
    void couldNotGetQualifyingBean() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> SpringUtil.getBean(DruidDataSource.class));
    }

    @Test
    @DisplayName("使用名字+类，获取bean")
    void getBean2() {
        assertNotNull(SpringUtil.getBean("masterDataSource", DruidDataSource.class));
    }
}