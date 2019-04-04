package com.supermap.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.junit.Assert.*;

/**
 * 测试文件创建
 *
 * @author leishiguang
 * @date 2019/04/03
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateFileUtilTest {

    @Test
    public void createFileTest() {
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
