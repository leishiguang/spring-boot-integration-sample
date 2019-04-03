package test.java.com.supermap;

import com.supermap.Application;
import com.supermap.model.JasyptEncryptableDetector;
import com.supermap.model.PropertyItem;
import com.supermap.model.PropertyItems;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * 测试类
 *
 * @author leishiguang
 * @date 2019/04/03
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Autowired
    ApplicationContext appCtx;

    @Autowired
    StringEncryptor encryptor;

    @Autowired
    PropertyItems propertyItem;

    @Autowired
    JasyptEncryptableDetector detector;
    /**
     * 注入原始文件资源
     */
    @Value("classpath:/application.properties")
    Resource appPropertiesFile;

    private Properties configProp;

    /**
     * 加密方式不应当发生变化
     */
    @Test
    public void encryptorMethodNotChangeTest(){
        String encryptedText = "xX548GDIMYGWWTlS0RNcGM8rvF1r7RSy";
        assertEquals("127.0.0.1:1521",encryptor.decrypt(encryptedText));
        encryptedText = "twodHADutmbh1Heq3ZM/AF6gCOyntdVx";
        assertEquals("password",encryptor.decrypt(encryptedText));
    }

    /**
     * 配置文件应当完成加密
     */
    @Test
    public void encryptorPropertiesTest() throws Exception {
        Environment env = appCtx.getEnvironment();
        String name,value;
        for(PropertyItem item:propertyItem.getItems()){
            if(item.isEncrypt()){
                name = item.getName();
                value = getProperty(name);
                //配置中的所有待加密配置，均已加密
                assertTrue(detector.isEncrypted(value));
                //读取到的配置，与解密服务解出来的一致
                assertEquals(env.getProperty(name),encryptor.decrypt(detector.unwrapEncryptedValue(value)));
            }
        }
    }

    /**
     * 读取原始配置文件
     */
    private String getProperty(String key) throws Exception {
        if(configProp == null){
            configProp = new Properties();
            try (InputStream in = appPropertiesFile.getInputStream()) {
                configProp.load(in);
            }
        }
        return configProp.getProperty(key);
    }

}
