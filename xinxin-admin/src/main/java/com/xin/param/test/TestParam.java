package com.xin.param.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Author: ljt
 * @Date: 2023/9/6 14:33
 * @Description: TestParam
 * @Version 1.0.0
 */
@Data
@ApiModel("添加参数")
public class TestParam {
    @ApiModelProperty("姓名")
    @NotEmpty(message = "姓名不能为空！")
    private String name;
    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式错误！")
    private String email;
    @ApiModelProperty("生日")
    private Date birthday;
    @ApiModelProperty("联系地址")
    private String address;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("性别（0：男1：女2：未知）")
    @Min(value = 0,message = "请选择正确的性别！")
    @Max(value = 2,message = "请选择正确的性别！")
    private Integer sex;
}
