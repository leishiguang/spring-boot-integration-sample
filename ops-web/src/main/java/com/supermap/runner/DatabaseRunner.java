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
 * 初始化数据库，Spring-boot 启动之后，程序自动运行
 *
 * @author leishiguang
 * @date 2019/03/15
 */
@Slf4j
@Component
@Order(value = 20)
public class DatabaseRunner implements CommandLineRunner {

    private final Flyway masterFlyway;

    @Value("#{ ('dev' eq '${spring.profiles.active}') && '${debug}'}")
    private boolean isDebug;

    @Autowired
    public DatabaseRunner(Flyway masterFlyway) {
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
        log.debug("开始：master 数据库自动升级 ----------------");
        try{
            masterFlyway.migrate();
        }catch (FlywayException flywayException){
            if(isDebug){
                log.warn("重试：master 数据库自动升级 ----------------");
                masterFlyway.clean();
                masterFlyway.migrate();
                log.warn("已完成：master 数据库重新初始化 -----------!");
            }else {
                log.error("数据库迁移文件与生产环境不匹配且无法自动完成升级",flywayException);
                throw flywayException;
            }
        }
        log.info("已完成：master 数据库自动升级 -------------!");
    }
}
