package com.xin.controller.sys;

import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xin.api.CommonResult;
import com.xin.api.PageResult;
import com.xin.entity.sys.SysMenu;
import com.xin.entity.sys.SysMenuExample;
import com.xin.enums.IsDeletedEnum;
import com.xin.mapper.sys.SysMenuMapper;
import com.xin.param.sys.SaveSysMenuParam;
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
 * @Description: SysMenuController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/sysMenu")
@Api(tags = {"菜单管理"})
@Validated
@Slf4j
public class SysMenuController {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysMenu.class),
    })
    @ApiOperation(value = "菜单分页列表", response = JSONObject.class, notes = "菜单分页列表")
    @RequestMapping(path = "/pageList", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<PageResult<SysMenu>> pageList(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                      @RequestParam(name = "name", required = false) String name,
                                                      @RequestParam(name = "code", required = false) String code) {
        PageHelper.startPage(pageNum, pageSize);
        SysMenuExample sysMenuExample = new SysMenuExample();
        sysMenuExample.setOrderByClause("updated_time desc");
        SysMenuExample.Criteria criteria = sysMenuExample.createCriteria();
        criteria.andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue());
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(StringUtils.join("%", name, "%"));
        }
        if (StringUtils.isNotBlank(code)) {
            criteria.andCodeLike(StringUtils.join("%", code, "%"));
        }
        PageResult<SysMenu> sysMenuPageResult = PageResult.convertPageResult(sysMenuMapper.selectByExample(sysMenuExample));
        return CommonResult.success(sysMenuPageResult);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysMenu.class),
    })
    @ApiOperation(value = "新增菜单", response = JSONObject.class, notes = "新增菜单")
    @PostMapping(path = "/insert")
    public CommonResult<SysMenu> insert(@RequestBody SaveSysMenuParam saveSysMenuParam) {
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(saveSysMenuParam, sysMenu);
        sysMenuMapper.insertSelective(sysMenu);
        return CommonResult.success(sysMenu);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysMenu.class),
    })
    @ApiOperation(value = "修改菜单", response = JSONObject.class, notes = "修改菜单")
    @PostMapping(path = "/update")
    public CommonResult<SysMenu> update(@RequestBody SaveSysMenuParam saveSysMenuParam) {
        if (saveSysMenuParam.getId() == null) {
            Asserts.fail("未找到菜单信息！");
        }
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(saveSysMenuParam.getId());
        if (sysMenu == null) {
            Asserts.fail("未找到菜单信息！");
        }
        BeanUtils.copyProperties(saveSysMenuParam, sysMenu);
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return CommonResult.success(sysMenu);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysMenu.class),
    })
    @ApiOperation(value = "删除单个菜单", response = JSONObject.class, notes = "删除单个菜单")
    @PostMapping(path = "/deleteById")
    public CommonResult<SysMenu> deleteById(@NotNull(message = "id不能为空！") @RequestParam(name = "id", required = false) Integer id) {
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(id);
        if (sysMenu == null) {
            Asserts.fail("未找到菜单信息！");
        }
        //这里做一个假删除，然后修改
        sysMenu.setIsDeleted(IsDeletedEnum.Deleted.getValue());
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return CommonResult.success(sysMenu);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysMenu.class),
    })
    @ApiOperation(value = "查询全部菜单", response = JSONObject.class, notes = "查询全部菜单")
    @PostMapping(path = "/list")
    public CommonResult<List<SysMenu>> list() {
        return CommonResult.success(sysMenuMapper.selectByExample(new SysMenuExample()));
    }

}
