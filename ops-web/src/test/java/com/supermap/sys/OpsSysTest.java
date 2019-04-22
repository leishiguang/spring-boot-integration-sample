package com.supermap.sys;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

/**
 * OpsSys 的打包测试
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
