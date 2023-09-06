package com.xin.controller;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.xin.api.CommonResult;
import com.xin.param.test.TestParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author: ljt
 * @Date: 2023/9/6 13:30
 * @Description: TestController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/test")
@Api(tags = {"demo控制层"})
@Validated
@Slf4j
public class TestController {


    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = CommonResult.class),
    })
    @ApiOperation(value = "demo接口", response = JSONObject.class, notes = "demo接口")
    @RequestMapping(path = "/hello", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult hello(@NotNull(message = "参数不能为空！") @RequestParam(name = "name", required = false) String name) {
        return CommonResult.success(name);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = CommonResult.class),
    })
    @ApiOperation(value = "demo接口", response = JSONObject.class, notes = "demo接口")
    @PostMapping(path = "/add")
    public CommonResult add(@Valid @RequestBody TestParam testParam) {
        log.info(JSON.toJSONString(testParam));

        return CommonResult.success(testParam);
    }

}
