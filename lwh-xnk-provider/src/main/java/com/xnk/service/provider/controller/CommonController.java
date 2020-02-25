package com.xnk.service.provider.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xnk.service.api.model.EvaluationCommon;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.config.SysConfig;
import com.xnk.service.provider.utils.FastDFSClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="公共接口")
@RestController
@RequestMapping(value = "/common")
public class CommonController {

	@Autowired
	private FastDFSClient fastDFSClient;
	
	@Value("${server.url}")
	private String serverUrl;
	
	@ApiOperation(value = "文件上传", notes = "文件上传", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "file", value = "文件类型", paramType = "query", required = true, dataType = "long")
	    })
	@RequestMapping(method = {RequestMethod.POST}, value = {"/upload"})
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("file")MultipartFile file,
			HttpServletRequest request, HttpServletResponse response)  {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		try {
			//fastdfs方式上传
			String suffix = file.getOriginalFilename()
					.substring(file.getOriginalFilename().lastIndexOf(".")+1);

			String path = this.fastDFSClient.uploadFile(file,suffix);
			
			return RestResult.restResult(result, serverUrl + path);
		}catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "静态数据查询", notes = "静态数据查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "0   静态数据查询", paramType = "query", required = true, dataType = "long")
	    })
	@RequestMapping(value = "list")
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value="type",required=true) Integer type,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			
			Map map = new HashMap();
			if(type == 0){
				map.put("basketBallPostionList", mapToList(SysConfig.basketBallPostionMap));
				map.put("halfCourtList",mapToList(SysConfig.halfCourtMap) );
				map.put("stackNoBallStateList",mapToList(SysConfig.stackNoBallStateMap) );
				map.put("dribbleUseList",mapToList(SysConfig.dribbleUseMap) );
				map.put("shangLanUseList",mapToList(SysConfig.shangLanUseMap) );
				map.put("touLanUseList",mapToList(SysConfig.touLanUseMap) );
				map.put("attackList",mapToList(SysConfig.attackMap) );
				map.put("defendList",mapToList(SysConfig.defendMap) );
				map.put("nutCapList",mapToList(SysConfig.nutCapMap) );
				map.put("footList",mapToList(SysConfig.footMap) );
				map.put("boundList",mapToList(SysConfig.boundMap) );
				map.put("instepList",mapToList(SysConfig.instepMap) );
				map.put("physicalFunctionList",mapToList(SysConfig.physicalFunctionMap) );
				map.put("shoeTypeList",mapToList(SysConfig.shoeTypeMap) );
				map.put("isPartialList",mapToList(SysConfig.isPartialMap) );
				map.put("shoeSizeList",mapToList(SysConfig.shoeSizeMap) );
				map.put("sockTypeList",mapToList(SysConfig.sockTypeMap) );
				map.put("fieldTypeList",mapToList(SysConfig.fieldTypeMap) );
			}
			
			if(type == 1){
				map.put("writeEvaluationRecomList",mapToList(SysConfig.writeEvaluationRecomMap) );
			}
			
			if(type == 2){
				map.put("conditionEvaluationList",mapToList(SysConfig.conditionEvaluationMap) );
			}
			
			
			return RestResult.restResult(result, map);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
	private List<EvaluationCommon> mapToList(Map<Integer, String> map){
		
		List<EvaluationCommon> list = new ArrayList<>(10);
		
		Set<Entry<Integer, String>> entrySet = map.entrySet();
		
		Iterator<Entry<Integer, String>> iterator = entrySet.iterator();
		
		while(iterator.hasNext()){
			EvaluationCommon common = new EvaluationCommon();
			Entry<Integer, String> next = iterator.next();
			common.setId(next.getKey().toString()
					);
			common.setName(next.getValue());
			list.add(common);
		}
		
		return list;
	}
}
