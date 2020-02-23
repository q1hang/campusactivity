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

    @GetMapping("/get")
    public String display(@RequestParam String str){

        return str+"_get()";
    }

    @PostMapping("post")
    public String post(@RequestParam String str){
        String result=str+"_post()";
        return result;
    }



    @GetMapping("getAllUser")
    public List<SysUser> getAllUser(){
        return sysUserMapper.selectList(new QueryWrapper<>());
    }

}
