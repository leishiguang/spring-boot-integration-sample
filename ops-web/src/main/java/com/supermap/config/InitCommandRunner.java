package com.supermap.config;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * Spring-boot 启动之后，程序自动运行，用于启动后的初始化
 */
@Slf4j
@Component
public class InitCommandRunner implements CommandLineRunner {

    private final Flyway masterFlyway;

    @Autowired
    public InitCommandRunner(Flyway masterFlyway) {
        this.masterFlyway = masterFlyway;
    }

    @Override
    public void run(String... args) throws Exception {
        //masterFlyway.clean();
        masterFlyway.migrate();
        log.info("已初始化 master 数据源");
    }
}
