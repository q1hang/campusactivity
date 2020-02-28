package com.campusactivity.core.User.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.campusactivity.core.User.dto.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author qihang
 * @since 2020-02-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends Model {

    private static final long serialVersionUID = 1L;


    @TableId
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 学号
     */
    @TableField("studentId")
    private String studentId;

    /**
     * 年级号
     */
    @TableField("gradeId")
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

    public SysUser(UserInfoDTO dto){
        this.id=dto.getId();
        this.username=dto.getUsername();
        this.studentId=dto.getStudentId();
        this.gradeId=dto.getGradeId();
        this.remarks=dto.getRemarks();
        this.telphone=dto.getTelphone();
        this.age=dto.getAge();
        this.sex=dto.getSex();
        this.qq=dto.getQq();
        this.wechat=dto.getWechat();
        this.email=dto.getEmail();
    }


}
