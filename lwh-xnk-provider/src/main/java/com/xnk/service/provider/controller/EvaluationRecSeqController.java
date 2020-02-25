package com.xnk.service.provider.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xnk.service.api.model.EvaluationUserWriteRecomItem;
import com.xnk.service.api.model.User;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.config.SysConfig;
import com.xnk.service.provider.service.EvaluationUserWriteRecomItemService;
import com.xnk.service.provider.service.UserService;
import com.xnk.service.provider.vo.CommonEntityVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="测评推荐序接口")
@RestController
@RequestMapping(value = "/evaluationRecSeq")
public class EvaluationRecSeqController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EvaluationUserWriteRecomItemService service;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "推荐序提交", notes = "推荐序提交", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "evaluationId", value = "测评id", paramType = "query", required = true, dataType = "long"),
			@ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = true, dataType = "long"),
			@ApiImplicitParam(name = "content", value = "多个推荐项以逗号隔开, 如： 1,2,3  1-很有收获 2-很有帮助 3-观点独特 4-文字精准 5-可读性强 6-图拍摄赞 7-图制作赞  ", paramType = "query", required = true, dataType = "string"),
	})
	@RequestMapping(value = "submit")
	public Map<String, Object> submit(HttpServletRequest request,
			@RequestParam(value="evaluationId",required=true) Long evaluationId,
			@RequestParam(value="userId",required=true) Long userId,
			@RequestParam(value="content",required=true) String content,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			
			if(null == content || content.trim().length() == 0 ){
				result.setCode(ResultCode.FAILED.getCode());
				result.setMessage("推荐序未填写");
				return RestResult.restResult(result, null);
			}
			
			User user = new User();
			user.setId(userId);
			User u2 = userService.get(user);
			if(null == u2){
				result.setCode(ResultCode.FAILED.getCode());
				result.setMessage("无效用户");
				return RestResult.restResult(result, null);
			}
			
			// 测评项推荐总人数/购买测评总人数   就是测评项占比
			Date curr = new Date();
			// 要更新测评整体推荐序
			if(content.indexOf(",") == -1){
				//单个推荐序
				EvaluationUserWriteRecomItem e = new EvaluationUserWriteRecomItem();
				e.setUserId(userId);
				e.setEvaluationId(evaluationId);
				e.setType(Integer.valueOf(content));
				List<EvaluationUserWriteRecomItem> list = this.service.list(e);
				if(null == list || list.size() == 0){
					e.setUserName(u2.getNickname());
					e.setCreateTime(curr);
					this.service.insertOne(e);
				}
			}else{
				
				EvaluationUserWriteRecomItem e1 = new EvaluationUserWriteRecomItem();
				e1.setUserId(userId);
				e1.setEvaluationId(evaluationId);
				List<EvaluationUserWriteRecomItem> oldList = this.service.list(e1);
				Set<Integer> set = new HashSet<Integer>();
				for(EvaluationUserWriteRecomItem data :oldList){
					set.add(data.getType());//已填写的推荐序，去重
				}
				//多个推荐序
				String[] datas = content.split(",");
				List<EvaluationUserWriteRecomItem> list = new ArrayList<>();
				for(String data : datas){
					int type = Integer.valueOf(data);
					if(set.contains(type))continue;//已提交推荐序项目
					
					EvaluationUserWriteRecomItem e = new EvaluationUserWriteRecomItem();
					e.setUserId(userId);
					e.setEvaluationId(evaluationId);
					e.setType(type);
					e.setUserName(u2.getNickname());
					e.setCreateTime(curr);
					list.add(e);
				}
				
				if(list.size() > 0 ) this.service.insertBatch(list);
				
			}
			return RestResult.restResult(result, null);
		} catch (Exception e) {

			e.printStackTrace();
			log.error("异常",e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
	}
	
	@ApiOperation(value = "测评所用用户推荐序", notes = "测评所用用户推荐序", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "evaluationId", value = "测评id", paramType = "query", required = true, dataType = "long"),
		@ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = false, dataType = "long"),
		@ApiImplicitParam(name = "userName", value = "用户昵称", paramType = "query", required = false, dataType = "string"),
		@ApiImplicitParam(name = "pageNo", value = "分页页数，默认0，分页大小默认20", paramType = "query", required = false, dataType = "integer")
	       })
	@RequestMapping(value = "list")
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value="evaluationId",required=true) Long evaluationId,
			@RequestParam(value="userId",required=false) Long userId,
			@RequestParam(value="userName",required=false) String userName,
			@RequestParam(value="pageNo",required=false,defaultValue="1") Integer pageNo,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {

			log.info(String.format("evaluationId=>%s,userId=>%s,pageNo=>%s",
					new Object[] { evaluationId, userId, pageNo }));

			EvaluationUserWriteRecomItem en = new EvaluationUserWriteRecomItem();
			en.setEvaluationId(evaluationId);
			en.setUserId(userId);
			en.setUserName(userName);

			Page<EvaluationUserWriteRecomItem> page = new Page<EvaluationUserWriteRecomItem>();
			page.setPageNo(pageNo);
			en.setPage(page);
			Page<EvaluationUserWriteRecomItem> page2 = this.service.page(en);

			for (EvaluationUserWriteRecomItem data : page.getList()) {
				List<Object> childs = null;
				if(data.getTypes() != null ){
					childs = new ArrayList<>();
					if(data.getTypes().indexOf(",")== -1){
						CommonEntityVo vo = new CommonEntityVo();
						vo.setKey(data.getTypes());
						vo.setValue(SysConfig.writeEvaluationRecomMap.get(Integer.valueOf(vo.getKey())));
						childs.add(vo);
					}else{
						String[] types = data.getTypes().split(",");
						for(String type:types){
							CommonEntityVo vo = new CommonEntityVo();
							vo.setKey(type);
							vo.setValue(SysConfig.writeEvaluationRecomMap.get(Integer.valueOf(vo.getKey())));
							childs.add(vo);
							data.setChilds(childs);
						}
					}
					data.setChilds(childs);
				}
			}
			return RestResult.restResult(result, page2);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("用户测评项推荐序列表",e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
}
