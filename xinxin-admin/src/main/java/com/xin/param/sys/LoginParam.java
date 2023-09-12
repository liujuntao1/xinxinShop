package com.xin.param.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: ljt
 * @Date: 2023/9/8 17:50
 * @Description: LoginParam
 * @Version 1.0.0
 */
@Data
@ApiModel("登录实体类")
public class LoginParam {
    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不能为空！")
    private String userName;

    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空！")
    private String passWord;

    @ApiModelProperty("验证码")
    @NotNull(message = "验证码不能为空！")
    private String verificationCode;
}
