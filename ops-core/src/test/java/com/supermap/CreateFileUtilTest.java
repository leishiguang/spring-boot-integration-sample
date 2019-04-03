package com.supermap;

import com.supermap.util.CreateFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        String dirName = "D:/work/temp/temp0/temp1";
        CreateFileUtil.createDir(dirName);
        //创建文件
        String fileName = dirName + "/temp2/tempFile.txt";
        CreateFileUtil.createFile(fileName);
        //创建临时文件
        String prefix = "temp";
        String suffix = ".txt";
        for (int i = 0; i < 10; i++) {
            log.error("创建了临时文件："
                    + CreateFileUtil.createTempFile(prefix, suffix, dirName));
        }
        //在默认目录下创建临时文件
        for (int i = 0; i < 10; i++) {
            log.error("在默认目录下创建了临时文件："
                    + CreateFileUtil.createTempFile(prefix, suffix, null));
        }
    }
}
