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


    public UserInfoDTO(SysUser sysUser){
        this.id=sysUser.getId();
        this.username=sysUser.getUsername();
        this.studentId=sysUser.getStudentId();
        this.gradeId=sysUser.getGradeId();
        this.remarks=sysUser.getRemarks();
    }
}
