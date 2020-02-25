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

import com.xnk.service.api.model.FollowShoeColor;
import com.xnk.service.api.model.Shoe;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.service.FollowShoeColorService;
import com.xnk.service.provider.service.ShoeService;
import com.xnk.service.provider.utils.UrlEnDeCodeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="鞋款接口")
@RestController
@RequestMapping(value = "/shoe")
public class ShoeController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	private ShoeService shoeService;
	@Autowired
	private FollowShoeColorService followShoeColorService;
	
	@ApiOperation(value = "鞋款详情", notes = "鞋款详情", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "shoeId", value = "鞋款标识shoeId", paramType = "query", required = true, dataType = "long")
			,@ApiImplicitParam(name = "userId", value = "关注者userId", paramType = "query", required = true, dataType = "long")
			
	})
	@RequestMapping(value = "get")
	public Map<String, Object> get(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="userId",required=true) Long userId,
			@RequestParam(value="shoeId",required=false) Long shoeId
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			Shoe shoe = new Shoe();
			shoe.setShoeId(shoeId);
			shoe = this.shoeService.get(shoe);
			shoe.setStatus(0);
			if(null != userId){
				FollowShoeColor sc = new FollowShoeColor();
				sc.setUserId(userId);
				sc.setFollowId(shoeId);
				sc.setType(1);//鞋款
				FollowShoeColor followShoeColor = this.followShoeColorService.get(sc);
				shoe.setStatus(null == followShoeColor ? 0 : 1);//返回该用户是否关注过鞋款
			}
			
			return RestResult.restResult(result, shoe);
		} catch (Exception e) {
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	@ApiOperation(value = "鞋款关注", notes = "鞋款关注", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "shoeId", value = "鞋款标识shoeId", paramType = "query", required = true, dataType = "long")
			,@ApiImplicitParam(name = "userId", value = "关注者userId", paramType = "query", required = true, dataType = "long")
		    ,@ApiImplicitParam(name = "stauts", value = "1 关注   0取消关注", paramType = "query", required = true, dataType = "long")})
	@RequestMapping(value = "relation")
	public Map<String, Object> relation(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="shoeId",required=true) Long shoeId,
			@RequestParam(value="userId",required=true) Long userId,
			@RequestParam(value="stauts",required=true) Integer stauts
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			FollowShoeColor sc = new FollowShoeColor();
			sc.setUserId(userId);
			sc.setFollowId(shoeId);
			sc.setType(1);//鞋款
			FollowShoeColor followShoeColor = this.followShoeColorService.get(sc);
			if(stauts == 1 ){//关注
				if(null == followShoeColor){
					sc.setStatus(1);
					sc.setCreateTime(new Date());
					sc.setType(1);//鞋款
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
	
	@ApiOperation(value = "鞋款查询", notes = "鞋款查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "shoeStatus", value = "类型查询，0-查询所有 1-按热门鞋款  2-最新更新  3-测评最多  4-粉丝最多 5-订单数", paramType = "query", required = true, dataType = "integer")
	       ,@ApiImplicitParam(name = "brandId", value = "品牌标识", paramType = "query", required = true, dataType = "long")
			,@ApiImplicitParam(name = "pageNo", value = "分页页数，默认0，分页大小默认20", paramType = "query", required = true, dataType = "integer")
			,@ApiImplicitParam(name = "shoeType", value = "鞋款类型  1-篮球鞋  2-跑鞋", paramType = "query", required = true, dataType = "integer")
			,@ApiImplicitParam(name = "searchType", value = "搜索类型  1-默认全部  2-搜索其他(针对不包含某个鞋款查询)", paramType = "query", required = true, dataType = "integer")
			,@ApiImplicitParam(name = "shoeId", value = "搜索类型为2时，此参数必传", paramType = "query", required = true, dataType = "long")
			 ,@ApiImplicitParam(name = "name", value = "鞋款搜索名称", paramType = "query", required = false, dataType = "string")
	       })
	@RequestMapping(value = "list")
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value="shoeStatus",required=true) Integer shoeStatus,
			@RequestParam(value="brandId",required=false) Long brandId,
			@RequestParam(value="shoeId",required=false) Long shoeId,
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
			
			log.info(String.format("name=>%s,shoeStatus=>%s,pageNo=>%s", new Object[]{
					name,shoeStatus,pageNo
			}));
			
			Page<Shoe> data = null;
			Shoe shoe = null;
			switch(shoeStatus){
			case 0:
				shoe = new Shoe();
				break;
			case 1:
				shoe = new Shoe();
				shoe.getPage().setOrderBy(" hot_num desc");
				break;
			case 2:
				shoe = new Shoe();
				shoe.getPage().setOrderBy(" modify_date desc");
				break;
			case 3:
				shoe = new Shoe();
				shoe.getPage().setOrderBy(" evaluation_num desc");
				break;
			case 4:
				shoe = new Shoe();
				shoe.getPage().setOrderBy(" user_num desc");
				break;
			case 5:
				shoe = new Shoe();
				shoe.getPage().setOrderBy(" order_num desc");
				break;
			default:
				break;
			}
			shoe.setName(name);
			shoe.setShoeType(shoeType);
			shoe.setBrandId(brandId);
			
			
			if(null != searchType && searchType == 2){
				if(null == shoeId){
					result.setCode(ResultCode.FAILED.getCode());
					result.setMessage("鞋款id不为空");
					return RestResult.restResult(result, null);
				}
				shoe.getPage().setCondition(" and shoe_id != "+shoeId);
			}
			shoe.getPage().setPageNo(pageNo);
			data = this.shoeService.page(shoe);
			return RestResult.restResult(result, data);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
}
