package com.xnk.service.provider.controller;

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

import com.xnk.service.api.model.FollowShoeColor;
import com.xnk.service.api.model.FollowUser;
import com.xnk.service.api.vo.UserVo;
import com.xnk.service.api.model.FollowShoeColor;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.service.FollowShoeColorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="用户关注鞋款/配色查询接口")
@RestController
@RequestMapping(value = "/followShoeColor")
public class FollowShoeColorController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	private FollowShoeColorService followShoeColorService;
	
	@ApiOperation(value = "关注鞋款/配色查询", notes = "关注鞋款/配色查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户标识userId", paramType = "query", required = true, dataType = "long")
		,@ApiImplicitParam(name = "type", value = "关注鞋款和配色查询传0  关注鞋款查询传1  关注配色查询传2 ", paramType = "query", required = true, dataType = "long")
	    ,@ApiImplicitParam(name = "pageNo", value = "分页页数，默认0，分页大小默认20", paramType = "query", required = true, dataType = "integer")
	    })
	@RequestMapping(value = "list")
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value="userId",required=true) Long userId,
			@RequestParam(value="pageNo",required=true) Integer pageNo,
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
			
			Page<FollowShoeColor> data = null;
			FollowShoeColor followShoeColor =  new FollowShoeColor();
			followShoeColor.setUserId(userId);
			followShoeColor.setType(type);
			followShoeColor.getPage().setPageNo(pageNo);
			data = this.followShoeColorService.page(followShoeColor);
			
			return RestResult.restResult(result, data);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
	}


	@ApiOperation(value = "关注鞋款配色", notes = "关注鞋款配色", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户标识userId", paramType = "query", required = true, dataType = "long")
		,@ApiImplicitParam(name = "type", value = "1 鞋款  2配色 ", paramType = "query", required = true, dataType = "long")
	    ,@ApiImplicitParam(name = "followId", value = "被关注鞋款配色id", paramType = "query", required = true, dataType = "long")
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
			
			FollowShoeColor fu = new FollowShoeColor();
			fu.setType(type);//1 鞋款 2配色
			fu.setUserId(userId);
			fu.setFollowId(followId);
			FollowShoeColor FollowShoeColor2 = this.followShoeColorService.get(fu);
			if (null == FollowShoeColor2) {
				fu.setStatus(1);
				fu.setCreateTime(new Date());
				this.followShoeColorService.insert(fu);
			} else {
				fu.setStatus(0);
				this.followShoeColorService.update(fu);
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
