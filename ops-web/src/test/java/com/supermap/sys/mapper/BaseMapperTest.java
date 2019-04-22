package com.supermap.sys.mapper;

import com.supermap.mapper.master.CityMapper;
import com.supermap.model.CityEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 基础 CRUD 的 Dao 层测试
 *
 * @author leishiguang
 * @version v1.0
 * @since v1.0
 */
@RunWith(JUnitPlatform.class)
@SpringBootTest
@DisplayName("Dao:通用 CRUD")
class BaseMapperTest {

    @Autowired
    CityMapper cityMapper;

    @Test
    @DisplayName("完整的增删改查")
    void normalCrud() {
        Integer cityId = 100;
        CityEntity city = cityMapper.selectByPrimaryKey(cityId);
        if (city != null) {
            cityMapper.delete(city);
        }
        Integer count = cityMapper.selectAll().size();
        city = new CityEntity(cityId, "SiChuan", "CNSC", "China");
        assertEquals(1, cityMapper.insert(city));
        Integer count2 = cityMapper.selectAll().size();
        assertEquals(1, count2 - count);
        assertNotNull(cityMapper.selectByPrimaryKey(cityId));
        city.setState("510500");
        assertEquals(1, cityMapper.updateByPrimaryKey(city));
        assertEquals(1, cityMapper.delete(city));
        Integer count3 = cityMapper.selectAll().size();
        assertEquals(count, count3);
        assertNull(cityMapper.selectByPrimaryKey(cityId));
    }

}
