package com.supermap;

import com.supermap.core.OpsCoreTest;
import com.supermap.core.util.CreateFileUtilTest;
import com.supermap.core.util.SpringUtilTest;
import com.supermap.web.OpsWebTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 所有方法的测试入口
 *
 * @author leishiguang
 * @date 2019/04/05
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        OpsCoreTest.class,
        OpsWebTest.class
})
public class ApplicationTest {
}
