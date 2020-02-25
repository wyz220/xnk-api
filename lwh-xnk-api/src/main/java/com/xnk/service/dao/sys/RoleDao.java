/**
 * 
 */
package com.xnk.service.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xnk.service.api.model.RoleMenuPo;
import com.xnk.service.api.model.RolePo;
import com.xnk.service.dao.CrudDao;

/**
 *
 */
@Mapper
public interface RoleDao extends CrudDao<RolePo> {
	
	public List<RolePo> gets(@Param("roleIds")Long[] roleIds);
	
	public List<Long> getRoleMenuIds(Long[] roleIds);
	
	public int insertRoleMenu(RoleMenuPo po);
	
    public int deleteRoleMenuByRoleId( Object entity);
    
    public List<RolePo> findRoleByUserId(Long[] userIds);
}
