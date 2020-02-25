package com.xnk.service.dao.user;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.FollowUser;
import com.xnk.service.dao.CrudDao;

@Mapper
public interface FollowUserMapper extends CrudDao<FollowUser>{
}