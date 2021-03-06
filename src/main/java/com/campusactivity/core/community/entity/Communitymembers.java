package com.campusactivity.core.community.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.campusactivity.common.util.ContextUtil;
import com.campusactivity.core.community.dto.CMDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 社团成员表
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Communitymembers extends Model {

    private static final long serialVersionUID = 1L;


    @TableId
    private Integer id;

    /**
     * 用户ID
     */
    @TableField("UserId")
    private Integer UserId;

    /**
     * 社团ID
     */
    @TableField("CommunityId")
    private Integer CommunityId;

    /**
     * 到达时间
     */
    @TableField("ArrivalTime")
    private Date ArrivalTime;

    /**
     * 状态:1.审核中2.在职3.已退出4.毕业
     */
    private String state;

    /**
     * 职位
     */
    private String position;

    private String communitymemberscol;

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

    public Communitymembers(CMDTO dto){
        this.id=dto.getId();
        this.UserId=dto.getUserId();
        this.CommunityId=dto.getCommunityId();
        this.ArrivalTime=dto.getArrivalTime();
        this.state=dto.getState();
        this.position=dto.getPosition();
        this.communitymemberscol=dto.getCommunitymemberscol();
        this.CreateDate=new Date();
        this.UpdateDate=new Date();
        this.CreateUser=ContextUtil.getCurrentUserId();
        this.UpdateUser=ContextUtil.getCurrentUserId();
    }
}
