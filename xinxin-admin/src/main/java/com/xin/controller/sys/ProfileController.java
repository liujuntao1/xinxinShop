package com.xin.controller.sys;

import cn.dev33.satoken.stp.StpUtil;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Base64;

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
    public static final String BASE64_PREFIX = "data:image/jpeg;base64,";
    @Autowired
    private SysUserMapper sysUserMapper;


    @LogOperation("个人中心-修改个人信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = String.class),
    })
    @ApiOperation(value = "修改个人信息", response = JSONObject.class, notes = "修改个人信息")
    @RequestMapping(path = "/updateProfile", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<String> updateProfile(@Validated @NotNull(message = "修改个人信息实体类不能为空！") @RequestBody UpdateProfileDTO updateProfileDTO) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(StpUtil.getLoginIdAsInt());
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
    @RequestMapping(path = "/updatePwd", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<String> updatePwd(@Validated @NotNull(message = "修改密码实体类不能为空！") @RequestBody UpdateProfilePwdDTO updateProfilePwdDTO) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(StpUtil.getLoginIdAsInt());
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

    @LogOperation("个人中心-头像上传")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = String.class),
    })
    @ApiOperation(value = "头像上传", response = JSONObject.class, notes = "头像上传")
    @PostMapping("/uploadAvatar")
    public CommonResult<String> uploadAvatar(@NotNull(message = "上传文件不能为空！") @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                //TODO 后期将头像上传至文件服务器
                SysUser sysUser = sysUserMapper.selectByPrimaryKey(StpUtil.getLoginIdAsInt());
                if (sysUser == null) {
                    Asserts.fail("未找到用户信息！");
                }
                // 将文件内容转换为Base64
                byte[] bytes = file.getBytes();
                String base64Image = BASE64_PREFIX +Base64.getEncoder().encodeToString(bytes);
                sysUser.setAvatar(base64Image);
                sysUserMapper.updateByPrimaryKeySelective(sysUser);
                return CommonResult.success(base64Image);
            } catch (IOException e) {
                e.printStackTrace();
                return CommonResult.success("error");
            }
        } else {
            return CommonResult.success("上传文件为空！");
        }
    }
}
