package com.campusactivity.core.User.dto;

import com.campusactivity.core.User.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author q1hang
 * @Description 用户基本信息DTO
 * @create: 2019-12-13 14:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
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
     * 学号
     */
    private String studentId;

    /**
     * 年级号
     */
    private String gradeId;

    /**
     * 备注
     */
    private String remarks;
    /**
     * 联系方式
     */
    private String telphone;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * qq号
     */
    private String qq;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生日期
     */
    private Date birth;


    public UserInfoDTO(SysUser sysUser){
        this.id=sysUser.getId();
        this.username=sysUser.getUsername();
        this.studentId=sysUser.getStudentId();
        this.gradeId=sysUser.getGradeId();
        this.remarks=sysUser.getRemarks();
        this.telphone=sysUser.getTelphone();
        this.age=sysUser.getAge();
        this.sex=sysUser.getSex();
        this.qq=sysUser.getQq();
        this.wechat=sysUser.getWechat();
        this.email=sysUser.getEmail();
        this.birth=sysUser.getBirth();
    }
}
