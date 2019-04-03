package com.supermap;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.java.com.supermap.JasyptEncryptableTest;

/**
 * 打包测试所有测试类
 *
 * @author leishiguang
 * @date 2019/04/03
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        OpsCoreTest.class,
        JasyptEncryptableTest.class
})
public class ApplicationTest {
}
