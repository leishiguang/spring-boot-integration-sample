package com.supermap.aide;

import lombok.Getter;
import lombok.ToString;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 系统运行时候的属性
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Getter
@ToString
@Component
@Order
public class RuntimeEnvironment {

    /**
     * 操作系统名称
     */
    String osName;
    /**
     * 操作系统的架构
     */
    String osArch;
    /**
     * 操作系统版本
     */
    String osVersion;

    /**
     * Java 虚拟机实现版本
     */
    String javaVmVersion;
    /**
     * Java 虚拟机实现供应商
     */
    String javaVmVendor;
    /**
     * Java 虚拟机实现名称
     */
    String javaVmName;
    /**
     * Java 运行时环境规范版本
     */
    String javaSpecificationVersion;
    /**
     * Java 虚拟机规范版本
     */
    String javaVmSpecificationVersion;
    /**
     * Java 运行时环境版本
     */
    String javaVersion;
    /**
     * 用户的当前工作目录
     */
    String userDir;
    /**
     * 用户的主目录
     */
    String userHome;
    /**
     * 用户的账户名称
     */
    String userName;

    /**
     * 计算机名称
     */
    String computerName;

    /**
     * 文件分隔符
     */
    String fileSeparator;

    /**
     * 当前运行环境（生产、开发、测试）
     */
    RuntimeConst.Profiles profiles;

    public RuntimeEnvironment() {
        this.addSystemProperty();
        this.addSystemEnv();
        this.addApplicationProperty();
    }

    /**
     * 添加应用属性
     */
    private void addApplicationProperty() {
        profiles = RuntimeConst.Profiles.valueOf("dev");

        /*ApplicationContext appCtx = SpringUtil.getBean(ApplicationContext.class);
        Environment appEnv = appCtx.getEnvironment();
        if(appEnv != null){
            profiles = RuntimeConst.Profiles.valueOf(appEnv.getProperty("spring.profiles.active"));
        }*/
    }

    /**
     * 添加系统属性
     */
    private void addSystemProperty() {
        osArch = get("os.arch");
        osName = get("os.name");
        osVersion = get("os.version");

        userDir = get("user.dir");
        userHome = get("user.home");
        userName = get("user.name");

        javaVmVersion = get("java.vm.version");
        javaVmVendor = get("java.vm.vendor");
        javaVmName = get("java.vm.name");
        javaSpecificationVersion = get("java.specification.version");
        javaVmSpecificationVersion = get("java.vm.specification.version");
        javaVersion = get("java.version");

        fileSeparator = get("file.separator");
    }

    /**
     * 添加系统环境
     */
    private void addSystemEnv() {
        Map<String, String> sysEnv = System.getenv();
        computerName = getComputerNamePrivate(sysEnv);
    }

    private String getComputerNamePrivate(Map<String, String> sysEnv) {
        String key1 = "COMPUTERNAME";
        String key2 = "USERDOMAIN";
        String key3 = "HOSTNAME";
        String defaultValue = "UnknownComputer";
        if (sysEnv.containsKey(key1)) {
            return sysEnv.get(key1);
        } else if (sysEnv.containsKey(key2)) {
            return sysEnv.get(key2);
        } else {
            return sysEnv.getOrDefault(key3, defaultValue);
        }
    }

    private String get(String key) {
        return System.getProperty(key);
    }
}
