package com.supermap.config;

import freemarker.template.TemplateException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;
import java.util.Properties;


/**
 * FreeMarker 配置类
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Configuration
public class FreeMarkerConfig {

    @Bean
    public ViewResolver viewResolverFtl() {
        FreeMarkerViewResolver resolver = init();
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setOrder(0);
        return resolver;
    }

    @Bean
    public ViewResolver viewResolverHtml() {
        FreeMarkerViewResolver resolver = init();
        resolver.setOrder(1);
        resolver.setSuffix(".html");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPath("classpath:/templates/");
        factory.setDefaultEncoding("UTF-8");
        factory.setPreferFileSystemAccess(false);
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        freemarker.template.Configuration configuration = factory.createConfiguration();
        configuration.setClassicCompatible(true);
        result.setConfiguration(configuration);
        Properties settings = new Properties();
        settings.put("template_update_delay", "0");
        settings.put("default_encoding", "UTF-8");
        settings.put("number_format", "0.######");
        settings.put("classic_compatible", true);
        settings.put("template_exception_handler", "ignore");
        result.setFreemarkerSettings(settings);
        return result;
    }

    private FreeMarkerViewResolver init (){
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setViewClass(org.springframework.web.servlet.view.freemarker.FreeMarkerView.class);
        //也可以在配置中引入
        resolver.setRequestContextAttribute("ctx");
        resolver.setExposeRequestAttributes(true);
        resolver.setExposeSessionAttributes(true);
        return resolver;
    }
}
