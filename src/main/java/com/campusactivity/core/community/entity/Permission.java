package com.campusactivity.core.community.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 社团权限表
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Permission extends Model {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    /**
     * 用户Id
     */
    @TableField("UserId")
    private Integer UserId;

    /**
     * 社团Id
     */
    @TableField("CommunityId")
    private Integer CommunityId;

    /**
     * 权限等级:1.普通管理员2.财务管理员3.超级管理员
     */
    @TableField("PrivilegeLevel")
    private String PrivilegeLevel;

    /**
     * 备注
     */
    private String remarks;

    @TableField("CreateDate")
    private Date CreateDate;

    @TableField("UpdateDate")
    private Date UpdateDate;

    @TableField("CreateUser")
    private Integer CreateUser;

    @TableField("UpdateUser")
    private Integer UpdateUser;


}
