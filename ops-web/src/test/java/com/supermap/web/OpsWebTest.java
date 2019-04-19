package com.supermap.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 打包测试所有测试类
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        JasyptEncryptableTest.class
})
public class OpsWebTest {
}