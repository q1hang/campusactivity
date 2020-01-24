package com.campusactivity.common.base;

import lombok.Data;

/**
 * @author q1hang
 * @version 1.0
 * @date 2020-01-24 17:13
 */
@Data
public class PageDTO {

    private Integer pageNum = 1;

    private Integer pageSize = 20;
}
