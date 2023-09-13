package com.xin.dto.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: ljt
 * @Date: 2023/9/13 09:50
 * @Description: UpdateUserPwdDTO
 * @Version 1.0.0
 */
@Data
@ApiModel("修改用户密码实体类")
public class UpdateUserPwdDTO {
    @ApiModelProperty("用户id")
    @NotNull(message = "用户id不能为空！")
    private Integer userId;
    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空！")
    private String pwd;
}
