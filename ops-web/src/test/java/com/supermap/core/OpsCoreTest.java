package com.supermap.core;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

/**
 * 打包测试所有测试类(OpsCore)
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("OpsCore测试")
@SelectPackages("com.supermap.core")
class OpsCoreTest {
}
