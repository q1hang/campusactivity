package com.campusactivity.core.User.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusactivity.common.exception.SSOLoginException;
import com.campusactivity.core.User.dto.UserInfoDTO;
import com.campusactivity.core.User.entity.SysUser;
import com.campusactivity.core.User.mapper.SysUserMapper;
import com.campusactivity.core.User.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campusactivity.core.login.dto.AppLoginDTO;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qihang
 * @since 2019-12-13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public UserInfoDTO appLogin(AppLoginDTO appLoginDTO) throws Exception {
        String userName=appLoginDTO.getUsername();
        String password=appLoginDTO.getPassword();
        QueryWrapper<SysUser> wrapper=new QueryWrapper();
        wrapper.eq("username",userName)
                .eq("password",password);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        if(sysUser==null){
            throw  new  SSOLoginException("没有此账号");
        }
        return new UserInfoDTO(sysUser);
    }
}
