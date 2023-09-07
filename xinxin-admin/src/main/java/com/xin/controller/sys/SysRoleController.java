package com.xin.controller.sys;

import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xin.api.CommonResult;
import com.xin.api.PageResult;
import com.xin.entity.sys.SysRole;
import com.xin.entity.sys.SysRoleExample;
import com.xin.entity.sys.SysUser;
import com.xin.enums.IsDeletedEnum;
import com.xin.mapper.sys.SysRoleMapper;
import com.xin.param.sys.SaveSysUserParam;
import com.xin.utils.Asserts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/6 15:34
 * @Description: SysRoleController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/sysRole")
@Api(tags = {"角色管理"})
@Validated
@Slf4j
public class SysRoleController {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysRole.class),
    })
    @ApiOperation(value = "角色分页列表", response = JSONObject.class, notes = "角色分页列表")
    @RequestMapping(path = "/pageList", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<PageResult<SysRole>> pageList(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                      @RequestParam(name = "name", required = false) String name,
                                                      @RequestParam(name = "code", required = false) String code) {
        PageHelper.startPage(pageNum, pageSize);
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.setOrderByClause("updated_time desc");
        SysRoleExample.Criteria criteria = sysRoleExample.createCriteria();
        criteria.andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue());
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(StringUtils.join("%", name, "%"));
        }
        if (StringUtils.isNotBlank(code)) {
            criteria.andCodeLike(StringUtils.join("%", code, "%"));
        }
        return CommonResult.success(PageResult.convertPageResult(sysRoleMapper.selectByExample(sysRoleExample)));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysRole.class),
    })
    @ApiOperation(value = "新增角色", response = JSONObject.class, notes = "新增角色")
    @PostMapping(path = "/insert")
    public CommonResult<SysRole> insert(@RequestBody SaveSysUserParam saveSysUserParam) {
        SysRole sysUser = new SysRole();
        BeanUtils.copyProperties(saveSysUserParam, sysUser);
        sysRoleMapper.insertSelective(sysUser);
        return CommonResult.success(sysUser);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysRole.class),
    })
    @ApiOperation(value = "修改角色", response = JSONObject.class, notes = "修改角色")
    @PostMapping(path = "/update")
    public CommonResult<SysRole> update(@RequestBody SaveSysUserParam saveSysUserParam) {
        if (saveSysUserParam.getId() == null) {
            Asserts.fail("未找到角色信息！");
        }
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(saveSysUserParam.getId());
        if (sysRole == null) {
            Asserts.fail("未找到角色信息！");
        }
        BeanUtils.copyProperties(saveSysUserParam, sysRole);
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        return CommonResult.success(sysRole);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysRole.class),
    })
    @ApiOperation(value = "删除单个角色", response = JSONObject.class, notes = "删除单个角色")
    @PostMapping(path = "/deleteById")
    public CommonResult<SysRole> deleteById(@NotNull(message = "id不能为空！") @RequestParam(name = "id", required = false) Integer id) {
        SysRole sysUser = sysRoleMapper.selectByPrimaryKey(id);
        if (sysUser == null) {
            Asserts.fail("未找到角色信息！");
        }
        //这里做一个假删除，然后修改
        sysUser.setIsDeleted(IsDeletedEnum.Deleted.getValue());
        sysRoleMapper.updateByPrimaryKeySelective(sysUser);
        return CommonResult.success(sysUser);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysUser.class),
    })
    @ApiOperation(value = "查询全部角色", response = JSONObject.class, notes = "查询全部角色")
    @PostMapping(path = "/list")
    public CommonResult<List<SysRole>> list() {
        return CommonResult.success(sysRoleMapper.selectByExample(new SysRoleExample()));
    }

}
