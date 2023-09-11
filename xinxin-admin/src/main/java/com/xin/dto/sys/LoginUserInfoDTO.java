package com.xin.dto.sys;

import com.xin.entity.sys.SysMenu;
import com.xin.entity.sys.SysRole;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/11 10:15
 * @Description: Login
 * @Version 1.0.0
 */
@Data
public class LoginUserInfoDTO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户状态（1：启用0:禁用）
     */
    private Integer status;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 删除状态（1：正常0：已删除）
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 角色集合
     */
    private List<SysRole> roles;
    /**
     * 菜单集合
     */
    private List<SysMenu> menus;
}
