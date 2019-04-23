package com.supermap.work.mapper.bdck;

import com.supermap.mapper.bdck.BdckCommonMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 不动产库的通用 Mapper 测试
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@RunWith(JUnitPlatform.class)
@SpringBootTest
@DisplayName("BdckCommonMapper")
class BdckCommonMapperTest {

    @Autowired
    BdckCommonMapper bdckCommonMapper;

    @Test
    @DisplayName("查询任何sql")
    void selectByFullSql() {
        String sql = "select * from bdck.bdcs_xmxx t where t.xmbh = 'ee0b9a4db5774baba16fa326f9176752'";
        List<LinkedHashMap<String, Object>> map = bdckCommonMapper.selectByFullSql(sql);
        assertNotNull(map);
    }
}
