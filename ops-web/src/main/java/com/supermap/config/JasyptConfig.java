package com.supermap.config;

import com.supermap.model.JasyptEncryptableDetector;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 加密服务相关配置类
 *
 * @author leishiguang
 * @date 2019/04/03
 */
@Slf4j
@Configuration
@EnableEncryptableProperties
public class JasyptConfig {

    private StringEncryptor stringEncryptor;

    /**
     * 自定义的加密解密 Bean，除密钥以外，其余均为默认配置。
     * bean 名字为 jasyptStringEncryptor 是 jasypt 的默认配置，否则需要在配置文件中手动定义
     */
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        return this.initStringEncryptor();
    }

    /**
     * 换一个名字
     */
    @Bean("encryptor")
    public StringEncryptor encryptorBean() {
        return this.initStringEncryptor();
    }

    /**
     * 自定义密文前后的关键词，bean 的名称应当采用默认配置
     */
    @Bean(name = "encryptablePropertyDetector")
    public JasyptEncryptableDetector encryptablePropertyDetector() {
        return new JasyptEncryptableDetector();
    }

    /**
     * 初始化加密组件
     */
    private StringEncryptor initStringEncryptor(){
        if(stringEncryptor == null){
            //加密密钥，不允许修改，以免加密解密失败。
            String jasyptPrivateKey = "OPS_TOOL";
            PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
            SimpleStringPBEConfig config = new SimpleStringPBEConfig();
            config.setPassword(jasyptPrivateKey);
            config.setAlgorithm("PBEWithMD5AndDES");
            config.setKeyObtentionIterations("1000");
            config.setPoolSize("1");
            config.setProviderName("SunJCE");
            config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
            config.setIvGeneratorClassName("org.jasypt.salt.NoOpIVGenerator");
            config.setStringOutputType("base64");
            encryptor.setConfig(config);
            stringEncryptor = encryptor;
        }
        return stringEncryptor;
    }
}
