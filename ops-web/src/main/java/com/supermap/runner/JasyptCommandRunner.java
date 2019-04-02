package com.supermap.runner;

import com.supermap.model.JasyptEncryptableDetector;
import com.supermap.model.PropertyItem;
import com.supermap.model.PropertyItems;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;
import java.util.Properties;


/**
 * 对未加密的一些关键配置，自动加密，当前测试使用
 */
@Slf4j
@Component
@Order(value = 2)
public class JasyptCommandRunner implements CommandLineRunner {

    private final StringEncryptor encryptor;
    private final ApplicationContext appCtx;
    private Properties configProp;

    /**
     * 读取配置中的文件
     */
    private PropertyItems propertyItems;

    @Value("${debug}")
    private boolean isDebug;

    /**
     * 注入原始文件资源
     */
    @Value("classpath:/application.properties")
    private Resource appPropertiesFile;

    private final JasyptEncryptableDetector detector;

    @Autowired
    public JasyptCommandRunner(StringEncryptor encryptor, ApplicationContext appCtx, JasyptEncryptableDetector encryptablePropertyDetector, PropertyItems propertyItem) {
        this.encryptor = encryptor;
        this.appCtx = appCtx;
        this.detector = encryptablePropertyDetector;
        this.propertyItems = propertyItem;
    }

    @Override
    public void run(String... args) throws Exception {
        if (isDebug) {
            debugTest();
        }
        log.debug("开始：检查配置文件是否需要加密 --------------");
        readPropertiesFile();
        checkEncryptedItem();
        writeBackFile();
        log.debug("已完成：配置文件的加密检查 -------------!");
    }

    /**
     * 测试加解密服务是否可用
     */
    private void debugTest() {
        log.debug("开始：加解密的验证 --------------");
        Environment env = appCtx.getEnvironment();
        log.debug("env.getProperty -------------------");
        log.debug("jasypt.test.encryptd1 : {}", env.getProperty("jasypt.test.encryptd1"));
        try {
            log.debug("jasypt.test.encryptd2 : {}", env.getProperty("jasypt.test.encryptd2"));
        } catch (Exception e) {
            log.error("读取密文发生错误", e);
        }
        log.debug("encrypt and decrypt : -------------------");
        log.debug("source text :{}", env.getProperty("jasypt.test.text"));
        String encryptedText = encryptor.encrypt(env.getProperty("jasypt.test.text"));
        log.debug("encrypted text : {}", encryptedText);
        log.debug("decrypted text : {}", encryptor.decrypt(encryptedText));
        log.debug("已完成：加解密的测试验证 --------------!");
    }

    /**
     * 读取原始配置文件
     */
    private void readPropertiesFile() {
        configProp = new Properties();
        try {
            InputStream in = appPropertiesFile.getInputStream();
            configProp.load(in);
            in.close();
        } catch (IOException e) {
            log.error("读取原始配置文件失败", e);
        }
    }

    /**
     * 检查哪些配置需要更新
     */
    private void checkEncryptedItem() {
        //开始检查配置，是否需要更新
        for (PropertyItem item : propertyItems.getItems()) {
            item.setValue(getProperty(item.getName()));
            //需要加密但是未加密，进行一次加密
            if (item.isEncrypt() && !detector.isEncrypted(item.getValue())) {
                item.setValue(detector.wrapEncryptedValue(encryptor.encrypt(item.getValue())));
                propertyItems.setNeedUpdate(true);
            }
        }
    }

    /**
     * 写回文件
     */
    private void writeBackFile() throws IOException {
        if (propertyItems.isNeedUpdate()) {
            log.info("开始：正在加密配置文件 --------");
            File file = appPropertiesFile.getFile();
            String newFilePathName = file.getPath();
            if (file.delete()) {
                file = new File(newFilePathName);
                OutputStream out = new FileOutputStream(file);
                // write into it
                out.write("#OPT配置文件\n".getBytes());
                out.write(("#" + new Date().toString() + "\n").getBytes());
                for (PropertyItem item : propertyItems.getItems()) {
                    out.write(("\n#" + item.getCommand() + "\n").getBytes());
                    out.write((item.getName() + "=" + item.getValue() + "\n").getBytes());
                }
                out.close();
                log.info("已完成：已替换为加密后的配置文件 --------!");
            }
        }
        //完成，置空便于垃圾回收
        propertyItems = null;
    }


    private String getProperty(String key) {
        return configProp.getProperty(key);
    }

}
