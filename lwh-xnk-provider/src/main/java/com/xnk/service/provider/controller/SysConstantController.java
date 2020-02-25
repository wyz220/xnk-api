package com.xnk.service.provider.controller;

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

import com.xnk.service.api.model.SysConstant;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.service.SysConstantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="系统静态接口")
@RestController
@RequestMapping(value = "/sysConstant")
public class SysConstantController {

private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysConstantService sysConstantService;
	
	@ApiOperation(value = "静态数据查询", notes = "品牌查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "查询静态类型  0推荐序", paramType = "query", required = false, dataType = "integer")
	       ,@ApiImplicitParam(name = "pageNo", value = "分页页数，默认0，分页大小默认20", paramType = "query", required = false, dataType = "integer")
		   ,@ApiImplicitParam(name = "childType", value = "品牌搜索名称", paramType = "query", required = false, dataType = "integer")
	       })
	@RequestMapping(value = "list")
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value="type",required=false) Integer type,
			@RequestParam(value="childType",required=false) Integer childType,
			@RequestParam(value="pageNo",required=false) Integer pageNo,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			
			log.info(String.format("type=>%s,childType=>%s,pageNo=>%s", new Object[]{
					type,childType,pageNo
			}));
			
			SysConstant po = new SysConstant();
			po.setType(type);
			po.setChildType(childType);
			Page<SysConstant> page = new Page<SysConstant>();
			page.setOrderBy(" seq asc");
			List<SysConstant> list = this.sysConstantService.list(po);
			return RestResult.restResult(result, list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("静态数据查询异常",e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
	
}
