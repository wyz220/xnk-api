package com.xnk.service.dao.user;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.UserTotal;
import com.xnk.service.dao.CrudDao;

@Mapper
public interface UserTotalMapper extends CrudDao<UserTotal>{
	
}