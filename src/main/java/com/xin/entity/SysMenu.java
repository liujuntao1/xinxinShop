package com.xin.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 菜单表
 * sys_menu
 */
@Data
public class SysMenu implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 上级菜单
     */
    private Integer parentId;

    /**
     * 菜单地址
     */
    private String url;

    /**
     * 菜单类型（0：一级菜单1：二级菜单2：按钮）
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单状态（1：启用0:禁用）
     */
    private Integer status;

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