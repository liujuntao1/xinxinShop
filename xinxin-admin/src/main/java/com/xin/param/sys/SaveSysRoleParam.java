package com.xin.param.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @Author: ljt
 * @Date: 2023/9/6 17:16
 * @Description: InsertSysUserParam
 * @Version 1.0.0
 */
@Data
@ApiModel("添加或修改角色对象")
public class SaveSysRoleParam {
    /**
     * 主键
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 角色名称
     */
    @NotNull(message = "角色名称不能为空！")
    @ApiModelProperty("角色名称")
    private String name;

    /**
     * 角色编码
     */
    @NotNull(message = "角色编码不能为空！")
    @ApiModelProperty("角色编码")
    private String code;

    /**
     * 角色描述
     */
    @NotNull(message = "角色描述不能为空！")
    @ApiModelProperty("角色描述")
    private String description;

    /**
     * 状态
     */
    @NotNull(message = "状态为空！")
    @ApiModelProperty("状态")
    private Integer status;

}
