package com.xnk.service.provider.common;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.xnk.service.provider.utils.MD5;
import com.xnk.service.provider.utils.StringUtils;


public class RestResult {

	public static Map<String, Object> restResult(ResultInfo resultInfo, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", resultInfo.getCode());
		if (resultInfo.getCode() == ResultInfo.SUCCESS) {
			result.put("result", "success");
		} else {
			result.put("result", "failed");
		}
		
		result.put("message", resultInfo.getMessage());
		if (data!=null) {
			result.put("data", data);
		}
		
		return result;
	}
	
	public static Map<String, Object> restResultSign(ResultInfo resultInfo, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", resultInfo.getCode());
		if (resultInfo.getCode() == ResultInfo.SUCCESS) {
			result.put("result", "success");
		} else {
			result.put("result", "failed");
		}
		
		result.put("message", resultInfo.getMessage());
		if (data!=null) {
			result.put("data", data);
		}

		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("code", result.get("code").toString());
		treeMap.put("result", result.get("result").toString());
		treeMap.put("message", result.get("message") != null ? result.get("message").toString() : null);
		if (data != null){
			String text = JSON.toJSONString(data);
			treeMap.put("data", text);
		}
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = treeMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = treeMap.get(key);
			if (null != value && !"".equals(value.trim())) {
				buffer.append(value);
			}
		}
		
		String sign = MD5.sign(buffer.toString(), "test_key", "UTF-8");
		result.put("sign", sign);
		return result;
	}
	
}
