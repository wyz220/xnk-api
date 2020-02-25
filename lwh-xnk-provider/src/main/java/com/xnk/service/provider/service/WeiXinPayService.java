package com.xnk.service.provider.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.Order;
import com.xnk.service.api.model.WeiXinPay;
import com.xnk.service.dao.evaluation.OrderMapper;
import com.xnk.service.dao.user.WeiXinPayMapper;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.provider.mapper.JsonMapper;
import com.xnk.service.provider.utils.AddressUtils;
import com.xnk.service.provider.utils.ConfigUtil;
import com.xnk.service.provider.utils.DateUtils;
import com.xnk.service.provider.utils.Global;
import com.xnk.service.provider.utils.HttpUtil;
import com.xnk.service.provider.utils.PayCommonUtil;
import com.xnk.service.provider.utils.XMLUtil;
import com.xnk.service.provider.vo.PayVo;
import com.xnk.service.service.CrudService;


@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class WeiXinPayService extends CrudService<WeiXinPayMapper,WeiXinPay>{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderMapper orderDao;
	
	public Map<String, Object> createOrder(PayVo vo,Order order , HttpServletRequest request) throws Exception{
		//生成唯一订单号
		String outTradeNo = new Date().getTime() + UUID.randomUUID().toString().substring(0, 20).replaceAll("-", "");
		//全部参数预览appid mch_id nonce_str body out_trade_no total_fee spbill_create_ip notify_url trade_type	 
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		 //设置了常用参数 包括appid mch_id nonce_str
		ConfigUtil.commonParams(packageParams);
		String nonceStr = (String) packageParams.get("nonce_str");
		String notify_url =Global.getConfig("server.context.url") +"/payment/WXPayBack";//回调接口
		String trade_type = "APP";// 交易类型App支付 也可以是小程序支付参数
		String body = "微信支付";
		packageParams.put("body", body);// 商品描述
		packageParams.put("out_trade_no", outTradeNo);// 商户订单号
		//分为单位
		String totalFee =String.valueOf(Math.round(new Double(order.getAmount().doubleValue())* 100));
		packageParams.put("total_fee", totalFee);// 总金额
		String spbillCreateIp = AddressUtils.getIpAddr(request);
		packageParams.put("spbill_create_ip", spbillCreateIp);// 发起人IP地址
		packageParams.put("notify_url", notify_url);// 回调地址
		packageParams.put("trade_type", trade_type);// 交易类型
		//参数和key构成签名 md5加密
		String sign = PayCommonUtil.createSign("UTF-8", packageParams,ConfigUtil.API_KEY);
		log.info("签名sign是 {}" ,sign);
		packageParams.put("sign", sign);// 签名
		String requestXML = PayCommonUtil.getRequestXml(packageParams);
		//预下单结果发送
		String resXml = HttpUtil.postData(ConfigUtil.UNIFIED_ORDER_URL, requestXML);
		log.info("预下单结果发送 返回的resXml信息是 {} " + resXml);
		Map map = XMLUtil.doXMLParse(resXml);
		String returnCode = (String) map.get("return_code");
		log.info("return_code {} ", returnCode);
		String returnMsg = (String) map.get("return_msg");
		log.info("returnMsg {} ", returnMsg);
		// 结果返回
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if("SUCCESS".equals(returnCode)){
			String resultCode = (String) map.get("result_code");
			log.info("resultCode is {}", resultCode);
			String errCodeDes = (String) map.get("err_code_des");
			log.info("errCodeDes is {}", errCodeDes);
			String err_code = (String) map.get("err_code");
			log.info("err_code is {}", err_code);
			if ("SUCCESS".equals(resultCode)) {
				String prepay_id = (String) map.get("prepay_id");
				String requestDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
				WeiXinPay weiXinPay = new WeiXinPay();
				weiXinPay.setPayId(order.getId().intValue());
				weiXinPay.setNonceStr(nonceStr);
				weiXinPay.setBody(body);
				weiXinPay.setOutTradeNo(outTradeNo);
				weiXinPay.setTotalFee(totalFee);
				weiXinPay.setSpbillCreateIp(spbillCreateIp);
				weiXinPay.setNotifyUrl(notify_url);
				weiXinPay.setPrepayId(prepay_id);
				weiXinPay.setReturnCode(returnCode);
				weiXinPay.setReturnMsg(returnMsg);
				int insert = this.dao.insert(weiXinPay);
				
				order.setMchtOrderNo(outTradeNo);
				order.setUpdateTime(new Date());
				int update = orderDao.update(order);
				
				if(insert > 0 && update > 0){
					// 返回前端的参数
					SortedMap<Object, Object> finalpackage = new TreeMap<Object, Object>();
					finalpackage.put("appid", ConfigUtil.APP_ID);
					finalpackage.put("partnerid", ConfigUtil.MCH_ID);
					finalpackage.put("prepayid", prepay_id);
					finalpackage.put("package", "Sign=WXPay");
					finalpackage.put("noncestr", nonceStr);
					String timeStamp = DateUtils.getTimestamp();
					finalpackage.put("timestamp", timeStamp);
					String signF = PayCommonUtil.createSign("UTF-8", finalpackage, ConfigUtil.API_KEY);
					dataMap.put("payInfoId", weiXinPay.getId());
					dataMap.put("prepayid", prepay_id);
					Map<String, Object> frontMap = new HashMap<String, Object>();
					frontMap.put("appid", ConfigUtil.APP_ID);
					frontMap.put("prepayid", prepay_id);
					frontMap.put("partnerid", ConfigUtil.MCH_ID);
					frontMap.put("package_v", "Sign=WXPay");
					frontMap.put("noncestr", nonceStr);
					frontMap.put("timestamp", timeStamp);
					finalpackage.put("sign", signF);
					frontMap.put("sign", signF);
					/*
					 * frontMap.put("sign_b", sign); frontMap.put("sign_c", (String)
					 * map.get("sign"));
					 */
					String requestXMLs = PayCommonUtil.getRequestXml(finalpackage);
					log.info("签名------" + requestXMLs);
					dataMap.put("payInfo", JsonMapper.toJsonString(frontMap));
					returnMap.put("result", "success");
					returnMap.put("data", dataMap);
					returnMap.put("msgCode", "0000");
					returnMap.put("msg", "");
					return returnMap;
				}
				
			}else {
				dataMap.put("returnCode", returnCode);
				dataMap.put("returnMsg", returnMsg);
				dataMap.put("errCode", err_code);
				dataMap.put("errCodeDes", errCodeDes);	
				returnMap.put("data", dataMap);
				returnMap.put("result", "fail");
				return returnMap;
			}
		}else {
			dataMap.put("returnCode", returnCode);
			dataMap.put("returnMsg", returnMsg);
			returnMap.put("data", dataMap);
			returnMap.put("result", "fail");
			return returnMap;
		}
		return returnMap;
	}
	
}
