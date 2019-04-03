package com.supermap.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 展示一些欢迎信息，或是增加版本号……
 *
 * @author leishiguang
 * @date 2019/03/15
 */
@Slf4j
@Component
@Order(value = 99)
public class WelcomeRunner implements CommandLineRunner {

    @Value("${server.port}")
    private String serverPort;

    @Value("#{ ('dev' eq '${spring.profiles.active}') && '${debug}'}")
    private boolean isDebug;

    @Value("${spring.profiles.active}")
    private String environmentType;

    @Override
    public void run(String... args){
        if(isDebug){
            log.info("开启：调试模式 ----------------------------!");
        }
        log.info("启动完成，当前环境：{}，端口号：{}",environmentType,serverPort);
    }
}
