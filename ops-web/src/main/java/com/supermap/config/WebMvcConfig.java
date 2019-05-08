package com.supermap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * WebMvc 配置类
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
        //设置ViewController的优先级,将此处的优先级设为最高,当存在相同映射时依然优先执行
        viewControllerRegistry.addRedirectViewController("/", "/views/index.html");
        viewControllerRegistry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(viewControllerRegistry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .setCachePeriod(0);*/
        //将所有/plugin/** 访问都映射到classpath:/plugin/ 目录下
        registry.addResourceHandler("/views/**")
                .addResourceLocations("classpath:/views/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
