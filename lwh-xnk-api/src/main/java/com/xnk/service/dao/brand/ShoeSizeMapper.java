package com.xnk.service.dao.brand;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.ShoeSize;
import com.xnk.service.dao.CrudDao;

@Mapper
public interface ShoeSizeMapper extends CrudDao<ShoeSize>{
	
}