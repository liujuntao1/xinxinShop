package com.xin.controller.sys;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONObject;
import com.xin.api.CommonResult;
import com.xin.dto.sys.LoginUserInfoDTO;
import com.xin.entity.sys.SysUser;
import com.xin.entity.sys.SysUserExample;
import com.xin.enums.IsDeletedEnum;
import com.xin.mapper.sys.SysUserMapper;
import com.xin.param.sys.LoginParam;
import com.xin.service.sys.LoginService;
import com.xin.utils.Asserts;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/7 13:58
 * @Description: LoginController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/sysLogin")
@Api(tags = {"登录管理"})
@Validated
@Slf4j
public class LoginController {


    private SysUserMapper sysUserMapper;
    private LoginService loginService;

    @Autowired
    public void setSysUserMapper(SysUserMapper sysUserMapper, LoginService loginService) {
        this.sysUserMapper = sysUserMapper;
        this.loginService = loginService;
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = String.class),
    })
    @ApiOperation(value = "登录接口", response = JSONObject.class, notes = "登录接口")
    @RequestMapping(path = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<String> login(@Validated @NotNull(message = "登录实体不能为空！") @RequestBody LoginParam loginParam) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria()
                .andIsDeletedEqualTo(IsDeletedEnum.not_Deleted.getValue())
                .andUserNameEqualTo(loginParam.getUserName());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtil.isEmpty(sysUsers)) {
            Asserts.fail("未找到用户信息！");
        }
        SysUser sysUser = sysUsers.get(0);
        if (sysUser.getStatus().equals(IsDeletedEnum.Deleted.getValue())) {
            Asserts.fail("用户已被禁用！");
        }
        if (!sysUser.getPwd().equals(MD5.create().digestHex(loginParam.getPassWord()))) {
            Asserts.fail("密码错误！");
        }
        //会话登录：参数填写要登录的账号id，建议的数据类型：long | int | String， 不可以传入复杂类型，如：User、Admin 等等
        StpUtil.login(sysUser.getId());
        //Sa-Token 为这个账号创建了一个Token凭证，且通过 Cookie 上下文返回给了前端
        return CommonResult.success("登录成功！");
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Boolean.class),
    })
    @ApiOperation(value = "判断用户是否登录", response = JSONObject.class, notes = "判断用户是否登录")
    @RequestMapping(path = "/isLogin", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<Boolean> isLogin(@ApiParam(name = "userId", value = "用户ID") @NotNull(message = "用户ID不能为空！") @RequestParam(name = "userId", required = false) Integer userId) {
        return CommonResult.success(StpUtil.isLogin(userId));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = LoginUserInfoDTO.class),
    })
    @ApiOperation(value = "获取登录用户信息", response = JSONObject.class, notes = "获取登录用户信息")
    @RequestMapping(path = "/getUserInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<LoginUserInfoDTO> getUserInfo() {
        return CommonResult.success(loginService.getUserInfo());
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Boolean.class),
    })
    @ApiOperation(value = "退出登录", response = JSONObject.class, notes = "退出登录")
    @RequestMapping(path = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<Boolean> logout() {
        StpUtil.logout();
        return CommonResult.success(true);
    }
}
