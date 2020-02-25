package com.xnk.service.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.FollowUser;
import com.xnk.service.api.model.UserTotal;
import com.xnk.service.dao.user.FollowUserMapper;
import com.xnk.service.dao.user.UserTotalMapper;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.provider.exception.BusinessException;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class FollowUserService extends CrudService<FollowUserMapper, FollowUser>{

	@Autowired
	protected FollowUserMapper dao;
	
	@Autowired
	protected UserTotalMapper userTotalDao;
	
	/**
	 *  用户关注
	 */
	public int insert(FollowUser fu)throws BusinessException{
		UserTotal total = new UserTotal();
		total.setUserId(fu.getUserId());
		UserTotal userTotal = this.userTotalDao.get(total);
		userTotal.setMyFollowUserCount(userTotal.getMyFollowUserCount() + 1);//我关注的人数
		total.setUserId(fu.getFollowUserId());
		
		UserTotal userTotal2 = this.userTotalDao.get(total);//被关注的粉丝数
		userTotal2.setMyFollowFanCount(userTotal2.getMyFollowFanCount() + 1);
		
		this.userTotalDao.update(userTotal2);
		this.userTotalDao.update(userTotal);
		
		return this.dao.insert(fu);
	}
	
	/**
	 * 用户取消关注
	 * @param entity
	 * @return
	 */
	public int update(FollowUser fu)throws BusinessException{
		UserTotal total = new UserTotal();
		total.setUserId(fu.getUserId());
		UserTotal userTotal = this.userTotalDao.get(total);
		userTotal.setMyFollowUserCount(userTotal.getMyFollowUserCount() - 1);//我关注的人数
		total.setUserId(fu.getFollowUserId());
		
		UserTotal userTotal2 = this.userTotalDao.get(total);//被关注的粉丝数
		userTotal2.setMyFollowFanCount(userTotal2.getMyFollowFanCount() - 1);
		
		this.userTotalDao.update(userTotal2);
		this.userTotalDao.update(userTotal);
		return dao.update(fu);
	}
	
	public Page<FollowUser> page(FollowUser entity) throws BusinessException{
		entity.getPage().setCount(this.dao.count(entity));
		entity.getPage().setList(this.dao.page(entity));
		return entity.getPage();
	}

	public int updateSpecial(FollowUser fu)throws BusinessException{
		return this.dao.update(fu);
	}
}
