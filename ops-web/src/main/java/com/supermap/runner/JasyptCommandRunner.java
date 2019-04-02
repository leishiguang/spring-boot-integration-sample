package com.supermap.runner;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * 对未加密的一些关键配置，自动加密，当前测试使用
 */
@Slf4j
@Component
@Order(value = 2)

@EnableEncryptableProperties
public class JasyptCommandRunner implements CommandLineRunner {

    private final StringEncryptor encryptor;
    private final ApplicationContext appCtx;

    @Value("${debug}")
    private boolean isDebug;

    @Autowired
    public JasyptCommandRunner(StringEncryptor encryptor, ApplicationContext appCtx) {
        this.encryptor = encryptor;
        this.appCtx = appCtx;
    }

    @Override
    public void run(String... args) {
        if(isDebug){
            test();
        }
    }

    private void test() {
        log.debug("开始：加解密的验证 --------------");
        Environment env = appCtx.getEnvironment();
        log.debug("env.getProperty -------------------");
        log.debug("jasypt.test.encryptd1 : {}", env.getProperty("jasypt.test.encryptd1"));
        try{
            log.debug("jasypt.test.encryptd2 : {}", env.getProperty("jasypt.test.encryptd2"));
        }catch (Exception e){
            log.error("读取密文发生错误",e);
        }
        log.debug("jasypt.test.text1 :{}", env.getProperty("jasypt.test.text1"));
        log.debug("jasypt.test.text2 : {}", env.getProperty("jasypt.test.text2"));
        log.debug("encrypt and decrypt : -------------------");
        String encryptedText1 = encryptor.encrypt(env.getProperty("jasypt.test.text1"));
        log.debug("encrypted text1 : {}", encryptedText1);
        log.debug("encrypted text1 : {}", encryptor.decrypt(encryptedText1));
        log.debug("已完成：加解密的验证 --------------!");
    }
}
