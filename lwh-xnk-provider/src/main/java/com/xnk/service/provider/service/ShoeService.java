package com.xnk.service.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.Shoe;
import com.xnk.service.dao.shoe.ShoeDao;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.provider.exception.BusinessException;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class ShoeService extends CrudService<ShoeDao, Shoe> {

	@Autowired
	protected ShoeDao dao;
	
	public Page<Shoe> page(Shoe shoe) throws BusinessException{
		shoe.getPage().setCount(this.dao.count(shoe));
		shoe.getPage().setList(this.dao.page(shoe));
		return shoe.getPage();
	}

}
