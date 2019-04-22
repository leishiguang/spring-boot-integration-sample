package com.supermap.service.impl;

import com.supermap.base.BaseCustomMapper;
import com.supermap.base.BaseService;
import com.supermap.base.impl.BaseServiceImpl;
import com.supermap.mapper.master.CityMapper;
import com.supermap.model.CityEntity;
import com.supermap.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * City 的 Service 实现类
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Service
public class CityServiceImpl extends BaseServiceImpl<CityEntity> implements CityService {

    private final CityMapper cityMapper;

    @Autowired
    public CityServiceImpl(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    @Override
    protected BaseCustomMapper<CityEntity> getMapper() {
        return cityMapper;
    }
}
