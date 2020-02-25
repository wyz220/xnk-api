package com.xnk.service.provider.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.xnk.service.api.model.Order;
import com.xnk.service.api.model.Order.OrderStatus;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.config.SysConfig;
import com.xnk.service.provider.exception.BusinessException;
import com.xnk.service.provider.service.OrderService;
import com.xnk.service.provider.service.WeiXinPayService;
import com.xnk.service.provider.utils.ConfigUtil;
import com.xnk.service.provider.utils.DesUtil;
import com.xnk.service.provider.utils.PayCommonUtil;
import com.xnk.service.provider.utils.XMLUtil;
import com.xnk.service.provider.vo.PayVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="支付接口")
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderService service;
	@Autowired
	private WeiXinPayService weiXinPayService;
	
	/** 业务类型 */
	private Integer type;// 1 测评支付
	
	private String amount;
	
	private String orderId;
	/** 支付通道 */
	private Integer channel;// 1微信支付
	
	/** wap支付前台通知地址 */
	private String backUrl;
	
	@ApiOperation(value = "用户支付", notes = "用户支付", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "1 测评支付 ", paramType = "query", required = true, dataType = "integer")
		,@ApiImplicitParam(name = "orderId", value = "订单标识，充值返回的id", paramType = "query", required = true, dataType = "long")
		,@ApiImplicitParam(name = "channel", value = "1 微信支付 ", paramType = "query", required = true, dataType = "integer")
	    ,@ApiImplicitParam(name = "amount", value = "支付金额（元）", paramType = "query", required = true, dataType = "string")
	    })
	@RequestMapping("/payOrder")
	@ResponseBody
	public Map<String, Object> payOrder(Model model, HttpServletRequest request,
			@RequestParam(value = "extParams", required = true) String extParams,
			@RequestParam(value = "sign", required = true) String sign) {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		Map<String, Object> resultMap2 = null;
		try {
			String json = DesUtil.decrypt(extParams, SysConfig.SECURITY_ENCRYPT_KEY);
			logger.info("PayOrderReqt [json=" + json + ",sign=" + sign + "]");
			PayVo vo = JSON.parseObject(json, PayVo.class);
			result = vo.validParam(sign);
			if (ResultCode.getResultCode(result.getCode()) != ResultCode.SUCCESS) {
				Map<String, Object> resultMap = RestResult.restResultSign(result, null);
				logger.info("PayOrderResp " + JSON.toJSONString(resultMap));
				return resultMap;
			}

			Order oParam = new Order();
			oParam.setId(Long.valueOf(vo.getOrderId()));
			Order order = service.get(oParam);
			if (order == null) {
				result.setCode(ResultCode.INVALID_ORDER.getCode());
				result.setMessage(ResultCode.INVALID_ORDER.getMessage());
				Map<String, Object> resultMap = RestResult.restResultSign(result, null);
				logger.info("PayOrderResp " + JSON.toJSONString(resultMap));
				return resultMap;
			}
			if (OrderStatus.getStatus(order.getStatus()) != OrderStatus.WAIT_PAY) {
				result.setCode(ResultCode.INVALID_ORDER_STATUS.getCode());
				result.setMessage(ResultCode.INVALID_ORDER_STATUS.getMessage());
				Map<String, Object> resultMap = RestResult.restResultSign(result, null);
				logger.info("PayOrderResp " + JSON.toJSONString(resultMap));
				return resultMap;
			}

			switch(vo.getChannel()){
			case 1://wx
				resultMap2 = this.weiXinPayService.createOrder(vo, order, request);
				break;
			case 2:
				break;
			default:
				break;
			}

		} catch (BusinessException e) {
			logger.error("Handle get payment order request exception occurred, cause by:{}", e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			Map<String, Object> resultMap = RestResult.restResultSign(result, null);
			logger.info("PayOrderResp " + JSON.toJSONString(resultMap));
			return resultMap;
		} catch (Exception e) {
			logger.error("Handle get payment order request exception occurred, cause by:{}", e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			Map<String, Object> resultMap = RestResult.restResultSign(result, null);
			logger.info("PayOrderResp " + JSON.toJSONString(resultMap));
			return resultMap;
		}
		logger.info("PayOrderResp " + JSON.toJSONString(resultMap2));
		return resultMap2;
	}
	

	@ApiOperation(value="微信支付完成回调")
	@RequestMapping(value="WXPayBack",method=RequestMethod.POST)
	public void WXPayBack(HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("weixin微信支付回调");
		// 读取参数
		InputStream inputStream = request.getInputStream();
		StringBuffer sb = new StringBuffer();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();
		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());        
		// 过滤空 设置 TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = m.get(parameter);

			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}
		
		// 账号信息
		String key = ConfigUtil.API_KEY; // key
		if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
			logger.info("验签正确 --  微信支付成功回调");
			logger.info(JSON.toJSONString(packageParams));
			
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			String resXml = "";
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				String orderNo = (String) packageParams.get("out_trade_no");
				logger.info("微信订单号{} --- 付款成功", orderNo);
				// 这里 根据实际业务场景 做相应的操作				
				service.paySuccess(orderNo);
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			} else {
				logger.info("支付失败,错误信息：{}",packageParams.get("err_code"));
				String orderNo = (String) packageParams.get("out_trade_no");
				service.payFail(orderNo);
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			// ------------------------------
			// 处理业务完毕
			// ------------------------------
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} else {
			logger.info("通知签名验证失败");
		}
	}
	
	@ApiOperation(value="微信支付完成回调")
	@RequestMapping(value="WXPayBack2")
	public void WXPayBack2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String orderNo = "1234";
		logger.info("微信订单号{} --- 付款成功", orderNo);
		// 这里 根据实际业务场景 做相应的操作				
		service.paySuccess(orderNo);
	}
}
