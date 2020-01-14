package com.campusactivity.core.User.controller;


import com.campusactivity.core.User.entity.SysUser;
import com.campusactivity.core.User.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/User/sys-user")
public class SysUserController {


    @Autowired
    private SysUserServiceImpl sysUserService;

    /**
     * 批量添加用户
     */
    @RequestMapping("/add")
    public String addAll(){
        String [] names={};
        int studentId=4;
        for(String name:names ){
            SysUser temp=new SysUser(name,"123456",String.valueOf(studentId++));
            sysUserService.save(temp);
        }
        return "success";
    }
}

