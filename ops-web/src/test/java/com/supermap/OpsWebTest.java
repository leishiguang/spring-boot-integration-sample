package com.supermap;

import com.supermap.common.JasyptEncryptableTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 打包测试所有测试类
 *
 * @author leishiguang
 * @date 2019/04/03
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        JasyptEncryptableTest.class
})
public class OpsWebTest {
}