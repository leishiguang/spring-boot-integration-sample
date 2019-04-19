package com.supermap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


/**
 * 临时测试，用于测试简单的代码
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class NormalTest {

    @Test
    public void testSubString() {
        String zipName = "123.zip";
        zipName = zipName.substring(0, zipName.length() - 4);
        assertEquals("123", zipName);
    }
}
