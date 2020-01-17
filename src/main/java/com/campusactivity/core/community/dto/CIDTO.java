package com.campusactivity.core.community.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author q1hang
 * @Description 社团信息DTO
 * @create: 2020-01-14 21:13
 **/
@Data
public class CIDTO {

    private Integer id;

    private String CommunityName;

    /**
     * 社团类型:1.科技型2.文艺型3.体育型4.公益型5.综合型
     */
    private String type;

    /**
     * 发起人
     */
    private Integer Originator;

    /**
     * 宗旨
     */
    private String Purpose;

    /**
     * 联系人电话
     */
    private String ContactsNumber;

    /**
     * 创建时间
     */
    private Date CreateDate;

    /**
     * 更新时间
     */
    private Date UpdateDate;

    /**
     * 创建者
     */
    private Integer CreateUser;

    /**
     * 更新者
     */
    private Integer UpdateUser;


}
