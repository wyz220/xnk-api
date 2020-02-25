/**
 * 
 */
package com.xnk.service.dao.sys;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.LogPo;
import com.xnk.service.dao.CrudDao;


/**
 * 
 *
 */
@Mapper
public interface LogDao extends CrudDao<LogPo> {

}
