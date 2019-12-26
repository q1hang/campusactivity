package com.campusactivity.core.login.dto;

import lombok.Data;

/**
 * @Author q1hang
 * @Description 登录DTO
 * @create: 2019-12-13 13:54
 **/
@Data
public class AppLoginDTO {

    private String username;

    private String password;

    //组织编码
    private String companyCode;
}
