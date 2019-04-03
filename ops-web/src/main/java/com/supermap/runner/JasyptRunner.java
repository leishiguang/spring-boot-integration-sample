package com.supermap.runner;

import com.supermap.model.JasyptEncryptableDetector;
import com.supermap.model.PropertyItem;
import com.supermap.model.PropertyItems;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;


/**
 * 对未加密的一些关键配置进行自动加密
 *
 * @author leishiguang
 * @date 2019/4/3
 */
@Slf4j
@Component
@Order(value = 2)
public class JasyptRunner implements CommandLineRunner {

    @Value("#{ ('dev' eq '${spring.profiles.active}') && '${debug}'}")
    private boolean isDebug;

    /**
     * 注入原始文件资源
     */
    @Value("./config/application.properties")
    private Resource appPropertiesFile;



    /**
     * 读取配置中的文件
     */
    private PropertyItems propertyItems;
    private Properties configProp;
    private final StringEncryptor encryptor;
    private final JasyptEncryptableDetector detector;

    @Autowired
    public JasyptRunner(StringEncryptor encryptor, JasyptEncryptableDetector encryptablePropertyDetector, PropertyItems propertyItem) {
        this.encryptor = encryptor;
        this.detector = encryptablePropertyDetector;
        this.propertyItems = propertyItem;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("开始：检查配置文件是否需要加密 -------------");
        readPropertiesFile();
        if (checkEncryptedItem()) {
            fillExistItem();
            writeBackFile();
        }
        log.info("已完成：配置文件加密检查 ------------------!");
    }


    /**
     * 读取原始配置文件
     */
    private void readPropertiesFile() throws Exception {
        if(appPropertiesFile == null){
            appPropertiesFile = new FileSystemResource("");
        }
        configProp = new Properties();
        try (InputStream in = appPropertiesFile.getInputStream()) {
            configProp.load(in);
        }
    }

    /**
     * 检查哪些配置需要加密，是否需要替换配置文件
     */
    private boolean checkEncryptedItem() {
        //开始检查配置，是否需要更新
        for (PropertyItem item : propertyItems.getItems()) {
            item.setValue(getProperty(item.getName()));
            //标记为空
            clearProperty(item.getName());
            //需要加密但是未加密，进行一次加密
            if (item.isEncrypt() && !detector.isEncrypted(item.getValue())) {
                item.setValue(detector.wrapEncryptedValue(encryptor.encrypt(item.getValue())));
                propertyItems.setNeedUpdate(true);
            }
        }
        return propertyItems.isNeedUpdate();
    }

    /**
     * 已存在的配置，如果未包含在内，则需要填充进来
     */
    private void fillExistItem() {
        Iterator<String> iterator = getAllPropertyNames().iterator();
        String key;
        String value;
        while (iterator.hasNext()) {
            key = iterator.next();
            value = getProperty(key);
            if (value != null && !"".equals(value)) {
                propertyItems.getItems().add(new PropertyItem(key, value));
            }
        }
    }

    /**
     * 写回文件
     */
    private void writeBackFile() throws IOException {
        log.info("开始：正在加密配置文件 ---------------------");
        File file = appPropertiesFile.getFile();
        String newFilePathName = file.getPath();
        if (file.delete()) {
            file = new File(newFilePathName);
            try (OutputStream out = new FileOutputStream(file)) {
                out.write("## OPT配置文件\n".getBytes(StandardCharsets.UTF_8));
                out.write(("## " + new Date().toString() + "\n").getBytes(StandardCharsets.UTF_8));
                for (PropertyItem item : propertyItems.getItems()) {
                    out.write(("## " + item.getCommand() + "\n").getBytes(StandardCharsets.UTF_8));
                    out.write((item.getName() + "=" + item.getValue() + "\n").getBytes(StandardCharsets.UTF_8));
                }
            }
            log.info("已完成：已替换为加密后的配置文件 ----------!");
        }
        //完成，置空便于垃圾回收
        propertyItems = null;
    }


    private String getProperty(String key) {
        return configProp.getProperty(key);
    }

    private void clearProperty(String key) {
        configProp.setProperty(key, "");
    }

    private Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }
}
