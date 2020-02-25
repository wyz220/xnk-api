/**
 * 
 */
package com.xnk.service.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.MenuPo;
import com.xnk.service.dao.CrudDao;

/**
 *
 */
@Mapper
public interface MenuDao extends CrudDao<MenuPo> {

	public List<MenuPo> gets(Long[] menuIds);
	
	public List<MenuPo> findMenuByRoleId(Long roleId);
}
