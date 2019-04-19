package com.supermap.runner;

import com.supermap.aide.MavenEnvironment;
import com.supermap.aide.RuntimeEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 检查打包时的环境与运行时的环境
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Slf4j
@Component
@Order(value = 1)
public class CheckEnvironmentRunner implements CommandLineRunner {

    private final MavenEnvironment mavenEnvironment;
    private final RuntimeEnvironment runtimeEnvironment;


    @Autowired
    public CheckEnvironmentRunner(MavenEnvironment mavenEnvironment, RuntimeEnvironment runtimeEnvironment) {
        this.mavenEnvironment = mavenEnvironment;
        this.runtimeEnvironment = runtimeEnvironment;
    }

    @Override
    public void run(String... args) {
        checkEqual("虚拟机版本", runtimeEnvironment.getJavaVmName(), mavenEnvironment.getJavaVmName());
        checkEqual("java版本", runtimeEnvironment.getJavaVersion(), mavenEnvironment.getJavaVersion());
        log.debug("编译环境为：" + mavenEnvironment.toString());
        log.debug("运行环境为：" + runtimeEnvironment.toString());
    }

    private void checkEqual(String message, String str1, String str2) {
        if (notEqual(str1, str2)) {
            log.warn("环境警告：{}不匹配，当前版本为{}，编译版本为{}", message, str1, str2);
        }
    }

    private boolean notEqual(String str1, String str2) {
        if (str1 != null) {
            return !str1.equals(str2);
        } else {
            return str2 != null;
        }
    }
}
