package com.xin.param.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/7 11:01
 * @Description: SaveUserRoleParam
 * @Version 1.0.0
 */
@Data
@ApiModel("用户关联角色实体类")
public class SaveSysUserRoleParam {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空！")
    @ApiModelProperty("用户ID")
    private Integer userId;

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空！")
    @ApiModelProperty("角色ID")
    private List<Integer> roleIds;
}
