package com.campusactivity.core.activity.dto;

import com.campusactivity.common.base.PageDTO;
import com.campusactivity.core.activity.entity.ActivityInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtInfoDTO extends PageDTO {

    /**
     *活动id
     */
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    /**
     * 报名开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registrationTime;

    /**
     * 报名截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;

    /**
     * 创建者
     */
    private Integer createUser;

    /**
     * 更新者
     */
    private Integer updateUser;

    public AtInfoDTO(ActivityInfo ai){
        this.id=ai.getId();
        this.communityId=ai.getCommunityId();
        this.activityName=ai.getActivityName();
        this.type=ai.getType();
        this.activityIntroduce=ai.getActivityIntroduce();
        this.address=ai.getAddress();
        this.endTime=ai.getEndTime();
        this.startTime=ai.getStartTime();
        this.manager=ai.getManager();
        this.registrationDeadline=ai.getRegistrationDeadline();
        this.registrationTime=ai.getRegistrationTime();
        this.createDate=ai.getCreateDate();
        this.updateDate=ai.getUpdateDate();
        this.createUser=ai.getCreateUser();
        this.updateUser=ai.getUpdateUser();
    }

}
