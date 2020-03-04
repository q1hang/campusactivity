package com.campusactivity.core.User.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusactivity.common.util.ContextUtil;
import com.campusactivity.core.User.dto.UserInfoDTO;
import com.campusactivity.core.User.entity.SysUser;
import com.campusactivity.core.User.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/user")
public class SysUserController {


    @Autowired
    private SysUserServiceImpl sysUserService;

    /**
     * 批量添加用户
     */
    @RequestMapping("/add")
    public String addAll(){
        String [] names={};
//        int studentId=4;
//        for(String name:names ){
//            SysUser temp=new SysUser(name,"123456",String.valueOf(studentId++));
//            sysUserService.save(temp);
//        }
        return "success";
    }

    @PostMapping("update")
    public UserInfoDTO update(@RequestBody UserInfoDTO dto){
        SysUser sysUser = new SysUser(dto);
        sysUserService.update(sysUser,new QueryWrapper<SysUser>().eq("id",ContextUtil.getCurrentUserId()));
        return new UserInfoDTO(sysUserService.getById(ContextUtil.getCurrentUserId()));
    }

    @GetMapping("select")
    public UserInfoDTO select(){
        return new UserInfoDTO(sysUserService.getById(ContextUtil.getCurrentUserId()));
    }
}

