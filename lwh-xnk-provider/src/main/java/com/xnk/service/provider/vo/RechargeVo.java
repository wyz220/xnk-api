/**
 * 
 */
package com.xnk.service.provider.vo;

import java.util.Iterator;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.config.SysConfig;
import com.xnk.service.provider.utils.MD5;

/**
 * @author lindaofen
 *
 */
public class RechargeVo {

	private Integer type;
	private Integer childType;
	private Integer source;//1 andorid 2小程序
	private Long userId;
	private Long evaluationId;
	private String amount;//支付金额
	private Integer payType;
	
	public ResultInfo validParam(String sign){
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		if (this.type == null
				|| this.childType == null
				||this.userId == null
				||this.evaluationId == null
				||this.payType == null
				|| StringUtils.isBlank(amount)){
			result.setCode(ResultCode.INVALID_PARAM.getCode());
			result.setMessage(ResultCode.INVALID_PARAM.getMessage());
			return result;
		}
		
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("type", String.valueOf(this.type));
		treeMap.put("childType", String.valueOf(this.childType));
		treeMap.put("userId", String.valueOf(this.userId));
		treeMap.put("evaluationId", String.valueOf(this.evaluationId));
		treeMap.put("amount", String.valueOf(this.amount));
		treeMap.put("payType", String.valueOf(this.payType));
		treeMap.put("source", String.valueOf(this.source));
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
	
	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public Integer getChildType() {
		return childType;
	}

	public void setChildType(Integer childType) {
		this.childType = childType;
	}

	public Long getEvaluationId() {
		return evaluationId;
	}



	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public Integer getPayType() {
		return payType;
	}



	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
}
