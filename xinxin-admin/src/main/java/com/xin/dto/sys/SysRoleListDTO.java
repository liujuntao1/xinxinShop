package com.xin.dto.sys;

import com.xin.entity.sys.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/12 17:08
 * @Description: SysRoleListDTO
 * @Version 1.0.0
 */
@Data
public class SysRoleListDTO extends SysRole {
    @ApiModelProperty("角色拥有的菜单集合")
    private List<Integer> menuIds;
}
