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
 * @date 2019/04/03
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateFileUtilTest.class,
        SpringUtilTest.class
})
public class OpsCoreTest {
}
