package com.xnk.service.dao.user;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.UserWalletHistory;
import com.xnk.service.dao.CrudDao;

@Mapper
public interface UserWalletHistoryMapper extends CrudDao<UserWalletHistory>{
}