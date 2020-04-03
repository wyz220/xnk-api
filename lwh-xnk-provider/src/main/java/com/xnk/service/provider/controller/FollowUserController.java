package com.xnk.service.provider.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xnk.service.api.model.FollowUser;
import com.xnk.service.api.vo.UserVo;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.service.FollowUserService;
import com.xnk.service.provider.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="用户关注接口")
@RestController
@RequestMapping(value = "/followUser")
public class FollowUserController {


	private Logger log = LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	private FollowUserService followUserService;
	
	@Autowired
	private UserService userService;
	
	
	
	@ApiOperation(value = "关注用户/粉丝查询", notes = "关注用户/粉丝查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户标识userId", paramType = "query", required = true, dataType = "long")
		,@ApiImplicitParam(name = "type", value = "关注用户查询为0  关注粉丝查询为1  2特别关注用户查询", paramType = "query", required = true, dataType = "long")
	    ,@ApiImplicitParam(name = "pageNo", value = "分页页数，默认0，分页大小默认20", paramType = "query", required = true, dataType = "integer")
	    })
	@RequestMapping(value = "list")
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value="userId",required=true) Long userId,
			@RequestParam(value="pageNo",required=false) Integer pageNo,
			@RequestParam(value="type",required=true) Integer type,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			
			log.info(String.format("userId=>%s,type=>%s,pageNo=>%s", new Object[]{
					userId,type,pageNo
			}));
			
			Page<FollowUser> data = null;
			FollowUser followUser = null;
			switch(type){
			case 0:
				followUser = new FollowUser();
				followUser.setUserId(userId);
				followUser.setType(1);//普通关注
				break;
			case 1:
				followUser = new FollowUser();
				followUser.setFollowUserId(userId);
				break;
			case 2:
				followUser = new FollowUser();
				followUser.setFollowUserId(userId);
				followUser.setType(2);//特别关注
				break;
			default:
				break;
			}
			followUser.getPage().setPageNo(pageNo);
			data = this.followUserService.page(followUser);
			List<FollowUser> list = data.getList();
			Long[] ids = new Long[list.size()];
			int count=0;
			for(FollowUser fu : list){
				if(1 == type){
					ids[count++] = fu.getUserId();//粉丝
				}else{
					ids[count++] = fu.getFollowUserId();//我的关注
				}
			}
			if(ids.length == 0){
				return RestResult.restResult(result, new ArrayList<UserVo>());
			}
			List<UserVo> uservos = userService.listByIds(ids);
			return RestResult.restResult(result, uservos);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}


	@ApiOperation(value = "关注(取消)用户/粉丝", notes = "关注(取消)用户/粉丝", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户标识userId", paramType = "query", required = true, dataType = "long")
		,@ApiImplicitParam(name = "type", value = "2特别关注  1 关注  0取消关注", paramType = "query", required = true, dataType = "long")
	    ,@ApiImplicitParam(name = "followId", value = "被关注用户Id", paramType = "query", required = true, dataType = "long")
		})
	@RequestMapping(value = "follow")
	public Map<String, Object> follow(HttpServletRequest request,
			@RequestParam(value="userId",required=true) Long userId,
			@RequestParam(value="followId",required=true) Long followId,
			@RequestParam(value="type",required=true) Integer type,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			
			log.info(String.format("userId=>%s,type=>%s,followId=>%s", new Object[]{
					userId,type,followId
			}));
			
			FollowUser fu = new FollowUser();
			fu.setType(1);//1 普通关注
			fu.setUserId(userId);
			fu.setFollowUserId(followId);
			FollowUser followUser2 = this.followUserService.get(fu);
			if(null == followUser2){
				if(type == 1){//关注
					fu.setStatus(1);
					fu.setCreateTime(new Date());
					this.followUserService.insert(fu);
				}
			}else{
				if(type == 0){//取消关注
					fu.setStatus(0);
					this.followUserService.update(fu);
				}else if(type == 2){//特别关注
					fu.setType(2);//特别关注
					fu.setStatus(1);
					this.followUserService.updateSpecial(fu);
				}
			}
			
			return RestResult.restResult(result, null);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
}
