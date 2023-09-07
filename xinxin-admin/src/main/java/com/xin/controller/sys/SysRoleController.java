package com.xin.controller.sys;

import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xin.api.CommonResult;
import com.xin.api.PageResult;
import com.xin.entity.sys.*;
import com.xin.enums.IsDeletedEnum;
import com.xin.mapper.sys.SysMenuRoleMapper;
import com.xin.mapper.sys.SysRoleMapper;
import com.xin.param.sys.SaveSysMenuRoleParam;
import com.xin.param.sys.SaveSysUserParam;
import com.xin.utils.Asserts;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;

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

    @Transactional(rollbackFor = Exception.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = String.class),
    })
    @ApiOperation(value = "角色关联菜单", response = JSONObject.class, notes = "角色关联菜单")
    @PostMapping(path = "/insertRoleMenu")
    public CommonResult<String> insertRoleMenu(@RequestBody SaveSysMenuRoleParam saveSysMenuRoleParam) {
        //先删除角色的所有权限，然后在新增
        deleteRoleMenuByRoleId(saveSysMenuRoleParam.getRoleId());
        for (Integer menuId : saveSysMenuRoleParam.getMenuId()) {
            SysMenuRole sysMenuRole = new SysMenuRole();
            sysMenuRole.setRoleId(saveSysMenuRoleParam.getRoleId());
            sysMenuRole.setMenuId(menuId);
            sysMenuRoleMapper.insertSelective(sysMenuRole);
        }
        return CommonResult.success("添加成功");
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
        //删除角色关联的菜单
        deleteRoleMenuByRoleId(id);
        //这里做一个假删除，然后修改
        sysUser.setIsDeleted(IsDeletedEnum.Deleted.getValue());
        sysRoleMapper.updateByPrimaryKeySelective(sysUser);
        return CommonResult.success(sysUser);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysRole.class),
    })
    @ApiOperation(value = "查询角色关联的菜单", response = JSONObject.class, notes = "查询角色关联的菜单")
    @RequestMapping(path = "/listRoleMenuByRoleId", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<PageResult<SysMenuRole>> listRoleMenuByRoleId(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                      @ApiParam(name = "roleId", value = "角色id") @NotNull(message = "角色id不能为空！") @RequestParam(name = "roleId", required = false) Integer roleId) {
        PageHelper.startPage(pageNum, pageSize);
        SysMenuRoleExample sysMenuRoleExample = new SysMenuRoleExample();
        sysMenuRoleExample.setOrderByClause("updated_time desc");
        SysMenuRoleExample.Criteria criteria = sysMenuRoleExample.createCriteria();
        criteria.andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                .andRoleIdEqualTo(roleId);
        return CommonResult.success(PageResult.convertPageResult(sysMenuRoleMapper.selectByExample(sysMenuRoleExample)));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = String.class),
    })
    @ApiOperation(value = "删除角色所有权限", response = JSONObject.class, notes = "删除角色所有权限")
    @PostMapping(path = "/deleteRoleMenuByRoleId")
    public CommonResult<String> deleteRoleMenuByRoleId(@NotNull(message = "id不能为空！") @RequestParam(name = "id", required = false) Integer id) {
        SysRole sysUser = sysRoleMapper.selectByPrimaryKey(id);
        if (sysUser == null) {
            Asserts.fail("未找到角色信息！");
        }
        SysMenuRoleExample sysMenuRoleExample = new SysMenuRoleExample();
        sysMenuRoleExample.createCriteria()
                .andRoleIdEqualTo(id);
        SysMenuRole sysMenuRole = new SysMenuRole();
        sysMenuRole.setIsDeleted(IsDeletedEnum.Deleted.getValue());
        sysMenuRoleMapper.updateByExampleSelective(sysMenuRole, sysMenuRoleExample);
        return CommonResult.success("操作成功！");
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
