package com.xin.entity.sys;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统用户表
 * sys_user
 */
@Data
public class SysUser implements Serializable {
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
     * 生日
     */
    private Date birthday;

    /**
     * 性别（1：男2：女3：未知）
     */
    private Integer sex;

    /**
     * 用户状态（1：启用0:禁用）
     */
    private Integer status;

    /**
     * 个人描述
     */
    private String description;

    /**
     * 住址
     */
    private String address;

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

    private static final long serialVersionUID = 1L;
}
