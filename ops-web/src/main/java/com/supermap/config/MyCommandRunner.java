package com.supermap.config;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * Spring-boot 启动之后，程序自动运行，用于启动后的初始化
 */
@Slf4j
@Component
public class MyCommandRunner implements CommandLineRunner {

    private final Flyway masterFlyway;

    @Value("${spring.profiles.active}")
    private String debugType;

    @Autowired
    public MyCommandRunner(Flyway masterFlyway) {
        this.masterFlyway = masterFlyway;
    }

    @Override
    public void run(String... args) throws Exception {
        this.initMasterDataBase();
    }

    private void initMasterDataBase()throws FlywayException{
        try{
            masterFlyway.migrate();
        }catch (FlywayException flywayException){
            if("dev".equals(debugType)){
                log.warn("初始化 master 数据库失败，正在重试。");
                masterFlyway.clean();
                masterFlyway.migrate();
                log.warn("已完成 master 数据库的重新初始化。");
            }else {
                log.error("初始化 master 数据库失败",flywayException);
                throw flywayException;
            }
        }
        log.info("已初始化 master 数据源");
    }
}
