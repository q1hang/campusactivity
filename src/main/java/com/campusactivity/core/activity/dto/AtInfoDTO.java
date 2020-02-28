package com.campusactivity.core.activity.dto;

import com.campusactivity.common.base.PageDTO;
import com.campusactivity.common.util.ExcelColumn;
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
    @ExcelColumn(value = "活动id", col = 1)
    private Integer id;

    /**
     * 社团Id
     */

    private Integer communityId;

    /**
     * 社团名
     */
    @ExcelColumn(value = "社团名", col = 2)
    private String communityName;

    /**
     * 活动名
     */
    @ExcelColumn(value = "活动名", col = 3)
    private String activityName;

    /**
     * 活动类型:1.体育类2.文化类3.创业类4.学术类5.科技类
     */
    @ExcelColumn(value = "活动类型", col = 4)
    private String type;

    /**
     * 活动开始时间
     */
    @ExcelColumn(value = "活动开始时间", col = 5)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @ExcelColumn(value = "活动结束时间", col = 6)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    /**
     * 报名开始时间
     */
    @ExcelColumn(value = "报名开始时间", col = 7)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registrationTime;

    /**
     * 报名截止时间
     */
    @ExcelColumn(value = "报名截止时间", col = 8)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registrationDeadline;

    /**
     * 负责人
     */
    @ExcelColumn(value = "负责人", col = 9)
    private Integer manager;

    /**
     * 活动地点:
     */
    @ExcelColumn(value = "活动地点", col = 10)
    private String address;

    /**
     * 活动介绍
     */
    @ExcelColumn(value = "活动介绍", col = 11)
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
