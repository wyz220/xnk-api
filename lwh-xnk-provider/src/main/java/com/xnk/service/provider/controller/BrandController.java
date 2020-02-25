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

import com.xnk.service.api.model.Brand;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.common.ResultInfo.ResultCode;
import com.xnk.service.provider.service.BrandService;
import com.xnk.service.provider.utils.UrlEnDeCodeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description="品牌接口")
@RestController
@RequestMapping(value = "/brand")
public class BrandController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BrandService brandService;
	
	@ApiOperation(value = "品牌查询", notes = "品牌查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "brandId", value = "品牌标识id", paramType = "query", required = false, dataType = "long")
	       ,@ApiImplicitParam(name = "pageNo", value = "分页页数，默认0，分页大小默认20", paramType = "query", required = false, dataType = "integer")
		   ,@ApiImplicitParam(name = "name", value = "品牌搜索名称", paramType = "query", required = false, dataType = "string")
	       })
	@RequestMapping(value = "list")
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value="brandId",required=false) Long brandId,
			@RequestParam(value="pageNo",required=false) Integer pageNo,
			@RequestParam(value="name",required=false) String name,
			HttpServletResponse response
			) throws Exception {
		ResultInfo result = new ResultInfo();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMessage(ResultCode.SUCCESS.getMessage());
		
		try {
			name = UrlEnDeCodeUtils.URLDecoderString(name);
			
			log.info(String.format("name=>%s,brandId=>%s,pageNo=>%s", new Object[]{
					name,brandId,pageNo
			}));
			
			Brand brand = new Brand();
			brand.setId(brandId);
			brand.setName(name);
			List<Brand> list = this.brandService.list(brand);
			
			return RestResult.restResult(result, list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("品牌查询异常",e);
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(ResultCode.FAILED.getMessage());
			return RestResult.restResult(result, null);
		}
		
	}
}
