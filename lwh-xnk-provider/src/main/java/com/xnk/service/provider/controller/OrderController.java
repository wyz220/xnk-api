package com.xnk.service.provider.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.xnk.service.api.model.Order;
import com.xnk.service.api.model.User;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.config.SysConfig;
import com.xnk.service.provider.exception.BusinessException;
import com.xnk.service.provider.service.OrderService;
import com.xnk.service.provider.service.UserService;
import com.xnk.service.provider.utils.DesUtil;
import com.xnk.service.provider.utils.StringUtils;
import com.xnk.service.provider.vo.RechargeVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="订单接口")
@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderService service;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "用户充值", notes = "用户充值", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "1 测评充值 ", paramType = "query", required = true, dataType = "integer")
		,@ApiImplicitParam(name = "childType", value = "子类型 默认传0", paramType = "query", required = true, dataType = "integer")
		,@ApiImplicitParam(name = "payType", value = "支付类型  1微信", paramType = "query", required = true, dataType = "integer")
		,@ApiImplicitParam(name = "source", value = "来源 1 android  2小程序", paramType = "query", required = true, dataType = "integer")
		,@ApiImplicitParam(name = "userId", value = "用户标识", paramType = "query", required = true, dataType = "long")
		,@ApiImplicitParam(name = "evaluationId", value = "测评标识", paramType = "query", required = true, dataType = "long")
	    ,@ApiImplicitParam(name = "amount", value = "支付金额（元）", paramType = "query", required = true, dataType = "string")
	    })
	@RequestMapping("/recharge")
	@ResponseBody
	public Map<String, Object> recharge(Model model,
			@RequestParam(value = "extParams", required = true) String extParams,
			@RequestParam(value = "sign", required = true) String sign) {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		Order po = null;
		try {
			String json = DesUtil.decrypt(extParams, SysConfig.SECURITY_ENCRYPT_KEY);
			logger.info("RechargeReqt [json=" + json + ",sign=" + sign + "]");
			RechargeVo vo = JSON.parseObject(json, RechargeVo.class);
			result = vo.validParam(sign);
			if (ResultCode.getResultCode(result.getCode()) != ResultCode.SUCCESS) {
				Map<String, Object> resultMap = RestResult.restResultSign(result, null);
				logger.info("RechargeResp " + JSON.toJSONString(resultMap));
				return resultMap;
			}
			
			User user = new User();
			user.setId(vo.getUserId());
			user = this.userService.get(user);
			
			if(null == user){
				result.setCode(ResultCode.FAILED.getCode());
				result.setMessage("无效用户");
				return RestResult.restResult(result, null);
			}
			
			double amount = Double.valueOf(vo.getAmount());
			po = new Order();
			po.setOrderNo(StringUtils.generateOrderNo());
			po.setUserId(vo.getUserId());
			po.setEvaluationId(vo.getEvaluationId());
			po.setNickName(user.getNickname());
			po.setType(vo.getType());
			po.setChildType(vo.getChildType());
			po.setSource(vo.getSource());
			po.setStatus(0);
			po.setAmount(amount);
			po.setActualPayAmount(amount);
			po.setDiscountAmount(0.0);
			po.setPayType(vo.getPayType());
			po.setCreateTime(new Date());
			service.insert(po);
		} catch (BusinessException e) {
			logger.error("Order recharge exception occurred, cause by:{}", e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			Map<String, Object> resultMap = RestResult.restResultSign(result, null);
			logger.info("RechargeResp " + JSON.toJSONString(resultMap));
			return resultMap;
		} catch (Exception e) {
			logger.error("Order recharge exception occurred, cause by:{}", e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			Map<String, Object> resultMap = RestResult.restResultSign(result, null);
			logger.info("RechargeResp " + JSON.toJSONString(resultMap));
			return resultMap;
		}

		Map<String, Object> resultMap = RestResult.restResultSign(result, po.getId());
		logger.info("RechargeResp " + JSON.toJSONString(resultMap));
		return resultMap;
	}
}
