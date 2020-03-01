package com.campusactivity.core.community.dto;

import com.campusactivity.common.base.PageDTO;
import com.campusactivity.common.util.ExcelColumn;
import com.campusactivity.core.community.entity.Communityinformation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

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

    @ExcelColumn(value = "社团id", col = 1)
    private Integer id;

    @ExcelColumn(value = "社团名", col = 2)
    private String communityName;

    /**
     * 社团类型:1.科技型2.文艺型3.体育型4.公益型5.综合型
     */
    @ExcelColumn(value = "活动类型", col = 3)
    private String type;


    /**
     * 发起人
     */
    @ExcelColumn(value = "发起人", col = 4)
    private String originator;

    /**
     * 宗旨
     */
    @ExcelColumn(value = "宗旨", col = 5)
    private String purpose;

    /**
     * 联系人电话
     */
    @ExcelColumn(value = "联系人电话", col = 6)
    private String contactsNumber;

    /**
     * 创建时间
     */
    @ExcelColumn(value = "创建时间", col = 7)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    private List<String> createDateList;

    /**
     * 更新时间
     */
    @ExcelColumn(value = "更新时间", col = 8)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;



    /**
     * 创建者
     */
    @ExcelColumn(value = "创建者", col = 9)
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
