package com.supermap.config;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * 用以检查bean的初始化过程
 *
 * @author leishiguang
 * @date 2019/04/05
 */

@Configuration
@Slf4j
public class ZzzBeanFactory implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        log.debug("对象---" + beanName + "---初始化开始");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("对象---" + beanName + "---初始化成功");
        return bean;
    }
}