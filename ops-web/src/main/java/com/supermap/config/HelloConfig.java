package com.supermap.config;

import com.supermap.aspect.HelloAnnotationAspect;
import com.supermap.aspect.HelloMethodAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {

    @Bean("helloMethodAspect")
    public HelloMethodAspect getHelloMethodAspect(){
        return new HelloMethodAspect();
    }

    @Bean("helloAnnotationAspect")
    public HelloAnnotationAspect getHelloAnnotationAspect(){
        return new HelloAnnotationAspect();
    }
}
