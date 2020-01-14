package com.campusactivity.core.User.service;

import com.campusactivity.core.User.dto.UserInfoDTO;
import com.campusactivity.core.User.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campusactivity.core.login.dto.AppLoginDTO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
public interface ISysUserService extends IService<SysUser> {

    public UserInfoDTO appLogin(AppLoginDTO appLoginDTO) throws Exception;

}
