package com.supermap.aide;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Maven 编译时的环境信息
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Data
@Component
public class MavenEnvironment {
    /**
     * 编译版本
     */
    @Value("${mv.project.version}")
    String projectVersion;

    /**
     * 编译时间
     */
    @Value("${mv.maven.build.timestamp}")
    String mavenBuildTimestamp;

    /**
     * java运行时环境，如：1.8.0_201-b09
     */
    @Value("${mv.java.runtime.version}")
    String javaRuntimeVersion;

    /**
     * java版本，如：1.8.0_201
     */
    @Value("${mv.java.version}")
    String javaVersion;

    /**
     * 打包时候的版本，如：Java HotSpot(TM) 64-Bit Server VM
     */
    @Value("${mv.java.vm.name}")
    String javaVmName;

    /**
     * 打包的操作系统名称，如Windows 10
     */
    @Value("${mv.os.name}")
    String osName;
    /**
     * 打包的平台位数，如：amd64
     */
    @Value("${mv.sun.cpu.isalist}")
    String sunCpuIsalist;

    /**
     * 打包的用户名称，如：leish
     */
    @Value("${mv.user.name}")
    String userName;

    /**
     * 打包的计算机名称，如：LEITING-THINKPA
     */
    @Value("${mv.computer.name}")
    String computerName;

}
