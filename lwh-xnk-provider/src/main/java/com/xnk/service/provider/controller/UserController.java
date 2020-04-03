package com.xnk.service.provider.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.xnk.service.api.model.Evaluation;
import com.xnk.service.api.model.FollowUser;
import com.xnk.service.api.model.User;
import com.xnk.service.api.vo.EvaluationNotBuyVo;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.service.EvaluationService;
import com.xnk.service.provider.service.FollowUserService;
import com.xnk.service.provider.service.UserService;
import com.xnk.service.provider.utils.UrlEnDeCodeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="用户接口")
@RestController
@RequestMapping(value = "/user")
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	private UserService userService;
	
	@Autowired
	private EvaluationService evaluationService; 
	
	@Autowired
	private FollowUserService followUserService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@ApiOperation(value = "用户已购测评查询", notes = "用户已购测评查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户标识 userId", paramType = "query", required = false, dataType = "long")
	       })
	@RequestMapping(value = "getUserBuyList")
	public Map<String, Object> getUserBuyList(HttpServletRequest request,
			@RequestParam(value="userId",required=false) Long userId,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			
			List<Evaluation> list = this.evaluationService.getUserBuyList(userId);
			
            List<EvaluationNotBuyVo> listVo = new ArrayList<EvaluationNotBuyVo>();
			
			for(Evaluation  d : list){
				EvaluationNotBuyVo dv = new EvaluationNotBuyVo();
				dv.setId(d.getId());
				dv.setUserId(d.getUserId());
				dv.setUserName(d.getUserName());
				dv.setEvaluationImgUrl(d.getEvaluationImgUrl());
				dv.setTitle(d.getTitle());
				dv.setIntroduction(d.getIntroduction());
				dv.setCreateDate(d.getCreateDate());
				dv.setOrderNum(d.getOrderNum());
				dv.setRecommNum(d.getRecommNum());
				dv.setRecommWriteSeqNum(d.getRecommWriteSeqNum());
				dv.setHotNum(d.getHotNum());
				dv.setRemark(d.getRemark());
				listVo.add(dv);
			}
			
			return RestResult.restResult(result, listVo);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	

	@ApiOperation(value = "用户已卖测评查询", notes = "用户已卖测评查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户标识 userId", paramType = "query", required = false, dataType = "long")
	       })
	@RequestMapping(value = "getUserSellList")
	public Map<String, Object> getUserSellList(HttpServletRequest request,
			@RequestParam(value="userId",required=false) Long userId,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			
			List<Evaluation> list = this.evaluationService.getUserSellList(userId);
			
            List<EvaluationNotBuyVo> listVo = new ArrayList<EvaluationNotBuyVo>();
			
			for(Evaluation  d : list){
				EvaluationNotBuyVo dv = new EvaluationNotBuyVo();
				dv.setId(d.getId());
				dv.setUserId(d.getUserId());
				dv.setUserName(d.getUserName());
				dv.setEvaluationImgUrl(d.getEvaluationImgUrl());
				dv.setTitle(d.getTitle());
				dv.setIntroduction(d.getIntroduction());
				dv.setCreateDate(d.getCreateDate());
				dv.setOrderNum(d.getOrderNum());
				dv.setRecommNum(d.getRecommNum());
				dv.setRecommWriteSeqNum(d.getRecommWriteSeqNum());
				dv.setHotNum(d.getHotNum());
				dv.setRemark(d.getRemark());
				listVo.add(dv);
			}
			
			return RestResult.restResult(result, listVo);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	

	@ApiOperation(value = "用户想买测评查询", notes = "用户想买测评查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户标识 userId", paramType = "query", required = false, dataType = "long")
	       })
	@RequestMapping(value = "getUserLikeList")
	public Map<String, Object> getUserLikeList(HttpServletRequest request,
			@RequestParam(value="userId",required=false) Long userId,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			
			List<Evaluation> list = this.evaluationService.getUserLikeList(userId);
			
            List<EvaluationNotBuyVo> listVo = new ArrayList<EvaluationNotBuyVo>();
			
			for(Evaluation  d : list){
				EvaluationNotBuyVo dv = new EvaluationNotBuyVo();
				dv.setId(d.getId());
				dv.setUserId(d.getUserId());
				dv.setUserName(d.getUserName());
				dv.setEvaluationImgUrl(d.getEvaluationImgUrl());
				dv.setTitle(d.getTitle());
				dv.setIntroduction(d.getIntroduction());
				dv.setCreateDate(d.getCreateDate());
				dv.setOrderNum(d.getOrderNum());
				dv.setRecommNum(d.getRecommNum());
				dv.setRecommWriteSeqNum(d.getRecommWriteSeqNum());
				dv.setHotNum(d.getHotNum());
				dv.setRemark(d.getRemark());
				listVo.add(dv);
			}
			
			return RestResult.restResult(result, listVo);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	@ApiOperation(value = "用户测评查询", notes = "用户测评查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户标识 userId", paramType = "query", required = false, dataType = "long")
	       ,@ApiImplicitParam(name = "pageNo", value = "pageNo", paramType = "query", required = false, dataType = "integer")
	})
	@RequestMapping(value = "userEvaluationList")
	public Map<String, Object> userEvaluationList(HttpServletRequest request,
			@RequestParam(value="userId",required=false) Long userId,
			@RequestParam(value="pageNo",required=true) Integer pageNo,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			
			Evaluation evaluation = new Evaluation();
			evaluation.setUserId(userId);
			Page<Evaluation> page = new Page<Evaluation>();
			page.setPageNo(pageNo);
			evaluation.setPage(page);
			Page<Evaluation> list = this.evaluationService.page(evaluation);

			Page<EvaluationNotBuyVo> listVo = new Page<EvaluationNotBuyVo>();
			List<EvaluationNotBuyVo> listdata = listVo.getList();
			for(Evaluation  d : list.getList()){
				EvaluationNotBuyVo dv = new EvaluationNotBuyVo();
				dv.setId(d.getId());
				dv.setUserId(d.getUserId());
				dv.setUserName(d.getUserName());
				dv.setEvaluationImgUrl(d.getEvaluationImgUrl());
				dv.setTitle(d.getTitle());
				dv.setIntroduction(d.getIntroduction());
				dv.setCreateDate(d.getCreateDate());
				dv.setOrderNum(d.getOrderNum());
				dv.setRecommNum(d.getRecommNum());
				dv.setRecommWriteSeqNum(d.getRecommWriteSeqNum());
				dv.setHotNum(d.getHotNum());
				dv.setRemark(d.getRemark());
				listdata.add(dv);
			}
			listVo.setCount(list.getCount());
			listVo.setPageNo(list.getPageNo());
			listVo.setList(listdata);
			return RestResult.restResult(result, listVo);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	@ApiOperation(value = "用户详情", notes = "用户详情", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userid", value = "用户标识userid", paramType = "query", required = true, dataType = "long")
			,@ApiImplicitParam(name = "relationId", value = "是否关注某用户的用户标识relationId", paramType = "query", required = false, dataType = "long")
	})
	@RequestMapping(value = "get")
	public Map<String, Object> get(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="userid",required=true) Long userid,
			@RequestParam(value="relationId",required=false) Long relationId
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			User user = new User();
			user.setId(userid);
			user = this.userService.get(user);
			user.setIsRelation(0);
			if(null != relationId){
				//查看用户是否关注relationId
				FollowUser fu = new FollowUser();
				fu.setUserId(userid);
				fu.setFollowUserId(relationId);
				fu.setStatus(1);
				fu.setType(1);
				int count = this.followUserService.count(fu);
				if(count > 0)user.setIsRelation(1);
			}
			return RestResult.restResult(result, user);
		} catch (Exception e) {
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	@ApiOperation(value = "用户查询", notes = "用户查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
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
			
			log.info(String.format("name=>%s,userStatus=>%s,pageNo=>%s", new Object[]{
					name,pageNo
			}));
			
			Page<User> data = null;
			User user = new User();
			user.setName(name);
			user.getPage().setPageNo(pageNo);
			data = this.userService.page(user);
			return RestResult.restResult(result, data);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}

	@ApiOperation(value = "用户信息更新", notes = "用户信息更新", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
					@ApiImplicitParam(name = "id", value = "用户标识userid", paramType = "query", required = true, dataType = "long")
					,@ApiImplicitParam(name = "userName", value = "用户名称", paramType = "query", required = false, dataType = "string")
					,@ApiImplicitParam(name = "nickName", value = "用户昵称", paramType = "query", required = false, dataType = "string")
					,@ApiImplicitParam(name = "birthdayed", value = "生日 yyyy-MM-dd", paramType = "query", required = false, dataType = "string")
					,@ApiImplicitParam(name = "address", value = "地址", paramType = "query", required = false, dataType = "string")
					,@ApiImplicitParam(name = "cityId", value = "城市代码", paramType = "query", required = false, dataType = "string")
					,@ApiImplicitParam(name = "introduction", value = "签名、介绍", paramType = "query", required = false, dataType = "string")
	})
	@RequestMapping(value = "update")
	public Map<String, Object> update(HttpServletRequest request,
								   HttpServletResponse response,
									  @RequestParam(value="birthdayed",required=false) String birthdayed,
														  User user
	) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());

		try {
			if(null != birthdayed){
				user.setBirthday(sdf.parse(birthdayed));
			}
			this.userService.update(user);
			return RestResult.restResult(result, null);
		} catch (Exception e) {
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}

	}
}
