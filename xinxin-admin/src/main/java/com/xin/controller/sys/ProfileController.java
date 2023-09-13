package com.xin.controller.sys;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONObject;
import com.xin.annotation.LogOperation;
import com.xin.api.CommonResult;
import com.xin.dto.sys.UpdateProfileDTO;
import com.xin.dto.sys.UpdateProfilePwdDTO;
import com.xin.entity.sys.SysUser;
import com.xin.mapper.sys.SysUserMapper;
import com.xin.utils.Asserts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Author: ljt
 * @Date: 2023/9/13 16:26
 * @Description: ProfileController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/profile")
@Api(tags = {"个人中心"})
@Validated
@Slf4j
public class ProfileController {
    @Autowired
    private SysUserMapper sysUserMapper;


    @LogOperation("个人中心-修改个人信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = String.class),
    })
    @ApiOperation(value = "修改个人信息", response = JSONObject.class, notes = "修改个人信息")
    @RequestMapping(path = "/updateProfile", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<String> updateProfile(@Validated @NotNull(message = "修改个人信息实体类不能为空！") @RequestBody UpdateProfileDTO updateProfileDTO) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(updateProfileDTO.getId());
        if (sysUser == null) {
            Asserts.fail("未找到用户信息！");
        }
        BeanUtils.copyProperties(updateProfileDTO, sysUser);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return CommonResult.success("操作成功！");
    }

    @LogOperation("个人中心-修改密码")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = String.class),
    })
    @ApiOperation(value = "修改密码", response = JSONObject.class, notes = "修改密码")
    @RequestMapping(path = "/updateProfile", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<String> updateProfile(@Validated @NotNull(message = "修改密码实体类不能为空！") @RequestBody UpdateProfilePwdDTO updateProfilePwdDTO) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(updateProfilePwdDTO.getId());
        if (sysUser == null) {
            Asserts.fail("未找到用户信息！");
        }
        String oldPwdMd5 = MD5.create().digestHex(updateProfilePwdDTO.getOldPwd());
        if (!sysUser.getPwd().equals(oldPwdMd5)) {
            Asserts.fail("旧密码错误！");
        }
        String newPwdMd5 = MD5.create().digestHex(updateProfilePwdDTO.getNewPwd());
        if (oldPwdMd5.equals(newPwdMd5)) {
            Asserts.fail("新旧密码不能重复！");
        }
        sysUser.setPwd(newPwdMd5);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return CommonResult.success("操作成功！");
    }
}
