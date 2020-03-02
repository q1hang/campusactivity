package com.campusactivity.core.reimbursement.dto;



import com.campusactivity.common.base.PageDTO;
import com.campusactivity.core.reimbursement.entity.ActivityReimbursement;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReimbursementDTO extends PageDTO {


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
     * 活动名
     */
    private String activityName;

    /**
     * 社团Id
     */
    private Integer communityId;

    /**
     * 社团名
     */
    private String communityName;

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
     * 报销状态(0草稿、1审批中、2通过、3作废)
     */
    private String status;

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
    private String createUser;

    /**
     * 更新者
     */
    private String updateUser;

    /**
     * 费用列表
     */
    private List<CostDTO> costList;

    /**
     * 节点名
     */
    private String nodeName;


    public ReimbursementDTO(ActivityReimbursement entity){
        this.id=entity.getId();
        this.reimbursementCode=entity.getReimbursementCode();
        this.activityId=entity.getActivityId();
        this.communityId=entity.getCommunityId();
        this.transactionMode=entity.getTransactionMode();
        this.attachId=entity.getAttachId();
        this.totalAmount=entity.getTotalAmount();
        this.status=entity.getStatus();
        this.createDate=entity.getCreateDate();
        this.updateDate=entity.getUpdateDate();
    }


}
