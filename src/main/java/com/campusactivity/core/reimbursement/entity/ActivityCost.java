package com.campusactivity.core.reimbursement.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.campusactivity.core.reimbursement.dto.CostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动费用表
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
public class ActivityCost extends Model {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    /**
     * 报销单Id
     */
    private Integer reimbursementId;

    /**
     * 费用类型:0、餐费1、交通费2、办公费3、招待费4、通讯费5、采购6、技术服务费7、租金8、分包9、其他10
     */
    private String costType;

    /**
     * 费用金额
     */
    private BigDecimal costPrice;

    /**
     * 费用事由
     */
    private String remark;

    /**
     * 附件id
     */
    private Integer attachId;

    public ActivityCost(CostDTO dto){
        reimbursementId=dto.getReimbursementId();
        costType=dto.getCostType();
        costPrice=dto.getCostPrice();
        reimbursementId=dto.getReimbursementId();
        attachId=dto.getAttachId();
        remark=dto.getRemark();
    }
}
