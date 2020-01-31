package com.campusactivity.core.community.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务过程记录表
 * </p>
 *
 * @author qihang
 * @since 2020-01-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProcessStatus extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 流程标识
     */
    private String processBusiness;

    /**
     * 审批人
     */
    private Integer approver;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审批结果,0：未通过,1：审批中,2:审批通过
     */
    private String approveResult;

    /**
     * 审批时间
     */
    private Date approveTime;


}
