package com.campusactivity.core.reimbursement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDTO {
    private String business;

    private String opinion;

    private String remark;
}
