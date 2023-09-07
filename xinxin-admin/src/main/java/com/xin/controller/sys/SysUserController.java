package com.xin.controller.sys;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xin.annotation.LogOperation;
import com.xin.annotation.Permission;
import com.xin.api.CommonResult;
import com.xin.api.PageResult;
import com.xin.entity.sys.SysUser;
import com.xin.entity.sys.SysUserExample;
import com.xin.entity.sys.SysUserRole;
import com.xin.entity.sys.SysUserRoleExample;
import com.xin.enums.IsDeletedEnum;
import com.xin.mapper.sys.SysUserMapper;
import com.xin.mapper.sys.SysUserRoleMapper;
import com.xin.param.sys.SaveSysUserParam;
import com.xin.param.sys.SaveSysUserRoleParam;
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
 * @Description: SysUserController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/sysUser")
@Api(tags = {"用户管理"})
@Validated
@Slf4j
public class SysUserController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;


    @Permission("sysuser.pageList")
    @LogOperation("用户分页列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysUser.class),
    })
    @ApiOperation(value = "用户分页列表", response = JSONObject.class, notes = "用户分页列表")
    @RequestMapping(path = "/pageList", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<PageResult<SysUser>> pageList(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                      @RequestParam(name = "userName", required = false) String userName,
                                                      @RequestParam(name = "phone", required = false) String phone) {
        PageHelper.startPage(pageNum, pageSize);
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.setOrderByClause("updated_time desc");
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue());
        if (StringUtils.isNotBlank(userName)) {
            criteria.andUserNameLike(StringUtils.join("%", userName, "%"));
        }
        if (StringUtils.isNotBlank(phone)) {
            criteria.andPhoneLike(StringUtils.join("%", phone, "%"));
        }
        PageResult<SysUser> sysUserPageResult = PageResult.convertPageResult(sysUserMapper.selectByExample(sysUserExample));
        return CommonResult.success(sysUserPageResult);
    }

    @LogOperation("新增用户角色")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysUserRole.class),
    })
    @ApiOperation(value = "新增用户角色", response = JSONObject.class, notes = "新增用户角色")
    @PostMapping(path = "/insertUserRole")
    public CommonResult<SysUserRole> insertUserRole(@RequestBody SaveSysUserRoleParam saveSysUserParam) {
        deleteUserRoleById(saveSysUserParam.getUserId());
        SysUserRole sysUserRole = new SysUserRole();
        BeanUtils.copyProperties(saveSysUserParam, sysUserRole);
        sysUserRoleMapper.insertSelective(sysUserRole);
        return CommonResult.success(sysUserRole);
    }

    @LogOperation("新增用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysUser.class),
    })
    @ApiOperation(value = "新增用户", response = JSONObject.class, notes = "新增用户")
    @PostMapping(path = "/insert")
    public CommonResult<SysUser> insert(@RequestBody SaveSysUserParam saveSysUserParam) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(saveSysUserParam, sysUser);
        sysUser.setPwd(MD5.create().digestHex("123456"));
        sysUserMapper.insertSelective(sysUser);
        return CommonResult.success(sysUser);
    }

    @LogOperation("修改用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysUser.class),
    })
    @ApiOperation(value = "修改用户", response = JSONObject.class, notes = "修改用户")
    @PostMapping(path = "/update")
    public CommonResult<SysUser> update(@RequestBody SaveSysUserParam saveSysUserParam) {
        if (saveSysUserParam.getId() == null) {
            Asserts.fail("未找到用户信息！");
        }
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(saveSysUserParam.getId());
        if (sysUser == null) {
            Asserts.fail("未找到用户信息！");
        }
        BeanUtils.copyProperties(saveSysUserParam, sysUser);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return CommonResult.success(sysUser);
    }

    @LogOperation("删除单个用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysUser.class),
    })
    @ApiOperation(value = "删除单个用户", response = JSONObject.class, notes = "删除单个用户")
    @PostMapping(path = "/deleteById")
    public CommonResult<SysUser> deleteById(@NotNull(message = "id不能为空！") @RequestParam(name = "id", required = false) Integer id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if (sysUser == null) {
            Asserts.fail("未找到用户信息！");
        }
        deleteUserRoleById(id);
        //这里做一个假删除，然后修改
        sysUser.setIsDeleted(IsDeletedEnum.Deleted.getValue());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return CommonResult.success(sysUser);
    }

    @LogOperation("清空用户角色")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = String.class),
    })
    @ApiOperation(value = "清空用户角色", response = JSONObject.class, notes = "清空用户角色")
    @PostMapping(path = "/deleteUserRoleById")
    public CommonResult<String> deleteUserRoleById(@NotNull(message = "用户id不能为空！") @RequestParam(name = "id", required = false) Integer id) {
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria()
                .andUserIdEqualTo(id);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setIsDeleted(IsDeletedEnum.Deleted.getValue());
        sysUserRoleMapper.updateByExampleSelective(sysUserRole, sysUserRoleExample);
        return CommonResult.success("操作成功！");
    }

    @LogOperation("查询用户关联的角色")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysUserRole.class),
    })
    @ApiOperation(value = "查询用户关联的角色", response = JSONObject.class, notes = "查询用户关联的角色")
    @PostMapping(path = "/listUserRoleById")
    public CommonResult<List<SysUserRole>> listUserRoleById(
            @NotNull(message = "用户ID不能为空！") @RequestParam(name = "userId", required = false) Integer userId) {
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria()
                .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                .andUserIdEqualTo(userId);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        return CommonResult.success(sysUserRoles);
    }

    @LogOperation("查询全部用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysUser.class),
    })
    @ApiOperation(value = "查询全部用户", response = JSONObject.class, notes = "查询全部用户")
    @PostMapping(path = "/list")
    public CommonResult<List<SysUser>> list() {
        return CommonResult.success(sysUserMapper.selectByExample(new SysUserExample()));
    }

}
