package com.campusactivity.core.community.dto;

import com.campusactivity.common.base.PageDTO;
import com.campusactivity.core.community.entity.Communityinformation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author q1hang
 * @Description 社团信息DTO
 * @create: 2020-01-14 21:13
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CIDTO extends PageDTO {

    private Integer id;

    private String communityName;

    /**
     * 社团类型:1.科技型2.文艺型3.体育型4.公益型5.综合型
     */
    private String type;


    /**
     * 发起人
     */
    private String originator;

    /**
     * 宗旨
     */
    private String purpose;

    /**
     * 联系人电话
     */
    private String contactsNumber;

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
    private Integer createUser;

    /**
     * 更新者
     */
    private Integer updateUser;

    public CIDTO(Communityinformation cf){
        this.id=cf.getId();
        this.communityName=cf.getCommunityName();
        this.contactsNumber=cf.getContactsNumber();
        this.purpose=cf.getPurpose();
        this.type=cf.getType();
        this.createDate=cf.getCreateDate();
        this.updateDate=cf.getUpdateDate();
        this.createUser=cf.getCreateUser();
        this.updateUser=cf.getUpdateUser();
    }

}
