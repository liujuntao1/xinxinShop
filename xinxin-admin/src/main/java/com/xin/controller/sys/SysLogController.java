package com.xin.controller.sys;

import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xin.api.CommonResult;
import com.xin.api.PageResult;
import com.xin.entity.sys.SysLog;
import com.xin.entity.sys.SysLogExample;
import com.xin.enums.IsDeletedEnum;
import com.xin.mapper.sys.SysLogMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/6 15:34
 * @Description: SysLogController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/sysLog")
@Api(tags = {"日志管理"})
@Validated
@Slf4j
public class SysLogController {

    @Autowired
    private SysLogMapper sysLogMapper;


    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysLog.class),
    })
    @ApiOperation(value = "日志分页列表", response = JSONObject.class, notes = "日志分页列表")
    @RequestMapping(path = "/pageList", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<PageResult<SysLog>> pageList(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                     @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                     @RequestParam(name = "userId", required = false) Integer userId,
                                                     @RequestParam(name = "ip", required = false) String ip) {
        PageHelper.startPage(pageNum, pageSize);
        SysLogExample sysLogExample = new SysLogExample();
        sysLogExample.setOrderByClause("updated_time desc");
        SysLogExample.Criteria criteria = sysLogExample.createCriteria();
        criteria.andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue());
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (StringUtils.isNotBlank(ip)) {
            criteria.andOperationIpLike(StringUtils.join("%", ip, "%"));
        }
        return CommonResult.success(PageResult.convertPageResult(sysLogMapper.selectByExample(sysLogExample)));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysLog.class),
    })
    @ApiOperation(value = "查询全部日志", response = JSONObject.class, notes = "查询全部日志")
    @PostMapping(path = "/list")
    public CommonResult<List<SysLog>> list() {
        return CommonResult.success(sysLogMapper.selectByExample(new SysLogExample()));
    }

}
