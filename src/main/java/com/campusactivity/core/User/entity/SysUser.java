package com.campusactivity.core.User.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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


    public SysUser(String username,String password,String studentId){
        this.username=username;
        this.password=password;
        this.studentId=studentId;
    }
}
