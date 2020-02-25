package com.xnk.service.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.SysConstant;
import com.xnk.service.dao.evaluation.SysConstantMapper;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class SysConstantService extends CrudService<SysConstantMapper, SysConstant>{

	@Autowired
	protected SysConstantMapper dao;
}
