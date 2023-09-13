package com.xin.dto.sys;

import io.swagger.annotations.ApiModel;
import lombok.Data;

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
     * 主键
     */
    private Integer id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;
}
