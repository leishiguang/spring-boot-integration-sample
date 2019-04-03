package com.supermap.config;

import com.supermap.aspect.HelloAnnotationAspect;
import com.supermap.aspect.HelloMethodAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 关于Aop的案例
 *
 * @author leishiguang
 * @date 2019/03/15
 */
@Configuration
public class HelloAopConfig {

    @Bean("helloMethodAspect")
    public HelloMethodAspect getHelloMethodAspect(){
        return new HelloMethodAspect();
    }

    @Bean("helloAnnotationAspect")
    public HelloAnnotationAspect getHelloAnnotationAspect(){
        return new HelloAnnotationAspect();
    }
}
