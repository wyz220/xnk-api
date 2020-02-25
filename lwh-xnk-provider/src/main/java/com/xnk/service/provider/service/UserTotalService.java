package com.xnk.service.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.UserTotal;
import com.xnk.service.dao.user.UserTotalMapper;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.provider.exception.BusinessException;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class UserTotalService extends CrudService<UserTotalMapper, UserTotal>{

	@Autowired
	protected UserTotalMapper dao;
	
	public Page<UserTotal> page(UserTotal entity) throws BusinessException{
		entity.getPage().setCount(this.dao.count(entity));
		entity.getPage().setList(this.dao.page(entity));
		return entity.getPage();
	}
	
}
