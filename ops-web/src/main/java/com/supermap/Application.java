package com.supermap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * OPS 运维工具启动类
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@EnableWebMvc
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        FlywayAutoConfiguration.class
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Server start success.");
    }
}
