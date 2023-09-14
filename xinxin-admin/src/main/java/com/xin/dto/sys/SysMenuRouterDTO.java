package com.xin.dto.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/14 13:37
 * @Description: SysMenuRouterDTO
 * @Version 1.0.0
 */
@Data
@ApiModel("菜单路由实体类")
public class SysMenuRouterDTO {
    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    private String path;

    /**
     * 菜单编码
     */
    @ApiModelProperty(value = "菜单编码")
    private String name;

    /**
     * 前端组件
     */
    @ApiModelProperty(value = "前端组件")
    private String component;

    /**
     * 重定向地址
     */
    @ApiModelProperty(value = "重定向地址")
    private String redirect;

    /**
     * 菜单标题
     */
    @ApiModelProperty(value = "菜单标题")
    private Meta meta;
    /**
     * 是否一直展示
     */
    @ApiModelProperty(value = "是否一直展示")
    private Boolean AlwaysShow;


    /**
     * 子级菜单
     */
    @ApiModelProperty(value = "子级菜单")
    private List<SysMenuRouterDTO> children;

    @Data
    @AllArgsConstructor
    public static class Meta {
        /**
         * 菜单标题
         */
        @ApiModelProperty(value = "菜单标题")
        private String title;
        /**
         * 菜单图标
         */
        @ApiModelProperty(value = "菜单图标")
        private String icon;
    }
}
