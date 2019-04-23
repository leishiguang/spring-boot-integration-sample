package com.supermap.sys.mapper.master;

import com.supermap.mapper.master.CityMapper;

import com.supermap.model.CityEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CityMapper测试
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@RunWith(JUnitPlatform.class)
@SpringBootTest
@DisplayName("CityMapper")
class CityMapperTest {

    @Autowired
    CityMapper cityMapper;

    @Test
    @DisplayName("注解方式查询")
    void findByState() {
        CityEntity cityEntity;
        String state = "CA";
        cityEntity = cityMapper.findByState(state);
        assertEquals(state, cityEntity.getState());
    }

    @Test
    @DisplayName("mapper方式查询")
    void selectCityById() {
        CityEntity cityEntity;
        Integer id = 1;
        cityEntity = cityMapper.selectCityById(id);
        assertEquals(id, cityEntity.getId());
    }
}