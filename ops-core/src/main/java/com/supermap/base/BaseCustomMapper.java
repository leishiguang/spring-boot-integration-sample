package com.supermap.base;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * 通用的 Dao 层方法
 * 继承 BaseMapper，得到了增删改查的通用方法
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
public interface BaseCustomMapper<T> extends BaseMapper<T> {
}
