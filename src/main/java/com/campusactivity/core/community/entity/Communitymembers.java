package com.campusactivity.core.community.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    private LocalDateTime ArrivalTime;

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
    private LocalDateTime CreateDate;

    /**
     * 更新时间
     */
    @TableField("UpdateDate")
    private LocalDateTime UpdateDate;

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


}
