/**
 * 
 */
package com.xnk.service.provider.vo;

import java.util.Iterator;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.config.SysConfig;
import com.xnk.service.provider.utils.MD5;

/**
 *
 */
public class PayVo {

	/** 业务类型 */
	private Integer type;// 1 测评支付
	
	private String amount;
	
	private String orderId;
	/** 支付通道 */
	private Integer channel;// 1微信支付
	
	/** wap支付前台通知地址 */
	private String backUrl;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	
	public ResultInfo validParam(String sign){
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		if (this.type == null
	|| this.channel == null
				|| StringUtils.isBlank(this.amount)
				|| StringUtils.isBlank(this.orderId)){
			result.setCode(ResultCode.INVALID_PARAM.getCode());
			result.setMessage(ResultCode.INVALID_PARAM.getMessage());
			return result;
		}

		
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("type", String.valueOf(this.type));
		treeMap.put("amount", this.amount);
		treeMap.put("orderId", this.orderId);
		treeMap.put("channel", String.valueOf(this.channel));
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = treeMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = treeMap.get(key);
			if (StringUtils.isNotEmpty(value)) {
				buffer.append(value);
			}
		}
		
		String calculateSign = MD5.sign(buffer.toString(), SysConfig.SECURITY_SIGN_KEY, "UTF-8");
		if (!calculateSign.equals(sign)){
			result.setCode(ResultCode.INVALID_SIGN.getCode());
			result.setMessage(ResultCode.INVALID_SIGN.getMessage());
			return result;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "PayReqt " + JSON.toJSONString(this);
	}

}
