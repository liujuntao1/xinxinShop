package com.xin.dto.sys;

import com.xin.entity.sys.SysMenu;
import com.xin.entity.sys.SysRole;
import lombok.Data;

import java.util.Date;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: ljt
 * @Date: 2023/9/11 10:15
 * @Description: Login
 * @Version 1.0.0
 */
@Data
@ApiModel(description = "用户登录信息")
public class LoginUserInfoDTO {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "用户状态（1：启用0:禁用）")
    private Integer status;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "删除状态（1：正常0：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;

    @ApiModelProperty(value = "个人描述")
    private String description;

    @ApiModelProperty(value = "住址")
    private String address;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "性别（1：男2：女3：未知）")
    private Integer sex;

    @ApiModelProperty(value = "角色集合")
    private List<SysRole> roles;

    @ApiModelProperty(value = "菜单集合")
    private List<SysMenu> menus;
}
