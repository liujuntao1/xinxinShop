package com.xin.param.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ljt
 * @Date: 2023/9/7 09:14
 * @Description: SaveSysMenuParam
 * @Version 1.0.0
 */
@Data
@ApiModel(description = "菜单实体类")
public class SaveSysMenuParam {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单编码")
    private String code;

    @ApiModelProperty(value = "菜单地址")
    private String url;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "上级菜单ID")
    private Integer parentId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "菜单状态（1：启用，0：禁用）")
    private Integer status;
}
