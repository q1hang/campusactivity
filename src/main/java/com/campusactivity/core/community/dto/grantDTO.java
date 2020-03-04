package com.campusactivity.core.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author q1hang
 * @Description 权限授予DTO
 * @create: 2020-03-04 10:58
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class grantDTO {

    private Integer userId;

    private Integer communityId;

    private String grantLevel;

    private String remark;
}
