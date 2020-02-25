package com.xnk.service.provider.controller;

import java.util.Date;
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

import com.xnk.service.api.model.Color;
import com.xnk.service.api.model.FollowShoeColor;
import com.xnk.service.api.model.Shoe;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.service.ColorService;
import com.xnk.service.provider.service.FollowShoeColorService;
import com.xnk.service.provider.utils.UrlEnDeCodeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="配色接口")
@RestController
@RequestMapping(value = "/color")
public class ColorController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ColorService service;
	
	@Autowired
	private FollowShoeColorService followShoeColorService;
	

	@ApiOperation(value = "配色详情", notes = "配色详情", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户标识id", paramType = "query", required = true, dataType = "long")
		,@ApiImplicitParam(name = "colorId", value = "配色标识colorId", paramType = "query", required = true, dataType = "long")})
	@RequestMapping(value = "get")
	public Map<String, Object> get(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="userId",required=true) Long userId,
			@RequestParam(value="colorId",required=false) Long colorId
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			Color c = new Color();
			c.setColorId(colorId);
			c = this.service.get(c);
			c.setStatus(0);
			if(null != userId){
				FollowShoeColor sc = new FollowShoeColor();
				sc.setUserId(userId);
				sc.setFollowId(colorId);
				sc.setType(2);//鞋款配色
				FollowShoeColor followShoeColor = this.followShoeColorService.get(sc);
				c.setStatus(null == followShoeColor ? 0 : 1);//返回该用户是否关注过鞋款
			}
			
			return RestResult.restResult(result, c);
		} catch (Exception e) {
			log.error("配色详情异常",e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	@ApiOperation(value = "鞋款配色关注", notes = "鞋款配色关注", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "shoeId", value = "鞋款标识shoeId", paramType = "query", required = true, dataType = "long")
			,@ApiImplicitParam(name = "userId", value = "关注者userId", paramType = "query", required = true, dataType = "long")
			,@ApiImplicitParam(name = "followId", value = "被关注者followId", paramType = "query", required = true, dataType = "long")
		    ,@ApiImplicitParam(name = "stauts", value = "1 关注   0取消关注", paramType = "query", required = true, dataType = "long")})
	@RequestMapping(value = "relation")
	public Map<String, Object> relation(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="shoeId",required=true) Long shoeId,
			@RequestParam(value="userId",required=true) Long userId,
			@RequestParam(value="followId",required=true) Long followId,
			@RequestParam(value="stauts",required=true) Integer stauts
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			FollowShoeColor sc = new FollowShoeColor();
			sc.setUserId(userId);
			sc.setFollowId(followId);
			sc.setType(2);//鞋款配色
			FollowShoeColor followShoeColor = this.followShoeColorService.get(sc);
			if(stauts == 1 ){//关注
				if(null == followShoeColor){
					sc.setStatus(1);
					sc.setCreateTime(new Date());
					sc.setType(2);//鞋款配色
					this.followShoeColorService.insert(sc);
				}else if(followShoeColor.getStatus() == 0){
					followShoeColor.setStatus(1);
					this.followShoeColorService.update(followShoeColor);
				}
			}else{
				followShoeColor.setStatus(0);
				this.followShoeColorService.update(followShoeColor);
			}
			return RestResult.restResult(result, null);
		} catch (Exception e) {
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	
	@ApiOperation(value = "配色查询", notes = "配色查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "colorStatus", value = "类型查询，0-查询所有 1-按热门鞋款  2-最新更新  3-测评最多  4-粉丝最多", paramType = "query", required = true, dataType = "integer")
	       ,@ApiImplicitParam(name = "pageNo", value = "分页页数，默认0，分页大小默认20", paramType = "query", required = true, dataType = "integer")
			,@ApiImplicitParam(name = "shoeType", value = "鞋款类型  1-篮球鞋  2-跑鞋", paramType = "query", required = true, dataType = "integer")
			,@ApiImplicitParam(name = "searchType", value = "搜索类型  1-默认全部  2-搜索其他(针对不包含某个配色查询)", paramType = "query", required = true, dataType = "integer")
			,@ApiImplicitParam(name = "colorId", value = "搜索类型为2时，此参数必传", paramType = "query", required = true, dataType = "long")
			,@ApiImplicitParam(name = "name", value = "配色搜索名称", paramType = "query", required = false, dataType = "string")
	       })
	@RequestMapping(value = "list")
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value="colorStatus",required=true) Integer colorStatus,
			@RequestParam(value="shoeId",required=false) Long shoeId,
			@RequestParam(value="colorId",required=false) Long colorId,
			@RequestParam(value="searchType",required=false) Integer searchType,
			@RequestParam(value="pageNo",required=false) Integer pageNo,
			@RequestParam(value="shoeType",required=false) Integer shoeType,
			@RequestParam(value="name",required=false) String name,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			name = UrlEnDeCodeUtils.URLDecoderString(name);
			
			log.info(String.format("name=>%s,ColorStatus=>%s,pageNo=>%s", new Object[]{
					name,colorStatus,pageNo
			}));
			
			Page<Color> data = null;
			Color color = null;
			switch(colorStatus){
			case 0:
				color = new Color();
				break;
			case 1:
				color = new Color();
				color.getPage().setOrderBy(" hot_num desc");
				break;
			case 2:
				color = new Color();
				color.getPage().setOrderBy(" modify_date desc");
				break;
			case 3:
				color = new Color();
				color.getPage().setOrderBy(" evaluation_num desc");
				break;
			case 4:
				color = new Color();
				color.getPage().setOrderBy(" user_num desc");
				break;
			case 5:
				color = new Color();
				color.getPage().setOrderBy(" order_num desc");
				break;
			default:
				break;
			}
			color.setName(name);
			color.setShoeType(shoeType);
			color.setShoeId(shoeId);
			if(null != searchType && searchType == 2){
				if(null == colorId){
					result.setCode(ResultCode.FAILED.getCode());
					result.setMessage("鞋款id不为空");
					return RestResult.restResult(result, null);
				}
				color.getPage().setCondition(" and color_id != "+colorId);
			}
			data = this.service.page(color);
			return RestResult.restResult(result, data);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("配色查询异常",e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
}
