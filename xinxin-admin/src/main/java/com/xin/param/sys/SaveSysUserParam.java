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
@ApiModel("添加或修改用户对象")
public class SaveSysUserParam {
    /**
     * 主键
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 昵称
     */
    @NotNull(message = "昵称不能为空！")
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空！")
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空！")
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空！")
    @Email(message = "邮箱格式错误！")
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;

}
