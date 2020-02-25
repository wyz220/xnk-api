package com.xnk.service.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.FollowShoeColor;
import com.xnk.service.dao.user.FollowShoeColorMapper;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.provider.exception.BusinessException;
import com.xnk.service.service.CrudService;
@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class FollowShoeColorService extends CrudService<FollowShoeColorMapper, FollowShoeColor>{

	@Autowired
	protected FollowShoeColorMapper dao;
	
	public Page<FollowShoeColor> page(FollowShoeColor entity) throws BusinessException{
		entity.getPage().setCount(this.dao.count(entity));
		entity.getPage().setList(this.dao.page(entity));
		return entity.getPage();
	}
	
}
