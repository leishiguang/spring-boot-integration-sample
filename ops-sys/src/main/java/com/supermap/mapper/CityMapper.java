package com.supermap.mapper;

import com.supermap.model.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CityMapper {

    /**
     *注解
     */
    @Select("select * from city where state = #{state}")
    City findByState(@Param("state") String state);

    /**
     * xml
     */
    City selectCityById(int city_id);

}
