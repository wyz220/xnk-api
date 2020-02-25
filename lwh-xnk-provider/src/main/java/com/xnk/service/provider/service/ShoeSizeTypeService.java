package com.xnk.service.provider.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.ShoeSizeType;
import com.xnk.service.dao.brand.ShoeSizeTypeMapper;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class ShoeSizeTypeService extends CrudService<ShoeSizeTypeMapper, ShoeSizeType>{

}
