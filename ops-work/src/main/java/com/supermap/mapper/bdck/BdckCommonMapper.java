package com.supermap.mapper.bdck;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 不动产库的通用 Mapper
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Mapper
@Repository
public interface BdckCommonMapper {

    /**
     * 用完整 sql 查询 数据
     *
     * @param sql 完整sql
     * @return 以 List 方式封装为有序 Map
     */
    List<LinkedHashMap<String, Object>> selectByFullSql(String sql);
}
