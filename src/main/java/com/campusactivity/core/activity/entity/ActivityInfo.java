package com.campusactivity.core.activity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.campusactivity.core.activity.dto.AtInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动信息表
 * </p>
 *
 * @author qihang
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("activityInfo")
@NoArgsConstructor
@AllArgsConstructor
public class ActivityInfo extends Model {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    /**
     * 社团Id
     */
    private Integer communityId;

    /**
     * 活动名
     */
    private String activityName;

    /**
     * 活动类型:1.体育类2.文化类3.创业类4.学术类5.科技类
     */
    private String type;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 报名开始时间
     */
    private Date registrationTime;

    /**
     * 报名截止时间
     */
    private Date registrationDeadline;

    /**
     * 负责人
     */
    private Integer manager;

    /**
     * 活动地点:
     */
    private String address;

    /**
     * 活动介绍
     */
    private String activityIntroduce;

    /**
     * 创建时间
     */
    @TableField("CreateDate")
    private Date CreateDate;

    /**
     * 更新时间
     */
    @TableField("UpdateDate")
    private Date UpdateDate;

    /**
     * 创建者
     */
    @TableField("CreateUser")
    private Integer CreateUser;

    /**
     * 更新者
     */
    @TableField("UpdateUser")
    private Integer UpdateUser;

    public ActivityInfo(AtInfoDTO dto){
        this.id=dto.getId();
        this.communityId=dto.getCommunityId();
        this.activityName=dto.getActivityName();
        this.type=dto.getType();
        this.activityIntroduce=dto.getActivityIntroduce();
        this.address=dto.getAddress();
        this.endTime=dto.getEndTime();
        this.startTime=dto.getStartTime();
        this.manager=dto.getManager();
        this.registrationDeadline=dto.getRegistrationDeadline();
        this.registrationTime=dto.getRegistrationTime();
        this.CreateDate=dto.getCreateDate();
        this.UpdateDate=dto.getUpdateDate();
        this.CreateUser=dto.getCreateUser();
        this.UpdateUser=dto.getUpdateUser();
    }

}
