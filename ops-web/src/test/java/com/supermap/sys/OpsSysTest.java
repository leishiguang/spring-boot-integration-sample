package com.supermap.sys;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

/**
 * 打包测试所有测试类(OpsSys)
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("OpsSys测试")
@SelectPackages("com.supermap.sys")
class OpsSysTest {
}
