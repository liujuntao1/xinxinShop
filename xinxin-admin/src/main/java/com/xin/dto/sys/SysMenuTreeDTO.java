package com.xin.dto.sys;

import com.xin.entity.sys.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/13 10:42
 * @Description: SysMenuTreeDTO
 * @Version 1.0.0
 */
@Data
@ApiModel("菜单树实体类")
public class SysMenuTreeDTO extends SysMenu {
    @ApiModelProperty("子级菜单集合")
    private List<SysMenuTreeDTO> children;
}
