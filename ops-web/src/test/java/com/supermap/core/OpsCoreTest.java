package com.supermap.core;

import com.supermap.core.util.CreateFileUtilTest;
import com.supermap.core.util.SpringUtilTest;
import com.supermap.util.SpringUtil;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * OpsCore 的测试
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateFileUtilTest.class,
        SpringUtilTest.class
})
public class OpsCoreTest {
}
