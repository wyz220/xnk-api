package com.xnk.service.dao.sys;

import com.xnk.service.api.model.SysRegionPo;
import com.xnk.service.dao.CrudDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRegionDao extends CrudDao<SysRegionPo> {

    List<SysRegionPo> getAreaCode(SysRegionPo po);

}
