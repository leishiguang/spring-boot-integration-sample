package com.supermap.runner;

import com.supermap.model.JasyptEncryptableDetector;
import com.supermap.model.PropertyItem;
import com.supermap.model.PropertyItems;
import com.supermap.util.CreateFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;


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
     * 读取配置中的文件
     */
    private PropertyItems propertyItems;
    private final StringEncryptor encryptor;
    private final JasyptEncryptableDetector detector;
    private final ApplicationContext appCtx;

    @Autowired
    public JasyptRunner(StringEncryptor encryptor, JasyptEncryptableDetector encryptablePropertyDetector, PropertyItems propertyItem, ApplicationContext appCtx) {
        this.encryptor = encryptor;
        this.detector = encryptablePropertyDetector;
        this.propertyItems = propertyItem;
        this.appCtx = appCtx;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("开始：检查配置文件是否需要加密 -------------");
        Properties configProp;
        //注入原始文件资源
        Resource appPropertiesResource = new FileSystemResource("config/application.properties");
        //文件不存在，以当前环境进行载入
        if (!appPropertiesResource.exists()) {
            configProp = readDefaultProperties();
        } else {
            configProp = readProperties(appPropertiesResource);
        }
        //检查是否加密
        if (checkEncryptedItem(configProp)) {
            //根据配置信息进行加密
            fillExistItem(configProp);
            writeBackFile(appPropertiesResource);
        }
        //help GC
        propertyItems = null;
        log.info("已完成：配置文件加密检查 ------------------!");
    }

    /**
     * 载入默认信息
     */
    private Properties readDefaultProperties() {
        Environment env = appCtx.getEnvironment();
        Properties properties = new Properties();
        for (PropertyItem item : propertyItems.getItems()) {
            properties.setProperty(item.getName(), env.getProperty(item.getName()));
        }
        return properties;
    }


    /**
     * 从资源中加载原始配置文件
     */
    private Properties readProperties(Resource resource) throws IOException {
        Properties configProp = new Properties();
        try (InputStream in = resource.getInputStream()) {
            configProp.load(in);
        }
        return configProp;
    }

    /**
     * 检查哪些配置需要加密，是否需要替换配置文件
     */
    private boolean checkEncryptedItem(Properties configProp) {
        //如果需要更新，直接返回
        if (propertyItems.isNeedUpdate()) {
            return true;
        }
        //开始检查配置，是否需要更新
        for (PropertyItem item : propertyItems.getItems()) {
            item.setValue(configProp.getProperty(item.getName()));
            //标记为空
            configProp.setProperty(item.getName(), "");
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
    private void fillExistItem(Properties configProp) {
        Iterator<String> iterator = configProp.stringPropertyNames().iterator();
        String key;
        String value;
        while (iterator.hasNext()) {
            key = iterator.next();
            value = configProp.getProperty(key);
            if (value != null && !"".equals(value)) {
                propertyItems.getItems().add(new PropertyItem(key, value));
            }
        }
    }

    /**
     * 写回文件
     */
    private void writeBackFile(Resource appPropertiesResource) throws IOException {
        log.info("开始：正在加密配置文件 ---------------------");
        log.debug(appPropertiesResource.getURI().toString());
        File file = appPropertiesResource.getFile();
        String newFilePathName = file.getAbsolutePath();
        boolean pass = false;
        if (!file.exists()) {
            pass = CreateFileUtil.createFile(newFilePathName);
        } else if (file.delete()) {
            log.debug("已删除原始文件");
            pass = true;
        }
        if (pass) {
            file = new File(newFilePathName);
            try (OutputStream out = new FileOutputStream(file)) {
                out.write("## OPT配置文件\n".getBytes(StandardCharsets.UTF_8));
                out.write(("## " + new Date().toString() + "\n").getBytes(StandardCharsets.UTF_8));
                for (PropertyItem item : propertyItems.getItems()) {
                    out.write(("## " + item.getCommand() + "\n").getBytes(StandardCharsets.UTF_8));
                    out.write((item.getName() + "=" + item.getValue() + "\n").getBytes(StandardCharsets.UTF_8));
                }
                log.info("已完成：已替换为加密后的配置文件 ----------!");
            }
        } else {
            log.warn("未完成：未对配置文件加密 ------------------!");
        }
    }

}
