package com.xin.entity.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志表
 * sys_log
 */
@Data
public class SysLog implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 日志类型（1：操作日志2：登录日志）
     */
    private Integer logType;

    /**
     * 日志内容
     */
    private String logContent;

    /**
     * ip
     */
    private String operationIp;

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
