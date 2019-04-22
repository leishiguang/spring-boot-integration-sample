package com.supermap.base.impl;

import com.supermap.base.BaseCustomMapper;
import com.supermap.base.BaseService;

import java.util.List;

/**
 * 基础 Service 实现类。将通用的 Dao 层方法开放给 Service 层
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
public abstract class BaseServiceImpl<T> implements BaseService<T>, BaseCustomMapper<T> {

    /**
     * 获取 BaseMapper
     * @return Mapper 返回每个 Entity 对应的 Mapper
     */
    protected abstract BaseCustomMapper<T> getMapper();

    @Override
    public int deleteByPrimaryKey(Object o) {
        return getMapper().deleteByPrimaryKey(o);
    }

    @Override
    public int delete(T t) {
        return getMapper().delete(t);
    }

    @Override
    public int insert(T t) {
        return getMapper().insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return getMapper().insertSelective(t);
    }

    @Override
    public boolean existsWithPrimaryKey(Object o) {
        return getMapper().existsWithPrimaryKey(o);
    }

    @Override
    public List<T> selectAll() {
        return getMapper().selectAll();
    }

    @Override
    public T selectByPrimaryKey(Object o) {
        return getMapper().selectByPrimaryKey(o);
    }

    @Override
    public int selectCount(T t) {
        return getMapper().selectCount(t);
    }

    @Override
    public List<T> select(T t) {
        return getMapper().select(t);
    }

    @Override
    public T selectOne(T t) {
        return getMapper().selectOne(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return getMapper().updateByPrimaryKey(t);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        return getMapper().updateByPrimaryKeySelective(t);
    }

}
