package com.xnk.service.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.User;
import com.xnk.service.api.vo.UserVo;
import com.xnk.service.dao.CrudDao;

@Mapper
public interface UserMapper extends CrudDao<User>{

	List<UserVo> listByIds(Long[] ids);
}