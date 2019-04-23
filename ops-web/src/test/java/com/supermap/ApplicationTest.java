package com.supermap;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * 打包测试所有测试类(com.supermap)
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@RunWith(JUnitPlatform.class)
@SelectPackages("com.supermap")
class ApplicationTest {
}
