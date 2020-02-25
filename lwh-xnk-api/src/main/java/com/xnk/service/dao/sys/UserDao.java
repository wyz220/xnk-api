/**
 * 
 */
package com.xnk.service.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.UserPo;
import com.xnk.service.api.model.UserRolePo;
import com.xnk.service.dao.CrudDao;


/**
 *
 */
@Mapper
public interface UserDao extends CrudDao<UserPo> {

	public List<Long> getUserRoleIds(Long userId);
	
    public int deleteUserRoleByRoleId( Object entity);
    
	public int insertUserRole(UserRolePo po);
    
    public int deleteUserRoleByUserId( Object entity);
}
