package com.xin.param.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/7 10:40
 * @Description: SaveSysMenuRoleParam
 * @Version 1.0.0
 */
@Data
@ApiModel(description = "角色菜单关联实体类")
public class SaveSysMenuRoleParam {
    @NotNull(message = "角色ID不能为空！")
    @ApiModelProperty(value = "角色ID", example = "2001")
    private Integer roleId;
    @NotNull(message = "菜单ID不能为空！")
    @ApiModelProperty(value = "菜单ID", example = "1001")
    private List<Integer> menuIds;
}
