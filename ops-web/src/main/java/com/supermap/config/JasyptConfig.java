package com.supermap.config;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableEncryptableProperties
public class JasyptConfig {

    /**
     * 自定义的加密解密 Bean，除密钥以外，其余均为默认配置。
     * bean 名字为 jasyptStringEncryptor 是 jasypt 的默认配置，否则需要在配置文件中手动定义
     */
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
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
        return encryptor;
    }

    /**
     * 换一个名字
     */
    @Bean("encryptor")
    public StringEncryptor encryptorBean() {
        return this.stringEncryptor();
    }

    /**
     * 自定义密文前后的关键词
     */
    @Bean(name = "encryptablePropertyDetector")
    public EncryptablePropertyDetector encryptablePropertyDetector() {
        return new MyEncryptablePropertyDetector();
    }

    /**
     * 自定义密文前后的关键词
     */
    private class MyEncryptablePropertyDetector implements EncryptablePropertyDetector {
        /**
         * 密文前缀
         */
        private final String prefix = "OPTENC(";
        /**
         * 密文后缀
         */
        private final String suffix = ")";

        @Override
        public boolean isEncrypted(String property) {
            if (property == null) {
                return false;
            } else {
                String trimmedValue = property.trim();
                return trimmedValue.startsWith(this.prefix) && trimmedValue.endsWith(this.suffix);
            }
        }

        @Override
        public String unwrapEncryptedValue(String property) {
            return property.substring(this.prefix.length(), property.length() - this.suffix.length());
        }
    }
}
