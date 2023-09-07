package com.xin.entity.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色表
 * sys_user_role
 */
@Data
public class SysUserRole implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;

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
