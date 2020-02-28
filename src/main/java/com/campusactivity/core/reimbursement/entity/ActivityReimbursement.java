package com.campusactivity.core.reimbursement.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.campusactivity.core.reimbursement.dto.ReimbursementDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动报销表
 * </p>
 *
 * @author qihang
 * @since 2020-02-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ActivityReimbursement extends Model {

    private static final long serialVersionUID = 1L;


    @TableId
    private Integer id;

    /**
     * 报销单号
     */
    private String reimbursementCode;

    /**
     * 活动Id
     */
    private Integer activityId;

    /**
     * 社团Id
     */
    private Integer communityId;

    /**
     * 交易方式:0、微信1、支付宝2、现金
     */
    private String transactionMode;

    /**
     * 附件id
     */
    private Integer attachId;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 报销状态(草稿、审批中、结束)
     */
    private String status;

    /**
     * 删除状态：0未删除，1删除
     */
    private String deleteStatus;

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


    public ActivityReimbursement(ReimbursementDTO dto){
        this.reimbursementCode=dto.getReimbursementCode();
        this.activityId=dto.getActivityId();
        this.communityId=dto.getCommunityId();
        this.transactionMode=dto.getTransactionMode();
        this.attachId=dto.getAttachId();
        this.totalAmount=dto.getTotalAmount();
        this.status=dto.getStatus();
    }

}
