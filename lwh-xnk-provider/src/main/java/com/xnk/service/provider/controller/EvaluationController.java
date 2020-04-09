package com.xnk.service.provider.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.xnk.service.api.model.Evaluation;
import com.xnk.service.api.model.EvaluationCommentItem;
import com.xnk.service.api.model.EvaluationWriteRecomTotal;
import com.xnk.service.api.model.User;
import com.xnk.service.api.model.UserDo;
import com.xnk.service.api.model.UserEvaluationBuy;
import com.xnk.service.api.vo.EvaluationNotBuyVo;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.config.SysConfig;
import com.xnk.service.provider.service.EvaluationCommentItemService;
import com.xnk.service.provider.service.EvaluationService;
import com.xnk.service.provider.service.EvaluationWriteRecomTotalService;
import com.xnk.service.provider.service.UserDoService;
import com.xnk.service.provider.service.UserEvaluationBuyService;
import com.xnk.service.provider.service.UserService;
import com.xnk.service.provider.service.UserTotalService;
import com.xnk.service.provider.utils.UrlEnDeCodeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import sun.java2d.pipe.SpanShapeRenderer;

@Api(description="测评接口")
@RestController
@RequestMapping(value = "/evaluation")
public class EvaluationController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	private EvaluationService service;
	@Autowired
	private UserEvaluationBuyService userBuyService;
	
	@Autowired
	private UserDoService userDoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EvaluationCommentItemService evaluationCommentItemService;
	
	@Autowired
	private UserTotalService userTotalService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private EvaluationWriteRecomTotalService evaluationWriteRecomTotalService;
	@ApiOperation(value = "获取用户最近测评内容", notes = "获取用户最近测评内容", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "shoeTypeId", value = "鞋类型标识 1-篮球鞋 2-跑鞋", paramType = "query", required = false, dataType = "long")
		,@ApiImplicitParam(name = "userId", value = "用户标识userId", paramType = "query", required = true, dataType = "long")
	    })
	@RequestMapping(value = "getUserRecentEvaluation")
	public Map<String, Object> getUserRecentEvaluation(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="shoeTypeId",required=false) Long shoeTypeId,
			@RequestParam(value="userId",required=true) Long userId
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			Evaluation evaluation = new Evaluation();
			evaluation.setUserId(userId);
			evaluation.setStatus(-1);//待编辑
			evaluation.setShoeTypeId(shoeTypeId);
			Page<Evaluation> page = new Page<Evaluation>();
			page.setOrderBy(" id desc");
			evaluation.setPage(page);
			//aa
			List<Evaluation> list = this.service.list(evaluation);
			
			if(list != null && list.size() > 0){
				evaluation = list.get(0);
			} else {
				//添加一个，返回回去
				User u = new User();
				u.setId(userId);
				User user = this.userService.get(u);
				if(null == user){
					result.setCode(ResultCode.FAILED.getCode());
					result.setMessage("无效用户信息");
					return RestResult.restResult(result, null);
				}
				
				Date curr = new Date();
				evaluation.setEvaluationUserImg(user.getIcon());
				evaluation.setOrderNum(0);
				evaluation.setRecommNum(0);
				evaluation.setHotNum(0);
				evaluation.setCreateDate(curr);
				this.service.insert(evaluation);
			}
			
			return RestResult.restResult(result, evaluation);
		} catch (Exception e) {
			log.error("添加测评异常",e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	

	@ApiOperation(value = "测评更新", notes = "测评更新", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "evaluationId", value = "测评标识evaluationId", paramType = "query", required = true, dataType = "long")
			,@ApiImplicitParam(name = "status", value = "发布测评状态传0", paramType = "query", required = true, dataType = "integer")
			,@ApiImplicitParam(name = "userName", value = "用户名称", paramType = "query", required = true, dataType = "string")
            ,@ApiImplicitParam(name = "introduction", value = "简介", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "evaluationShoeType", value = "测评鞋类型  篮球鞋/跑鞋", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "postion", value = "篮球场上位置", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "halfCourt", value = "全场多还是半场多 单选", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "stackNoBallState", value = "进攻时我的无球状态 多选", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "dribbleUse", value = "哪种运球运用比较多 多选", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "shangLanUse", value = "哪种上篮运用比较多", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "touLanUse", value = "哪种投篮运用比较多", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "attack", value = "关于抢断", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "defend", value = "关于篮板", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "nutCap", value = "关于盖帽", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "basketballPlay", value = "篮球打法描述", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "gender", value = "性别", paramType = "query", required = false, dataType = "integer")
			,@ApiImplicitParam(name = "age", value = "年龄", paramType = "query", required = false, dataType = "integer")
					,@ApiImplicitParam(name = "birthdayed", value = "出生日期 yyyy-MM-dd", paramType = "query", required = false, dataType = "integer"),
					@ApiImplicitParam(name = "height", value = "身高", paramType = "query", required = false, dataType = "double")
			,@ApiImplicitParam(name = "weight", value = "体重", paramType = "query", required = false, dataType = "integer")
			,@ApiImplicitParam(name = "footType", value = "脚形", paramType = "query", required = false, dataType = "string")
					,@ApiImplicitParam(name = "size", value = "尺码大小", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "footTypeFileId", value = "脚形标识", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "footImgUrl", value = "脚形", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "boundImgUrl", value = "足弓", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "boundType", value = "足弓类型", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "boundFeetFileId", value = "足弓标识", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "instepType", value = "脚背", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "instepImgUrl", value = "脚背图片", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "instepFileId", value = "脚背标识", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "physicalFunction", value = "身体机能", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "physicalFunctionFileId", value = "身体机能标识", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "shoeType", value = "鞋类型", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "shoeTypeId", value = "鞋类型标识 1-篮球鞋 2-跑鞋", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "brandName", value = "品牌名称", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "brandId", value = "品牌标识", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "shoeName", value = "品牌鞋款名称", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "shoeId", value = "品牌鞋款标识", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "colorName", value = "配色名称", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "colorId", value = "配色标识", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "isPartialCode", value = "偏码描述", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "isPartialId", value = "偏码描述标识", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "shoeSizeName", value = "鞋形-正常宽带/其他", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "shoeSizeId", value = "鞋形大小", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "sockType", value = "袜子", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "sockTypeId", value = "袜子标识", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "fieldType", value = "篮球场地", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "fieldTypeId", value = "篮球场地标识", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "title", value = "标题", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "evaluationImgUrl", value = "测评封面图", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "summary", value = "总结", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "huanzhenLevel", value = "缓震五星评价 1很差 2差 3一般 4好 5很好", paramType = "query", required = false, dataType = "integer")
			,@ApiImplicitParam(name = "huanzhenImgUrl", value = "缓震图片", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "huanzhenContent", value = "缓震需求内容", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "huanzhenFirst", value = "前掌缓震", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "huanzhenAfter", value = "后掌缓震", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "naimoLevel", value = "耐磨五星评价 1很差 2差 3一般 4好 5很好", paramType = "query", required = false, dataType = "integer")
			,@ApiImplicitParam(name = "naimoImgUrl", value = "耐磨图片", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "naimoContent", value = "耐磨内容填写", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "zhuadiliLevel", value = "抓地力五星评价 1很差 2差 3一般 4好 5很好", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "zhuadiliImgUrl", value = "抓地力图片", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "zhuadiliContent", value = "抓地力内容填写", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "touqiLevel", value = "透气五星评价 1很差 2差 3一般 4好 5很好", paramType = "query", required = false, dataType = "integer")
			,@ApiImplicitParam(name = "touqiImgUrl", value = "透气图片", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "touqiContent", value = "透气内容", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "flexLevel", value = "灵活性五星评价 1很差 2差 3一般 4好 5很好", paramType = "query", required = false, dataType = "integer")
			,@ApiImplicitParam(name = "flexImgUrl", value = "灵活性图片", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "flexShoeMianBottom", value = "灵活性鞋面和鞋底配合", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "flexShoeMian", value = "鞋面防扭功能", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "flexShoeBottom", value = "鞋底防扭功能", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "zhiChengLevel", value = "支撑性五星评价 1很差 2差 3一般 4好 5很好", paramType = "query", required = false, dataType = "integer")
			,@ApiImplicitParam(name = "zhiChengImgUrl", value = "支撑性图片", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "zhiChengMianFirst", value = "支撑性鞋面前掌", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "zhiChengMianMiddle", value = "支撑性鞋面中掌", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "zhiChengMianAfter", value = "支撑性鞋面后掌", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "zhiChengBottomFirst", value = "支撑性鞋底前掌", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "zhiChengBottomMiddle", value = "支撑性鞋底中掌", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "zhiChengBottomAfter", value = "支撑性鞋底中掌", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "packageLevel", value = "包裹性五星评价 1很差 2差 3一般 4好 5很好", paramType = "query", required = false, dataType = "integer")
			,@ApiImplicitParam(name = "packageImgUrl", value = "包裹性图片地址", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "packageFirst", value = "前掌鞋面包裹性", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "packageMiddle", value = "中掌鞋面包裹性", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "packageAfter", value = "后掌鞋面包裹性", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "workLevel", value = "做工五星评价 1很差 2差 3一般 4好 5很好", paramType = "query", required = false, dataType = "integer")
			,@ApiImplicitParam(name = "workImgUrl", value = "做工图片", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "workFengXian", value = "做工缝线", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "workJiaoShui", value = "做工胶水", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "firstFootFeelContent", value = "第一次穿着脚感描述", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "afterUseFootFeelContent", value = "脚感所用时间和磨合后的描述", paramType = "query", required = false, dataType = "string")
					,@ApiImplicitParam(name = "footFeelImgUrl", value = "脚感磨合图片", paramType = "query", required = false, dataType = "string")
					,@ApiImplicitParam(name = "appreanceLevel", value = "外观五星评价 1很差 2差 3一般 4好 5很好", paramType = "query", required = false, dataType = "integer")
			,@ApiImplicitParam(name = "appreanceImgUrl", value = "外观图片", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "appreanceShoeEvaluate", value = "外观鞋评价", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "appreanceColorEvaluate", value = "外观配色评价", paramType = "query", required = false, dataType = "string")
			,@ApiImplicitParam(name = "appreanceInfluence", value = "外观影响", paramType = "query", required = false, dataType = "string")
	})
	
	@RequestMapping(value = "update" , method = {RequestMethod.POST,RequestMethod.GET})
	public Map<String, Object> update(HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam(value = "evaluationId", required = true) Long evaluationId, 
			Evaluation ev,

			@RequestParam(value = "birthdayed", required = false) String birthdayed
			/*@RequestParam(value = "age", required = false) String age,
			@RequestParam(value = "height", required = false) String height,
			@RequestParam(value = "weight", required = false) String weight,
			@RequestParam(value = "footType", required = false) String footType,
			@RequestParam(value = "footTypeFileId", required = false) String footTypeFileId,
			@RequestParam(value = "footImgUrl", required = false) String footImgUrl,
			@RequestParam(value = "boundImgUrl", required = false) String boundImgUrl,
			@RequestParam(value = "boundType", required = false) String boundType,
			@RequestParam(value = "boundFeetFileId", required = false) String boundFeetFileId,
			@RequestParam(value = "instepType", required = false) String instepType,
			@RequestParam(value = "instepImgUrl", required = false) String instepImgUrl,
			@RequestParam(value = "instepFileId", required = false) String instepFileId,
			@RequestParam(value = "physicalFunction", required = false) String physicalFunction,
			@RequestParam(value = "physicalFunctionFileId", required = false) String physicalFunctionFileId, 

			@RequestParam(value = "shoeType", required = false) String shoeType,
			@RequestParam(value = "shoeTypeId", required = false) String shoeTypeId,
			@RequestParam(value = "brandName", required = false) String brandName,
			@RequestParam(value = "brandId", required = false) String brandId,
			@RequestParam(value = "isPartialCode", required = false) String isPartialCode,
			@RequestParam(value = "isPartialId", required = false) String isPartialId,
			@RequestParam(value = "shoeSizeName", required = false) String shoeSizeName,
			@RequestParam(value = "shoeSizeId", required = false) String shoeSizeId,
			@RequestParam(value = "sockType", required = false) String sockType,
			@RequestParam(value = "sockTypeId", required = false) String sockTypeId,
			@RequestParam(value = "fieldType", required = false) String fieldType,
			@RequestParam(value = "fieldTypeId", required = false) String fieldTypeId, 

			@RequestParam(value = "evaluationShoeType", required = false) String evaluationShoeType,
			@RequestParam(value = "postion", required = false) String postion,
			@RequestParam(value = "halfCourt", required = false) String halfCourt,
			@RequestParam(value = "stackNoBallState", required = false) String stackNoBallState,
			@RequestParam(value = "dribbleUse", required = false) String dribbleUse,
			@RequestParam(value = "shangLanUse", required = false) String shangLanUse,
			@RequestParam(value = "touLanUse", required = false) String touLanUse,
			@RequestParam(value = "attack", required = false) String attack,
			@RequestParam(value = "defend", required = false) String defend,
			@RequestParam(value = "nutCap", required = false) String nutCap,
			@RequestParam(value = "basketballPlay", required = false) String basketballPlay,

			@RequestParam(value = "firstFootFeelContent", required = false) String firstFootFeelContent,
			@RequestParam(value = "afterUseFootFeelContent", required = false) String afterUseFootFeelContent,
			@RequestParam(value = "packageLevel", required = false) String packageLevel,
			@RequestParam(value = "packageImgUrl", required = false) String packageImgUrl,
			@RequestParam(value = "packageFirst", required = false) String packageFirst,
			@RequestParam(value = "packageMiddle", required = false) String packageMiddle,
			@RequestParam(value = "packageAfter", required = false) String packageAfter,
			@RequestParam(value = "workLevel", required = false) String workLevel,
			@RequestParam(value = "workImgUrl", required = false) String workImgUrl,
			@RequestParam(value = "workFengXian", required = false) String workFengXian,
			@RequestParam(value = "workJiaoShui", required = false) String workJiaoShui,
			@RequestParam(value = "appreanceLevel", required = false) String appreanceLevel,
			@RequestParam(value = "appreanceImgUrl", required = false) String appreanceImgUrl,
			@RequestParam(value = "appreanceShoeEvaluate", required = false) String appreanceShoeEvaluate,
			@RequestParam(value = "appreanceColorEvaluate", required = false) String appreanceColorEvaluate,
			@RequestParam(value = "appreanceInfluence", required = false) String appreanceInfluence,
			@RequestParam(value = "zhuadiliLevel", required = false) String zhuadiliLevel,
			@RequestParam(value = "zhuadiliImgUrl", required = false) String zhuadiliImgUrl,
			@RequestParam(value = "zhuadiliContent", required = false) String zhuadiliContent,
			@RequestParam(value = "touqiLevel", required = false) String touqiLevel,
			@RequestParam(value = "touqiImgUrl", required = false) String touqiImgUrl,
			@RequestParam(value = "touqiContent", required = false) String touqiContent,
			@RequestParam(value = "flexLevel", required = false) String flexLevel,
			@RequestParam(value = "flexImgUrl", required = false) String flexImgUrl,
			@RequestParam(value = "flexShoeMianBottom", required = false) String flexShoeMianBottom,
			@RequestParam(value = "flexShoeMian", required = false) String flexShoeMian,
			@RequestParam(value = "flexShoeBottom", required = false) String flexShoeBottom,
			@RequestParam(value = "zhiChengLevel", required = false) String zhiChengLevel,
			@RequestParam(value = "zhiChengImgUrl", required = false) String zhiChengImgUrl,
			@RequestParam(value = "zhiChengMianFirst", required = false) String zhiChengMianFirst,
			@RequestParam(value = "zhiChengMianMiddle", required = false) String zhiChengMianMiddle,
			@RequestParam(value = "zhiChengMianAfter", required = false) String zhiChengMianAfter,
			@RequestParam(value = "zhiChengBottomFirst", required = false) String zhiChengBottomFirst,
			@RequestParam(value = "zhiChengBottomMiddle", required = false) String zhiChengBottomMiddle,
			@RequestParam(value = "zhiChengBottomAfter", required = false) String zhiChengBottomAfter,
			@RequestParam(value = "introduction", required = false) String introduction,
			@RequestParam(value = "evaluationImgUrl", required = false) String evaluationImgUrl,
			@RequestParam(value = "summary", required = false) String summary,
			@RequestParam(value = "huanzhenLevel", required = false) String huanzhenLevel,
			@RequestParam(value = "huanzhenImgUrl", required = false) String huanzhenImgUrl,
			@RequestParam(value = "huanzhenContent", required = false) String huanzhenContent,
			@RequestParam(value = "huanzhenFirst", required = false) String huanzhenFirst,
			@RequestParam(value = "huanzhenAfter", required = false) String huanzhenAfter,
			@RequestParam(value = "naimoLevel", required = false) String naimoLevel,
			@RequestParam(value = "naimoImgUrl", required = false) String naimoImgUrl,
			@RequestParam(value = "naimoContent", required = false) String naimoContent*/
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			ev.setId(evaluationId);
			
			if(null != ev.getFootTypeFileId()) ev.setFootType(SysConfig.footMap.get(ev.getFootTypeFileId().intValue()));
			if(null != ev.getBoundFeetFileId()) ev.setBoundType(SysConfig.boundMap.get(ev.getBoundFeetFileId().intValue()));
			if(null != ev.getInstepFileId()) ev.setInstepType(SysConfig.instepMap.get(ev.getInstepFileId().intValue()));
			if(null != ev.getPhysicalFunctionFileId()) ev.setPhysicalFunction(SysConfig.physicalFunctionMap.get(ev.getPhysicalFunctionFileId().intValue()));
			if(null != ev.getShoeTypeId()) ev.setShoeType(SysConfig.shoeTypeMap.get(ev.getShoeTypeId().intValue()));
			if(null != ev.getIsPartialId()) ev.setIsPartialCode(SysConfig.isPartialMap.get(ev.getIsPartialId().intValue()));
			if(null != ev.getShoeSizeId()) ev.setShoeSizeName(SysConfig.shoeSizeMap.get(ev.getShoeSizeId().intValue()));
			if(null != ev.getSockTypeId()) ev.setSockType(SysConfig.sockTypeMap.get(ev.getSockTypeId().intValue()));
			if(null != ev.getFieldTypeId()) ev.setFieldType(SysConfig.fieldTypeMap.get(ev.getFieldTypeId().intValue()));
			if(null != birthdayed){

				Date birthday = sdf.parse(birthdayed);
				ev.setBirthday(birthday);
			}
			this.service.update(ev);
			
			return RestResult.restResult(result, null);
		} catch (Exception e) {
			log.error("更新测评异常",e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	@ApiOperation(value = "测评想买", notes = "测评想买", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "evaluationId", value = "测评标识evaluationId", paramType = "query", required = true, dataType = "long"),
			@ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = true, dataType = "long")
	})
	@RequestMapping(value = "isNeedBuy")
	public Map<String, Object> isNeedBuy(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="evaluationId",required=true) Long evaluationId,
			@RequestParam(value="userId",required=true) Long userId
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			UserDo u = new UserDo();
			u.setUserId(userId);
			u.setEvaluationId(evaluationId);
			u.setStatus(1);
			u.setType(2);//想买类型
			UserDo userDo = this.userDoService.get(u);
			if(null != userDo)return RestResult.restResult(result, 1);
				
			return RestResult.restResult(result, 0);
		} catch (Exception e) {
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	@ApiOperation(value = "测评详情", notes = "测评详情", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "evaluationId", value = "测评标识evaluationId", paramType = "query", required = true, dataType = "long"),
		@ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = false, dataType = "long")
})
	@RequestMapping(value = "get")
	public Map<String, Object> get(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="evaluationId",required=true) Long evaluationId,
			@RequestParam(value="userId",required=false) Long userId
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			Evaluation evaluation = new Evaluation();
			evaluation.setId(evaluationId);
			evaluation = this.service.get(evaluation);
			
			if(null != evaluation){
				evaluation.setIsBuy(0);//不传userId 默认用户未购买
				if(null != userId){
					//未购买要进行处理
					UserEvaluationBuy entity = new UserEvaluationBuy();
					entity.setUserId(userId);
					entity.setEvaluationId(evaluation.getId());
					List<UserEvaluationBuy> list = this.userBuyService.list(entity);
					if(null == list || list.size() == 0){//未购买
						EvaluationNotBuyVo dv = new EvaluationNotBuyVo();
						dv.setId(evaluation.getId());
						dv.setUserId(evaluation.getUserId());
						dv.setUserName(evaluation.getUserName());
						dv.setEvaluationImgUrl(evaluation.getEvaluationImgUrl());
						dv.setTitle(evaluation.getTitle());
						dv.setIntroduction(evaluation.getIntroduction());
						dv.setCreateDate(evaluation.getCreateDate());
						dv.setOrderNum(evaluation.getOrderNum());
						dv.setRecommNum(evaluation.getRecommNum());
						dv.setRecommWriteSeqNum(evaluation.getRecommWriteSeqNum());
						dv.setHotNum(evaluation.getHotNum());
						dv.setRemark(evaluation.getRemark());
						dv.setIsBuy(0);
						dv.setEvaluationUserImg(evaluation.getEvaluationUserImg());
						return RestResult.restResult(result, dv);
					}
					evaluation.setIsBuy(1);
				}
				
			}
			return RestResult.restResult(result, evaluation);
		} catch (Exception e) {
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	@ApiOperation(value = "测评推荐序", notes = "测评推荐序", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "evaluationId", value = "测评标识evaluationId", paramType = "query", required = true, dataType = "long")})
	@RequestMapping(value = "getWriteRecommend")
	public Map<String, Object> getWriteRecommend(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="evaluationId",required=true) Long evaluationId
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			
			EvaluationWriteRecomTotal evaluation = new EvaluationWriteRecomTotal();
			evaluation.setEvaluationId(evaluationId);
			Page<EvaluationWriteRecomTotal> page = new Page<EvaluationWriteRecomTotal>();
			page.setOrderBy(" seq asc");
			List<EvaluationWriteRecomTotal> list = this.evaluationWriteRecomTotalService.list(evaluation);
			return RestResult.restResult(result, list);
		} catch (Exception e) {
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	
	@ApiOperation(value = "测评查询", notes = "测评查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "evaluationStatus", value = "类型查询，0-查询所有 1-按推荐查询  2-按最新更新查询  3-按热门查询 ", paramType = "query", required = true, dataType = "integer")
			,@ApiImplicitParam(name = "shoeTypeId", value = "鞋类型标识 1-篮球鞋 2-跑鞋", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "pageNo", value = "分页页数，默认0，分页大小默认20", paramType = "query", required = true, dataType = "integer")
		   ,@ApiImplicitParam(name = "name", value = "测评搜索标题名称", paramType = "query", required = false, dataType = "string")
	       })
	@RequestMapping(value = "list")
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value="evaluationStatus",required=true) Integer evaluationStatus,
			@RequestParam(value="pageNo",required=false) Integer pageNo,
			@RequestParam(value="shoeTypeId",required=false) Long shoeTypeId,
			@RequestParam(value="name",required=false) String name,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			name = UrlEnDeCodeUtils.URLDecoderString(name);
			
			log.info(String.format("name=>%s,evaluationStatus=>%s,pageNo=>%s", new Object[]{
					name,evaluationStatus,pageNo
			}));
			
			Page<Evaluation> data = null;
			Evaluation evaluation = new Evaluation();
			switch(evaluationStatus){
			case 0:
				break;
			case 1:
				evaluation.getPage().setOrderBy(" recomm_num desc");
				break;
			case 2:
				evaluation.getPage().setOrderBy(" modify_date desc");
				break;
			case 3:
				evaluation.getPage().setOrderBy(" hot_num desc");
				break;
			default:
				break;
			}
			evaluation.setStatus(1);//审核通过的
			evaluation.setTitle(name);
			evaluation.setShoeTypeId(shoeTypeId);
			evaluation.getPage().setPageNo(pageNo);
			data = this.service.page(evaluation);
			
			Page<EvaluationNotBuyVo> dataNotBuy = new Page<EvaluationNotBuyVo>();
			List<EvaluationNotBuyVo> list = dataNotBuy.getList();
			for(Evaluation d : data.getList()){
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
				list.add(dv);
			}
			return RestResult.restResult(result, dataNotBuy);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}

	@ApiOperation(value = "测评相关推荐查询", notes = "测评相关推荐查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "shoeTypeId", value = "鞋类型标识 1-篮球鞋 2-跑鞋", paramType = "query", required = false, dataType = "long")
			,@ApiImplicitParam(name = "evaluationId", value = "测评id", paramType = "query", required = true, dataType = "long")
	       })
	@RequestMapping(value = "relationList")
	public Map<String, Object> relationList(HttpServletRequest request,
			@RequestParam(value="evaluationId",required=true) Long evaluationId,
			@RequestParam(value="shoeTypeId",required=false) Long shoeTypeId,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			log.info(String.format("evaluationId=>%s", new Object[]{
					evaluationId
			}));
			
			Evaluation evaluation = new Evaluation();
			evaluation.setId(evaluationId);
			evaluation = this.service.get(evaluation);
			if(null == evaluation){
				result.setCode(ResultCode.FAILED.getCode());
				result.setMessage("测评不存在");
				return RestResult.restResult(result, null);
			}
			Evaluation param = new Evaluation();
			Page<Evaluation> page = new Page<Evaluation>();
			page.setCondition(" and id!="+evaluation.getId() );
			param.setColorId(evaluation.getColorId());
			param.setShoeTypeId(evaluation.getShoeTypeId());//通过篮球鞋  跑鞋
			param.setPage(page);
			List<Evaluation> list = this.service.list(param);
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
	
	@ApiOperation(value = "测评用户推荐/想买", notes = "测评用户推荐/想买", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "evaluationId", value = "测评id", paramType = "query", required = true, dataType = "long"),
			@ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = true, dataType = "long"),
			@ApiImplicitParam(name = "type", value = "1用户推荐 2用户想买 3取消推荐 4用户取消想买 ", paramType = "query", required = true, dataType = "integer")
		       })
	@RequestMapping(value = "userRecOrNeedBuy")
	public Map<String, Object> userRecOrNeedBuy(HttpServletRequest request,
			@RequestParam(value="evaluationId",required=true) Long evaluationId,
			@RequestParam(value="userId",required=true) Long userId,
			@RequestParam(value="type",required=true) Integer type,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			log.info(String.format("evaluationId=>%s,userId=>%s,type=>%s", new Object[]{
					evaluationId,userId,type
			}));
			if(userId == null || type == null || evaluationId == null){
				result.setCode(ResultCode.FAILED.getCode());
				result.setMessage(ResultCode.FAILED.getMessage());
				return RestResult.restResult(result, null);
			}
			
			UserDo u = new UserDo();
			u.setUserId(userId);
			u.setEvaluationId(evaluationId);
			UserDo userDo = null;
			Date curr = new Date();
			switch(type){
			case 1://用户推荐
				u.setType(type);
				u.setStatus(1);
				userDo = this.userDoService.get(u);
				if(null == userDo){
					//1推荐测评 2 想买测评
					UserDo used = new UserDo();
					used.setType(type);
					used.setChildType(0);
					used.setUserId(userId);
					used.setEvaluationId(evaluationId);
					used.setStatus(1);
					used.setCreateTime(curr);
					this.userDoService.insert(used);
				}
				break;
			case 2://用户想买
				u.setType(type);
				u.setStatus(1);
				userDo = this.userDoService.get(u);
				if(null == userDo){
					//1推荐测评 2 想买测评
					UserDo used = new UserDo();
					used.setType(type);
					used.setChildType(0);
					used.setUserId(userId);
					used.setEvaluationId(evaluationId);
					used.setStatus(1);
					used.setCreateTime(curr);
					this.userDoService.insert(used);
				}
				break;
			case 3://用户取消推荐
				u.setType(1);
				u.setStatus(1);
				userDo = this.userDoService.get(u);
				if(null != userDo){
					//1推荐测评 2 想买测评
					userDo.setStatus(0);
					this.userDoService.update(userDo);
				}
				break;
			case 4://用户取消想买
				u.setType(2);
				u.setStatus(1);
				userDo = this.userDoService.get(u);
				if(null != userDo){
					//1推荐测评 2 想买测评
					userDo.setStatus(0);
					this.userDoService.update(userDo);
				}
				break;
			default:
				break;
			}
			
			return RestResult.restResult(result, null);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	
	@ApiOperation(value = "测评用户项点赞和评论", notes = "测评用户项点赞和评论", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "evaluationId", value = "测评id", paramType = "query", required = true, dataType = "long"),
			@ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = true, dataType = "long"),
			@ApiImplicitParam(name = "childType", value = "测评子项 标识  1脚感磨合  2外观设计 3做工 4包裹性 5支撑性 6灵活性 7透气性 8抓地力 9耐磨性 10缓震性", paramType = "query", required = true, dataType = "integer"),
			@ApiImplicitParam(name = "type", value = "1点赞  2评论 ", paramType = "query", required = true, dataType = "integer"),
			@ApiImplicitParam(name = "content", value = " 当type为2，此项必填", paramType = "query", required = false, dataType = "string"),
			@ApiImplicitParam(name = "parentId", value = "父类评论id,用户追加评论展示", paramType = "query", required = false, dataType = "long")
	})
	
	@RequestMapping(value = "userCommentOrGood")
	public Map<String, Object> userCommentOrGood(HttpServletRequest request,
			@RequestParam(value="evaluationId",required=true) Long evaluationId,
			@RequestParam(value="userId",required=true) Long userId,
			@RequestParam(value="type",required=true) Integer type,
			@RequestParam(value="childType",required=true) Integer childType,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="parentId",required=false) Long parentId,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			log.info(String.format("evaluationId=>%s,userId=>%s,type=>%s", new Object[]{
					evaluationId,userId,type
			}));
			if(userId == null || type == null || evaluationId == null || childType == null){
				result.setCode(ResultCode.FAILED.getCode());
				result.setMessage(ResultCode.FAILED.getMessage());
				return RestResult.restResult(result, null);
			}
			
			if(type == 2 && StringUtils.isEmpty(content)){
				result.setCode(ResultCode.FAILED.getCode());
				result.setMessage("评论内容不为空");
				return RestResult.restResult(result, null);
			}
			
			Evaluation en = new Evaluation();
			en.setId(evaluationId);
			Evaluation evaluation = this.service.get(en);
			if(null == evaluation){
				result.setCode(ResultCode.FAILED.getCode());
				result.setMessage("测评不存在");
				return RestResult.restResult(result, null);
			}
			
			UserDo u = new UserDo();
			u.setUserId(userId);
			u.setEvaluationId(evaluationId);
			UserDo userDo = null;
			Date curr = new Date();
			switch(type){
			case 1://点赞
				u.setType(UserDo.Type.EvealCommentChild.getType());//数据点赞标识
				u.setStatus(1);
				u.setChildType(childType);
				userDo = this.userDoService.get(u);
				if(null == userDo){
					//1推荐测评 2 想买测评
					UserDo used = new UserDo();
					used.setType(UserDo.Type.EvealCommentChild.getType());
					used.setChildType(childType);
					used.setUserId(userId);
					used.setEvaluationId(evaluationId);
					used.setStatus(1);
					used.setCreateTime(curr);
					this.userDoService.insertCommentGood(used,evaluation);
				}
				break;
			case 2://评论
				
				User user = new User();
				user.setId(userId);
				User u2 = userService.get(user);
				if(null == u2){
					result.setCode(ResultCode.FAILED.getCode());
					result.setMessage("无效用户");
					return RestResult.restResult(result, null);
				}
				EvaluationCommentItem citem = new EvaluationCommentItem();

			    citem.setParentId(parentId);
			    citem.setEvaluationId(evaluationId);
			    citem.setUserId(userId);
			    citem.setUserName(u2.getNickname());
			    citem.setType(childType);//测评此项
			    citem.setContent(content);
			    citem.setCreateTime(curr);
			    
			    this.evaluationCommentItemService.insertComment(citem,evaluation);
				break;
			default:
				break;
			}
			return RestResult.restResult(result, null);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	@ApiOperation(value = "测评项评论内容列表", notes = "测评项评论内容列表", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "evaluationId", value = "测评id", paramType = "query", required = true, dataType = "long"),
			@ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", required = true, dataType = "long"),
			@ApiImplicitParam(name = "childType", value = "测评子项 标识  1脚感磨合  2外观设计 3做工 4包裹性 5支撑性 6灵活性 7透气性 8抓地力 9耐磨性 10缓震性", paramType = "query", required = true, dataType = "integer"),
	})
	@RequestMapping(value = "evaluationCommentList")
	public Map<String, Object> evaluationCommentList(HttpServletRequest request,
			@RequestParam(value="evaluationId",required=true) Long evaluationId,
			@RequestParam(value="userId",required=true) Long userId,
			@RequestParam(value="childType",required=true) Integer childType,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			log.info(String.format("evaluationId=>%s,userId=>%s,childType=>%s", new Object[]{
					evaluationId,userId,childType
			}));
			if(userId == null || evaluationId == null || childType == null){
				result.setCode(ResultCode.FAILED.getCode());
				result.setMessage(ResultCode.FAILED.getMessage());
				return RestResult.restResult(result, null);
			}
			
			
			Evaluation en = new Evaluation();
			en.setId(evaluationId);
			Evaluation evaluation = this.service.get(en);
			if(null == evaluation){
				result.setCode(ResultCode.FAILED.getCode());
				result.setMessage("测评不存在");
				return RestResult.restResult(result, null);
			}
			
			UserEvaluationBuy entity = new UserEvaluationBuy();
			entity.setUserId(evaluation.getUserId());
			entity.setEvaluationId(evaluation.getId());
			List<UserEvaluationBuy> list = this.userBuyService.list(entity);
			if(null == list || list.size() == 0){//未购买
				result.setCode(ResultCode.FAILED.getCode());
				result.setMessage("未购买测评");
				return RestResult.restResult(result, null);
			}
			
			EvaluationCommentItem citem = new EvaluationCommentItem();

		    citem.setEvaluationId(evaluationId);
		    citem.setType(childType);//测评此项

		    List<EvaluationCommentItem> cList = this.evaluationCommentItemService.list(citem);
		    Map<Long,List<EvaluationCommentItem>> parent = Maps.newConcurrentMap();
		    
		    for(EvaluationCommentItem item : cList){
		    		List<EvaluationCommentItem> childList = parent.get(item.getParentId());
		    		if(childList == null){
		    			childList = new ArrayList<EvaluationCommentItem>();
		    			childList.add(item);
			    		parent.put(item.getParentId(), childList);
		    		} else{
		    			childList.add(item);
		    		}
		    } 
		    
		    //递归回溯所有评论
		    init(0L,parent);
		    
		    List<EvaluationCommentItem> data = parent.get(0L);//评论第一层级
		    
			return RestResult.restResult(result, data);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("异常",e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}

	/**
	 * 回溯算法获取评论信息
	 * @param parentId
	 * @param parent
	 */
	private void init(Long parentId, Map<Long, List<EvaluationCommentItem>> parent) {
		List<EvaluationCommentItem> list = parent.get(parentId);
		if(null == list || list.size() == 0) return;
		
		Iterator<EvaluationCommentItem> iterator = list.iterator();//对集合修改使用迭代器
	    while(iterator.hasNext()){
	    	EvaluationCommentItem next = iterator.next();
	    	List<EvaluationCommentItem> childList = parent.get(next.getId());
	    	
	    	next.setChild(childList);
	    	
	    	init(next.getId(),parent);
	    }
	    
	}


}
