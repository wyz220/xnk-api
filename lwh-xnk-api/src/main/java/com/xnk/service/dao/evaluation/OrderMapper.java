package com.xnk.service.dao.evaluation;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.Order;
import com.xnk.service.dao.CrudDao;

@Mapper
public interface OrderMapper extends CrudDao<Order>{
	
}