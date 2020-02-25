package com.xnk.service.dao.shoe;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.Shoe;
import com.xnk.service.dao.CrudDao;

@Mapper
public interface ShoeDao extends CrudDao<Shoe> {

}
