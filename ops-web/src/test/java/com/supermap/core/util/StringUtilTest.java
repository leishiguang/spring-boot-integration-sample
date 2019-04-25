package com.supermap.core.util;

import com.supermap.util.StringUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 字符串处理类测试
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@RunWith(JUnitPlatform.class)
@DisplayName("字符串处理工具类")
class StringUtilTest {

    @Test
    @DisplayName("生成数据库简短名称")
    void generateDbNameTest() {
        String[][] strPairs = new String[3][2];
        strPairs[0][0] = "jdbc:oracle:thin:@cdserver:1521:testa";
        strPairs[0][1] = "@cdserver/testa";
        strPairs[1][0] = "jdbc:oracle:thin:@172.0.0.2:testa";
        strPairs[1][1] = "@2/testa";
        strPairs[2][0] = "jdbc:oracle:thin:@172.0.0.2:1521:testa";
        strPairs[2][1] = "@2/testa";
        for (String[] strpair : strPairs) {
            assertEquals(strpair[1], StringUtil.generateDbName("", strpair[0]));
            assertEquals("bdck" + strpair[1], StringUtil.generateDbName("bdck", strpair[0]));
        }
    }
}
