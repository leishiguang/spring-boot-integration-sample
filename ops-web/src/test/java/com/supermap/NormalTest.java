package com.supermap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 临时测试，用于测试简单的代码
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@RunWith(JUnitPlatform.class)
@DisplayName("临时测试")
class NormalTest {

    @Test
    @DisplayName("字符串切分")
    void testSubString() {
        String zipName = "123.zip";
        zipName = zipName.substring(0, zipName.length() - 4);
        assertEquals("123", zipName);
    }

}
