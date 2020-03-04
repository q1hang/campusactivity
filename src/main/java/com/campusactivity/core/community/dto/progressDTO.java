package com.campusactivity.core.community.dto;

import com.campusactivity.core.reimbursement.dto.ReimbursementDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author q1hang
 * @Description 审批进度DTO
 * @create: 2020-03-04 16:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class progressDTO {

    List<ReimbursementDTO> reimbursementList;

    List<CMDTO> rucruitList;
}
