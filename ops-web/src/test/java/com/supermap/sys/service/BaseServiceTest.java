package com.supermap.sys.service;

import com.supermap.mapper.master.CityMapper;
import com.supermap.model.CityEntity;
import com.supermap.service.CityService;
import com.supermap.util.SpringUtil;
import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Service 层基础CRUD
 *
 * @author leishiguang
 * @version v1.0
 * @since v1.0
 */
@RunWith(JUnitPlatform.class)
@SpringBootTest
@DisplayName("Service:通用 CRUD")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BaseServiceTest {

    @Autowired
    CityService cityService;
    private CityMapper cityMapper;
    private Integer cityId = 201;
    private String cityCountry = "China";
    private String cityName = "SiChuan";
    private String cityState = "510500";

    @BeforeAll
    void beforeClass() {
        cityMapper = SpringUtil.getBean(CityMapper.class);
        cityMapper.insert(new CityEntity(cityId, cityName, cityState, cityCountry));
    }

    @AfterAll
    void AfterClass() {
        cityMapper.deleteByPrimaryKey(cityId);
    }

    @Test
    @DisplayName("完整的增删改查")
    void normalCrud() {
        Integer cityId = 100;
        CityEntity city = cityService.selectByPrimaryKey(cityId);
        if (city != null) {
            cityService.delete(city);
        }
        Integer count = cityService.selectAll().size();
        city = new CityEntity(cityId, "SiChuan", "CNSC", "China");
        assertEquals(1, cityService.insert(city));
        Integer count2 = cityService.selectAll().size();
        assertEquals(1, count2 - count);
        assertNotNull(cityService.selectByPrimaryKey(cityId));
        city.setState("510500");
        assertEquals(1, cityService.updateByPrimaryKey(city));
        assertEquals(1, cityService.delete(city));
        Integer count3 = cityService.selectAll().size();
        assertEquals(count, count3);
        assertNull(cityService.selectByPrimaryKey(cityId));
    }

    @Test
    @DisplayName("按条件查询")
    void selectByCondition() {
        CityEntity city = new CityEntity();
        city.setCountry(cityCountry);
        List<CityEntity> cityList = cityService.select(city);
        assertNotNull(cityList, "条件查询不应为空");
        city.setName(cityName);
        cityList = cityService.select(city);
        assertNotNull(cityList, "条件查询不应为空");
        city.setId(cityId);
        city.setState(cityState);
        cityList = cityService.select(city);
        assertNotNull(cityList, "条件查询不应为空");
        assertEquals(1, cityList.size());
        assertEquals(cityId, city.getId(), "条件查询出正确对象");
    }

}
