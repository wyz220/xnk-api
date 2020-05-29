package com.xnk.service.provider.controller;

import com.xnk.service.api.model.Brand;
import com.xnk.service.api.model.SysRegionPo;
import com.xnk.service.provider.common.RestResult;
import com.xnk.service.provider.common.ResultInfo;
import com.xnk.service.provider.service.SysRegionService;
import com.xnk.service.provider.utils.UrlEnDeCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Api(description="地址区域接口")
@RestController
@RequestMapping(value = "/region")
public class SysRegionController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

@Autowired
private SysRegionService sysRegionService;


    @ApiOperation(value = "地址区域查询", notes = "地址区域查询", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentCode", value = "地址父类code,省份默认传0", paramType = "query", required = false, dataType = "string")
            ,@ApiImplicitParam(name = "code", value = "地址code", paramType = "query", required = false, dataType = "string")
            ,@ApiImplicitParam(name = "name", value = "地址名称", paramType = "query", required = false, dataType = "string")
    })
    @RequestMapping(value = "list")
    public Map<String, Object> list(HttpServletRequest request,
                                    @RequestParam(value="parentCode",required=false,defaultValue = "0") String parentCode,
                                    @RequestParam(value="code",required=false) String code,
                                    @RequestParam(value="name",required=false) String name,
                                    HttpServletResponse response
    ) throws Exception {
        ResultInfo result = new ResultInfo();
        result.setCode(ResultInfo.ResultCode.SUCCESS.getCode());
        result.setMessage(ResultInfo.ResultCode.SUCCESS.getMessage());

        try {
            log.info(String.format("parentCode=>%s,code=>%s", new Object[]{
                    parentCode,code
            }));

            SysRegionPo region = new SysRegionPo();

            region.setCode(code);
            region.setParentCode(parentCode);
            region.setName(name);

            List<SysRegionPo> list = this.sysRegionService.list(region);

            return RestResult.restResult(result, list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("地址查询异常",e);
            result.setCode(ResultInfo.ResultCode.FAILED.getCode());
            result.setMessage(ResultInfo.ResultCode.FAILED.getMessage());
            return RestResult.restResult(result, null);
        }

    }
}
