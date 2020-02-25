package com.xnk.service.dao.user;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.UserDo;
import com.xnk.service.dao.CrudDao;

@Mapper
public interface UserDoMapper extends CrudDao<UserDo>{
}
