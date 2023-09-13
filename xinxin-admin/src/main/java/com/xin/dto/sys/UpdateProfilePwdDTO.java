package com.xin.dto.sys;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: ljt
 * @Date: 2023/9/13 16:31
 * @Description: UpdateProfilePwdDTO
 * @Version 1.0.0
 */
@Data
@ApiModel("修改密码实体类")
public class UpdateProfilePwdDTO {

    /**
     * 旧密码
     */
    private String oldPwd;

    /**
     * 新密码
     */
    private String newPwd;
}
