package com.supermap.mapper.master;

import com.supermap.model.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * City Mapper 接口
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Mapper
@Repository
public interface CityMapper {

    /**
     * 注解方式查询
     * @param state state
     * @return City
     */
    @Select("select * from city where state = #{state}")
    City findByState(@Param("state") String state);


    /**
     * mapper 方式查询
     * @param cityId cityId
     * @return City
     */
    City selectCityById(int cityId);

}
