package com.xin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.xin.dto.sys.LoginUserInfoDTO;
import com.xin.entity.sys.*;
import com.xin.enums.IsDeletedEnum;
import com.xin.mapper.sys.*;
import com.xin.service.sys.LoginService;
import com.xin.utils.Asserts;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: ljt
 * @Date: 2023/9/11 10:19
 * @Description: LoginServiceImpl
 * @Version 1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    private SysUserMapper sysUserMapper;
    private SysUserRoleMapper sysUserRoleMapper;
    private SysRoleMapper sysRoleMapper;
    private SysMenuMapper sysMenuMapper;
    private SysMenuRoleMapper sysMenuRoleMapper;

    @Autowired
    public LoginServiceImpl(SysUserMapper sysUserMapper, SysUserRoleMapper sysUserRoleMapper, SysRoleMapper sysRoleMapper, SysMenuMapper sysMenuMapper, SysMenuRoleMapper sysMenuRoleMapper) {
        this.sysUserMapper = sysUserMapper;
        this.sysUserRoleMapper = sysUserRoleMapper;
        this.sysRoleMapper = sysRoleMapper;
        this.sysMenuMapper = sysMenuMapper;
        this.sysMenuRoleMapper = sysMenuRoleMapper;
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    @Override
    public LoginUserInfoDTO getUserInfo() {
        //TODO 将用户信息缓存至redis中，key为token，value为LoginUserInfoDTO对象
        LoginUserInfoDTO loginUserInfoDTO = new LoginUserInfoDTO();
        int loginUserId = StpUtil.getLoginIdAsInt();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(loginUserId);
        if (sysUser == null) {
            Asserts.fail("用户未找到！");
        }
        BeanUtils.copyProperties(sysUser, loginUserInfoDTO);
        //查询角色集合
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                .andUserIdEqualTo(loginUserId);
        List<Integer> roleIds = sysUserRoleMapper.selectByExample(sysUserRoleExample).stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(roleIds)) {
            SysRoleExample sysRoleExample = new SysRoleExample();
            sysRoleExample.createCriteria()
                    .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                    .andIdIn(roleIds);
            List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
            loginUserInfoDTO.setRoles(sysRoles);
            //查询菜单集合
            SysMenuRoleExample sysMenuRoleExample = new SysMenuRoleExample();
            sysMenuRoleExample.createCriteria()
                    .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                    .andRoleIdIn(roleIds);
            List<Integer> menuIds = sysMenuRoleMapper.selectByExample(sysMenuRoleExample).stream().map(SysMenuRole::getMenuId).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(menuIds)) {
                SysMenuExample sysMenuExample = new SysMenuExample();
                sysMenuExample.createCriteria()
                        .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                        .andIdIn(menuIds);
                List<SysMenu> sysMenus = sysMenuMapper.selectByExample(sysMenuExample);
                loginUserInfoDTO.setMenus(sysMenus);
            }
        }
        return loginUserInfoDTO;
    }
}
