package com.campusactivity.core.User.dto;

import com.campusactivity.core.User.entity.SysUser;
import lombok.Data;

/**
 * @Author q1hang
 * @Description 用户基本信息DTO
 * @create: 2019-12-13 14:27
 **/
@Data
public class UserInfoDTO {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public UserInfoDTO(SysUser sysUser){
        this.id=sysUser.getId();
        this.username=sysUser.getUsername();
        this.password=sysUser.getPassword();
    }
}
