package com.xnk.service.dao.brand;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.Brand;
import com.xnk.service.dao.CrudDao;

@Mapper
public interface BrandMapper extends CrudDao<Brand>{
	
}