package com.supermap.core.util;

import com.supermap.util.CreateFileUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * 测试文件创建
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@RunWith(JUnitPlatform.class)
@DisplayName("文件创建工具类")
class CreateFileUtilTest {

    @Test
    @DisplayName("创建文件")
    void createFileTest() {
        //创建目录
        String dirName = "D:/temp";
        File tmpfile = new File(dirName + "/");
        if (tmpfile.exists()) {
            assertTrue(CreateFileUtil.deleteDir(tmpfile));
        }
        assertTrue(CreateFileUtil.createDir(dirName));
        //创建文件
        String fileName = dirName + "/temp0/temp1/temp2/tempFile.txt";
        assertTrue(CreateFileUtil.createFile(fileName));
        //创建临时文件
        String prefix = "temp";
        String suffix = ".txt";
        String tmpDirName = tmpfile.getAbsolutePath();
        int times = 10;
        for (int i = 0; i < times; i++) {
            assertNotNull(CreateFileUtil.createTempFile(prefix, suffix, tmpDirName));
        }
        //在默认目录下创建临时文件
        for (int i = 0; i < times; i++) {
            assertNotNull(CreateFileUtil.createTempFile(prefix, suffix, null));
        }
        assertTrue(CreateFileUtil.deleteDir(tmpfile));
    }
}
