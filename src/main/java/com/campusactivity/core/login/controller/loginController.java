package com.campusactivity.core.login.controller;

import com.campusactivity.common.exception.SSOLoginException;
import com.campusactivity.common.util.JsonMapper;
import com.campusactivity.common.util.JwtUtil;
import com.campusactivity.common.util.RedisUtil;
import com.campusactivity.core.User.dto.UserInfoDTO;
import com.campusactivity.core.User.entity.SysUser;
import com.campusactivity.core.User.service.ISysUserService;
import com.campusactivity.core.User.service.impl.SysUserServiceImpl;
import com.campusactivity.core.login.dto.AppLoginDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author q1hang
 * @Description 登录控制器
 * @create: 2019-12-13 13:51
 **/

@RestController
@RequestMapping("/login")
public class loginController {

    @Autowired
    private SysUserServiceImpl sysUserService;


    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody AppLoginDTO appLoginDTO)throws Exception{

        //验证登录信息
        UserInfoDTO userInfoDTO = sysUserService.appLogin(appLoginDTO);

        //生产token
        String token = getLoginToken(userInfoDTO);
        //保存信息到缓存
        Map<String, Object> result = new HashMap<>(2);
        result.put("userInfo", userInfoDTO);
        result.put("token", token);
        String key = RedisUtil.KEY_PREFIX_EM + "_token_" + userInfoDTO.getId();
        RedisUtil.hPut(key, "token", token);
        RedisUtil.hPut(key, "userInfo", JsonMapper.obj2String(userInfoDTO));
        RedisUtil.expire(key, 2, TimeUnit.HOURS);
        //返回token
        return result;
    }

    /**
     * 使用JWT封装生成登录令牌
     * @param sysUser
     * @return
     * @throws JsonProcessingException
     */
    private String getLoginToken(UserInfoDTO sysUser){
        Map<String,Object> map = new HashMap<>(3);
        map.put("userId", sysUser.getId());
        map.put("userName", sysUser.getUsername());
        // JWT签发认证token，存储到redis
        String token = null;
        try {
            token = JwtUtil.createJWT(map,null);
        } catch (JsonProcessingException e) {
            throw new SSOLoginException("创建JWT令牌异常："+e.getMessage(), e);
        }

        return token;
    }
}
