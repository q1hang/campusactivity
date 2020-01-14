package com.campusactivity.core.User.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusactivity.common.util.RedisUtil;
import com.campusactivity.core.User.entity.SysUser;
import com.campusactivity.core.User.mapper.SysUserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author q1hang
 * @Description 测试类
 * @create: 2019-12-12 16:11
 **/
@RestController
@RequestMapping("/test")
public class test {

    @Resource
    private SysUserMapper sysUserMapper;

    @GetMapping("/test")
    public String display(){
        String hello = RedisUtil.get("hello");
        if(hello==null||hello.length()==0){
           return "redis没有这个值";
        }
        return hello;
    }

    @GetMapping("getAllUser")
    public List<SysUser> getAllUser(){
        List<SysUser> allUser = new ArrayList<>();
        return sysUserMapper.selectList(new QueryWrapper<>());
    }

}
