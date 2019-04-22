package com.supermap.mapper.master;

import com.supermap.base.BaseCustomMapper;
import com.supermap.model.CityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * CityEntity Mapper 接口
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Mapper
@Repository
public interface CityMapper extends BaseCustomMapper<CityEntity> {

    /**
     * 注解方式查询
     * @param state state
     * @return CityEntity
     */
    @Select("select * from city where state = #{state}")
    CityEntity findByState(@Param("state") String state);

    /**
     * mapper 方式查询
     * @param cityId cityId
     * @return CityEntity
     */
    CityEntity selectCityById(Integer cityId);

}
