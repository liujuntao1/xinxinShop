package com.xin.dto.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: ljt
 * @Date: 2023/9/13 16:28
 * @Description: UpdateProfileDTO
 * @Version 1.0.0
 */
@Data
@ApiModel("修改个人信息实体类")
public class UpdateProfileDTO {
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "个人描述")
    private String description;

    @ApiModelProperty(value = "住址")
    private String address;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "性别（1：男2：女3：未知）")
    private Integer sex;
}
