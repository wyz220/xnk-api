package com.xnk.service.provider.controller;

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

import com.xnk.service.api.model.UserTotal;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.service.UserTotalService;
import com.xnk.service.provider.utils.UrlEnDeCodeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Api(description="用户统计接口")
@RestController
@RequestMapping(value = "/userTotal")
public class UserTotalController {


	private Logger log = LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	private UserTotalService userTotalService;
	
	@ApiOperation(value = "用户统计详情", notes = "用户统计详情", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userid", value = "用户标识userId", paramType = "query", required = true, dataType = "long")})
	@RequestMapping(value = "get")
	public Map<String, Object> get(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="userid",required=true) Long userid
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			UserTotal userTotal = new UserTotal();
			userTotal.setUserId(userid);
			userTotal = this.userTotalService.get(userTotal);
			return RestResult.restResult(result, userTotal);
		} catch (Exception e) {
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	@ApiOperation(value = "用户统计查询", notes = "用户统计查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		    @ApiImplicitParam(name = "pageNo", value = "分页页数，默认0，分页大小默认20", paramType = "query", required = true, dataType = "integer")
		   ,@ApiImplicitParam(name = "name", value = "用户搜索名称", paramType = "query", required = false, dataType = "string")
	       })
	@RequestMapping(value = "list")
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value="pageNo",required=true) Integer pageNo,
			@RequestParam(value="name",required=false) String name,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			name = UrlEnDeCodeUtils.URLDecoderString(name);
			
			log.info(String.format("name=>%s,userTotalStatus=>%s,pageNo=>%s", new Object[]{
					name,pageNo
			}));
			
			Page<UserTotal> data = null;
			UserTotal userTotal = new UserTotal();
			userTotal.getPage().setPageNo(pageNo);
			data = this.userTotalService.page(userTotal);
			return RestResult.restResult(result, data);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}

	

}
