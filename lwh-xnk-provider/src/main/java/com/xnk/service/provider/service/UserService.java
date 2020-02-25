package com.xnk.service.provider.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.User;
import com.xnk.service.api.model.UserTotal;
import com.xnk.service.api.vo.UserVo;
import com.xnk.service.dao.user.UserMapper;
import com.xnk.service.dao.user.UserTotalMapper;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.provider.exception.BusinessException;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class UserService extends CrudService<UserMapper, User>{

	@Autowired
	protected UserMapper dao;
	@Autowired
	protected UserTotalMapper userTotalMapper;
	
	public Page<User> page(User entity) throws BusinessException{
		entity.getPage().setCount(this.dao.count(entity));
		entity.getPage().setList(this.dao.page(entity));
		return entity.getPage();
	}

	public int updateUser(User u) throws BusinessException{
		User param = new User();
		param.setOpenid(u.getOpenid());
		if(null != u.getOpenid()) param = this.dao.get(param);
		
		if(null == u.getOpenid() || null == param){
			u.setBalance(0.0);
			u.setStatus(1);//有效
			u.setCreateTime(new Date());
			int i = this.dao.insert(u);
			
			UserTotal ut = new UserTotal();
		    
			ut.setUserId(u.getId());
			ut.setEvaluationAlreadyBuyCount(0);
			ut.setEvaluationLikeCount(0);
			ut.setEvaluationReleaseCount(0);
			ut.setEvaluationSellCount(0);
			ut.setMyFollowColorCount(0);
			ut.setMyFollowFanCount(0);
			ut.setMyFollowShoeCount(0);
			ut.setMyFollowUserCount(0);
			ut.setCreateTime(new Date());
			this.userTotalMapper.insert(ut);
			return i;
		}else{
			u.setId(param.getId());
			u.setUpdateTime(new Date());
			return this.dao.update(u);
		}
	}

	public List<UserVo> listByIds(Long[] ids)throws BusinessException{
		return this.dao.listByIds(ids);
	}
	
}
