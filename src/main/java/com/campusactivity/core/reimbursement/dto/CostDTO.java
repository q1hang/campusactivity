package com.campusactivity.core.reimbursement.dto;

import com.campusactivity.core.reimbursement.entity.ActivityCost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostDTO {
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

    public CostDTO(ActivityCost entity) {
        id=entity.getId();
        reimbursementId=entity.getReimbursementId();
        costType=entity.getCostType();
        costPrice=entity.getCostPrice();
        remark=entity.getRemark();
        attachId=entity.getAttachId();
    }
}
