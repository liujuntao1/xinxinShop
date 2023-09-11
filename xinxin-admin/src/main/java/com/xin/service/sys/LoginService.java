package com.xin.service.sys;

import com.xin.dto.sys.LoginUserInfoDTO;

/**
 * @Author: ljt
 * @Date: 2023/9/11 10:18
 * @Description: LoginService
 * @Version 1.0.0
 */
public interface LoginService {
    /**
     * 获取登录用户信息
     *
     * @return
     */
    LoginUserInfoDTO getUserInfo();
}
