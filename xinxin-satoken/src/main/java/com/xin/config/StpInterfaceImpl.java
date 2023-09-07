package com.xin.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.xin.entity.sys.*;
import com.xin.enums.IsDeletedEnum;
import com.xin.mapper.sys.SysMenuMapper;
import com.xin.mapper.sys.SysMenuRoleMapper;
import com.xin.mapper.sys.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: ljt
 * @Date: 2023/9/7 14:57
 * @Description: StpInterfaceImpl
 * @Version 1.0.0 这里获取登录用户的权限和角色集合
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 返回一个账号所拥有的权限码集合
     *
     * @param o
     * @param s
     * @return
     */
    @Override
    public List<String> getPermissionList(Object o, String s) {
        List<String> permissionList = new ArrayList<>();
        int userId = StpUtil.getLoginIdAsInt();
        //查询账号所有的角色
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue());
        List<Integer> roldIds = sysUserRoleMapper.selectByExample(sysUserRoleExample).stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(roldIds)) {
            //查询角色下所有的菜单
            SysMenuRoleExample sysMenuRoleExample = new SysMenuRoleExample();
            sysMenuRoleExample.createCriteria()
                    .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                    .andRoleIdIn(roldIds);
            List<Integer> menuIds = sysMenuRoleMapper.selectByExample(sysMenuRoleExample).stream().map(SysMenuRole::getMenuId).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(roldIds)) {
                SysMenuExample sysMenuExample = new SysMenuExample();
                sysMenuExample.createCriteria()
                        .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                        .andIdIn(menuIds);
                permissionList = sysMenuMapper.selectByExample(sysMenuExample).stream().map(SysMenu::getUrl).collect(Collectors.toList());
            }
        }
        return permissionList;
    }

    /**
     * 返回一个账号所拥有的角色集合
     *
     * @param o
     * @param s
     * @return
     */
    @Override
    public List<String> getRoleList(Object o, String s) {
        return null;
    }
}
