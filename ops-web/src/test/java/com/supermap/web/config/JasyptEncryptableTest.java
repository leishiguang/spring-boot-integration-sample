package com.supermap.web.config;

import com.supermap.aide.JabotEncryptDetector;
import com.supermap.aide.PropertyItem;
import com.supermap.aide.PropertyItems;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 测试加密服务
 * 
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@RunWith(JUnitPlatform.class)
@SpringBootTest
@DisplayName("加密服务类")
class JasyptEncryptableTest {

    @Autowired
    ApplicationContext appCtx;

    @Autowired
    StringEncryptor encryptor;

    @Autowired
    PropertyItems propertyItem;

    @Autowired
    JabotEncryptDetector detector;

    /**
     * 注入原始文件资源
     */
    private static Resource appPropertiesFile;

    private Properties configProp;

    @BeforeAll
    static void beforeClass() {
        appPropertiesFile = new FileSystemResource("config/application.properties");
    }

    @Test
    @DisplayName("加密方式不应当发生变化")
    void encryptorMethodNotChangeTest() {
        String encryptedText = "xX548GDIMYGWWTlS0RNcGM8rvF1r7RSy";
        assertEquals("127.0.0.1:1521", encryptor.decrypt(encryptedText));
        encryptedText = "twodHADutmbh1Heq3ZM/AF6gCOyntdVx";
        assertEquals("password", encryptor.decrypt(encryptedText));
    }

    @Test
    @DisplayName("配置文件应当完成加密")
    void encryptorPropertiesTest() throws Exception {
        Environment env = appCtx.getEnvironment();
        String name, value;
        for (PropertyItem item : propertyItem.getItems()) {
            if (item.isEncrypt()) {
                name = item.getName();
                value = getProperty(name);
                //配置中的所有待加密配置，均已加密
                assertTrue(detector.isEncrypted(value));
                //读取到的配置，与解密服务解出来的一致
                assertEquals(env.getProperty(name), encryptor.decrypt(detector.unwrapEncryptedValue(value)));
            }
        }
    }

    /**
     * 读取原始配置文件
     */
    private String getProperty(String key) throws Exception {
        if (configProp == null) {
            configProp = new Properties();
            try (InputStream in = appPropertiesFile.getInputStream()) {
                configProp.load(in);
            }
        }
        return configProp.getProperty(key);
    }

}