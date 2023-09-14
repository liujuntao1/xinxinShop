package com.xin.controller.sys;

import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xin.annotation.LogOperation;
import com.xin.api.CommonResult;
import com.xin.api.PageResult;
import com.xin.dto.sys.SysMenuRouterDTO;
import com.xin.dto.sys.SysMenuTreeDTO;
import com.xin.entity.sys.SysMenu;
import com.xin.entity.sys.SysMenuExample;
import com.xin.enums.IsDeletedEnum;
import com.xin.enums.MenuTypeEnum;
import com.xin.mapper.sys.SysMenuMapper;
import com.xin.param.sys.SaveSysMenuParam;
import com.xin.service.sys.LoginService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private LoginService loginService;

    @LogOperation("菜单管理-菜单分页列表")
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

    @LogOperation("菜单管理-新增菜单")
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

    @LogOperation("菜单管理-修改菜单")
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

    @LogOperation("菜单管理-删除单个菜单")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysMenu.class),
    })
    @ApiOperation(value = "删除单个菜单", response = JSONObject.class, notes = "删除单个菜单")
    @RequestMapping(path = "/deleteById", method = {RequestMethod.GET, RequestMethod.POST})
    public CommonResult<SysMenu> deleteById(@NotNull(message = "id不能为空！") @RequestParam(name = "id", required = false) Integer id) {
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(id);
        if (sysMenu == null) {
            Asserts.fail("未找到菜单信息！");
        }
        //TODO  删除先判断是否关联角色，将子级菜单全部删除
        //这里做一个假删除，然后修改
        sysMenu.setIsDeleted(IsDeletedEnum.Deleted.getValue());
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return CommonResult.success(sysMenu);
    }

    @LogOperation("菜单管理-查询全部菜单")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysMenu.class),
    })
    @ApiOperation(value = "查询全部菜单", response = JSONObject.class, notes = "查询全部菜单")
    @RequestMapping(path = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<List<SysMenu>> list() {
        SysMenuExample sysMenuExample = new SysMenuExample();
        sysMenuExample.createCriteria()
                .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue());
        return CommonResult.success(sysMenuMapper.selectByExample(sysMenuExample));
    }

    @LogOperation("菜单管理-获取分页菜单树")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysMenuTreeDTO.class),
    })
    @ApiOperation(value = "获取分页菜单树", response = JSONObject.class, notes = "获取菜单树")
    @RequestMapping(path = "/pageTreeList", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<PageResult<SysMenuTreeDTO>> pageTreeList() {
        SysMenuExample sysMenuExample = new SysMenuExample();
        sysMenuExample.setOrderByClause("created_time desc");
        sysMenuExample.createCriteria()
                .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                .andParentIdIsNull();
        PageResult<SysMenu> sysMenuPageResult = PageResult.convertPageResult(sysMenuMapper.selectByExample(sysMenuExample));
        PageResult<SysMenuTreeDTO> dtoPageResult = new PageResult<>();
        BeanUtils.copyProperties(sysMenuPageResult, dtoPageResult);
        List<SysMenuTreeDTO> dtoList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuPageResult.getData()) {
            SysMenuTreeDTO sysMenuTreeDTO = new SysMenuTreeDTO();
            BeanUtils.copyProperties(sysMenu, sysMenuTreeDTO);
            sysMenuTreeDTO.setChildren(getChildMenu(sysMenuTreeDTO.getId()));
            dtoList.add(sysMenuTreeDTO);
        }
        dtoPageResult.setData(dtoList);
        return CommonResult.success(dtoPageResult);
    }

    @LogOperation("菜单管理-获取菜单树")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysMenuTreeDTO.class),
    })
    @ApiOperation(value = "获取菜单树", response = JSONObject.class, notes = "获取菜单树")
    @RequestMapping(path = "/treeList", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<List<SysMenuTreeDTO>> treeList() {
        SysMenuExample sysMenuExample = new SysMenuExample();
        sysMenuExample.createCriteria()
                .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                .andParentIdIsNull();
        List<SysMenu> sysMenus = sysMenuMapper.selectByExample(sysMenuExample);
        List<SysMenuTreeDTO> dtoList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            SysMenuTreeDTO sysMenuTreeDTO = new SysMenuTreeDTO();
            BeanUtils.copyProperties(sysMenu, sysMenuTreeDTO);
            sysMenuTreeDTO.setChildren(getChildMenu(sysMenuTreeDTO.getId()));
            dtoList.add(sysMenuTreeDTO);
        }
        return CommonResult.success(dtoList);
    }

    @LogOperation("菜单管理-获取菜单路由列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SysMenuRouterDTO.class),
    })
    @ApiOperation(value = "获取菜单路由列表", response = JSONObject.class, notes = "获取菜单路由列表")
    @RequestMapping(path = "/menuRouterList", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<List<SysMenuRouterDTO>> menuRouterList() {
        //查询用户所有拥有的菜单列表
        List<SysMenu> menuList = loginService.getUserInfo().getMenus().stream()
                .filter(item -> !item.getType().equals(MenuTypeEnum.button.getValue())).collect(Collectors.toList());
        return CommonResult.success(getSysMenuRouterDTOChildMenu(menuList, null));
    }

    /**
     * 生成前端的路由菜单
     *
     * @param menuList
     * @param parentId
     * @return
     */
    public List<SysMenuRouterDTO> getSysMenuRouterDTOChildMenu(List<SysMenu> menuList, Integer parentId) {
        List<SysMenuRouterDTO> dtoList = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>()).stream()
                .filter(item -> item != null && item.getParentId() == parentId)
                .forEach(item -> {
                    SysMenuRouterDTO sysMenuRouterDTO = new SysMenuRouterDTO();
                    //判断是否是一级菜单
                    if (item.getType().equals(MenuTypeEnum.module.getValue())) {
                        sysMenuRouterDTO.setComponent("Layout");
                        sysMenuRouterDTO.setAlwaysShow(true);
                    } else {
                        sysMenuRouterDTO.setComponent(item.getUrl());
                        sysMenuRouterDTO.setAlwaysShow(false);
                    }
                    sysMenuRouterDTO.setName(item.getCode());//菜单编码
                    sysMenuRouterDTO.setRedirect(item.getUrl());//重定向地址
                    sysMenuRouterDTO.setPath(item.getUrl());//重定向地址
                    sysMenuRouterDTO.setMeta(new SysMenuRouterDTO.Meta(item.getName(), item.getIcon()));//标题、icon
                    sysMenuRouterDTO.setChildren(getSysMenuRouterDTOChildMenu(menuList, item.getId())); //递归查询子级
                    dtoList.add(sysMenuRouterDTO);
                });
        return dtoList;
    }

    /**
     * 递归获取子级
     *
     * @param parentId
     * @return
     */
    public List<SysMenuTreeDTO> getChildMenu(Integer parentId) {
        SysMenuExample sysMenuExample = new SysMenuExample();
        sysMenuExample.createCriteria()
                .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                .andParentIdEqualTo(parentId);
        // 获取子级菜单项
        List<SysMenu> sysMenus = sysMenuMapper.selectByExample(sysMenuExample);
        List<SysMenuTreeDTO> childMenuItems = new ArrayList<>();
        for (SysMenu menuItem : sysMenus) {
            SysMenuTreeDTO sysMenuTreeDTO = new SysMenuTreeDTO();
            BeanUtils.copyProperties(menuItem, sysMenuTreeDTO);
            // 递归获取子级菜单项
            sysMenuTreeDTO.setChildren(getChildMenu(sysMenuTreeDTO.getId()));
            childMenuItems.add(sysMenuTreeDTO);
        }
        return childMenuItems;
    }

}
