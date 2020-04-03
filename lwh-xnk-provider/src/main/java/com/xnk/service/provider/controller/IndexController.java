package com.xnk.service.provider.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xnk.service.api.model.User;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.config.SysConfig;
import com.xnk.service.provider.service.UserService;
import com.xnk.service.provider.wx.WXUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="公共接口")
@RestController
@RequestMapping(value = "/")
public class IndexController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	
	@ApiOperation(value = "登录接口", notes = "登录接口", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		   @ApiImplicitParam(name = "user", value = "微信接口返回的用户信息", paramType = "query", required = false, dataType = "string")
	       ,@ApiImplicitParam(name = "code", value = "微信页面code值，用于获取用户openid", paramType = "query", required = false, dataType = "string")
		   ,@ApiImplicitParam(name = "source", value = "用户来源,1小程序  2android", paramType = "query", required = false, dataType = "integer")
		   ,@ApiImplicitParam(name = "nickName", value = "微信昵称", paramType = "query", required = false, dataType = "string")
		   ,@ApiImplicitParam(name = "avatarUrl", value = "微信用户头像", paramType = "query", required = false, dataType = "string")
		   ,@ApiImplicitParam(name = "openid", value = "微信用户openid", paramType = "query", required = false, dataType = "string")
		   ,@ApiImplicitParam(name = "sex", value = "微信返回的用户性别  1男 0女", paramType = "query", required = false, dataType = "string")
	       })
	@RequestMapping(value = "login")
	public Map<String, Object> get(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="user",required=false) String user,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="source",required=false) Integer source,
			@RequestParam(value="nickName",required=false) String nickName,
			@RequestParam(value="avatarUrl",required=false) String avatarUrl,
			@RequestParam(value="openid",required=false) String openid,
			@RequestParam(value="sex",required=false) String sex
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		log.info("code=>"+code+",source=>"+source+",user=>"+user);
		
		try { 
			//user = "{\"nickName\":\"test1117\",\"avatarUrl\":\"www.bai\",\"gender\":1}";
			JSONObject userObj = JSON.parseObject(user);
			
			if(StringUtils.isBlank(openid)){
				nickName = userObj.getString("nickName");
				avatarUrl = userObj.getString("avatarUrl");
				sex = userObj.getString("gender");
				openid = WXUtils.getOpenId(code);
			}
			if(null != source && source == 1){
				openid = "wx_ming";
			}
			User u = new User();
			u.setOpenid(StringUtils.isNotBlank(openid) ? openid : null);
			u.setNickname(nickName);
			u.setIcon(avatarUrl);
			u.setSex(Integer.valueOf(sex));
			u.setWxappid(SysConfig.WX_APP_ID);
			u.setSource(source);
			
			this.userService.updateUser(u);
			
			Map<String,Object> dataMap = Maps.newHashMap();
			
			dataMap.put("user_id", u.getId());
			dataMap.put("openid", u.getOpenid());
			dataMap.put("userName", u.getName());
			dataMap.put("nickName", nickName);
			dataMap.put("sex", u.getSex());
			dataMap.put("address", u.getAddress());

			return RestResult.restResult(result, dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
}
