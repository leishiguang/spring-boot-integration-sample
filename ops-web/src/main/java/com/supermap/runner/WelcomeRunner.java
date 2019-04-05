package com.supermap.runner;

import com.supermap.aide.MavenEnvironment;
import com.supermap.aide.RuntimeConst;
import com.supermap.aide.RuntimeEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${spring.profiles.active}")
    private String environmentType;

    private final MavenEnvironment mavenEnvironment;
    private final RuntimeEnvironment runtimeEnvironment;

    @Autowired
    public WelcomeRunner(MavenEnvironment mavenEnvironment, RuntimeEnvironment runtimeEnvironment) {
        this.mavenEnvironment = mavenEnvironment;
        this.runtimeEnvironment = runtimeEnvironment;
    }

    @Override
    public void run(String... args){
        if (RuntimeConst.Profiles.dev == runtimeEnvironment.getProfiles()) {
            log.info("开启：调试模式 ----------------------------!");
        }
        log.info("启动完成，当前版本：{}，环境：{}，端口号：{}", mavenEnvironment.getProjectVersion(), environmentType, serverPort);
        log.info("欢迎你，{}.{}", runtimeEnvironment.getComputerName(), runtimeEnvironment.getUserName());
    }
}
