package com.xnk.service.dao.shoe;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.Color;
import com.xnk.service.dao.CrudDao;

@Mapper
public interface ColorDao extends CrudDao<Color> {

}
