package com.supermap.base;

/**
 * 基础 Service
 * 与 Dao 层（Mapper）做好区分，目前继承了通用 Dao 的接口，让所有 Service 都具有通用方法
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
public interface BaseService<T> extends BaseCustomMapper<T> {
}
