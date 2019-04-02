package com.supermap.runner;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Spring-boot 启动之后，程序自动运行，用于启动后的初始化。
 * 本例，初始化数据库
 */
@Slf4j
@Component
@Order(value = 1)
public class DatabaseCommandRunner implements CommandLineRunner {

    private final Flyway masterFlyway;

    @Value("${spring.profiles.active}")
    private String debugType;

    @Autowired
    public DatabaseCommandRunner(Flyway masterFlyway) {
        this.masterFlyway = masterFlyway;
    }

    @Override
    public void run(String... args){
        this.initMasterDataBase();
    }

    /**
     * 初始化 master 数据库
     */
    private void initMasterDataBase()throws FlywayException{
        log.debug("开始：master 数据源自动升级 ---------");
        try{
            masterFlyway.migrate();
        }catch (FlywayException flywayException){
            if("dev".equals(debugType)){
                log.warn("初始化 master 数据库失败，正在重试。");
                masterFlyway.clean();
                masterFlyway.migrate();
                log.warn("已完成 master 数据库的重新初始化。");
            }else {
                log.error("数据库迁移文件与生产环境不匹配且无法自动完成升级",flywayException);
                throw flywayException;
            }
        }
        log.info("已完成：master 数据源初始化 ---------!");
    }
}
