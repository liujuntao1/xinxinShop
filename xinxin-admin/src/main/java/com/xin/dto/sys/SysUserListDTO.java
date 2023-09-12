package com.xin.dto.sys;

import com.xin.entity.sys.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/12 16:33
 * @Description: SysUserListDTO
 * @Version 1.0.0
 */
@Data
public class SysUserListDTO extends SysUser {
    @ApiModelProperty("用户拥有的角色集合")
    private List<Integer> roleIds;
}
