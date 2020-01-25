package com.campusactivity.core.community.dto;

import com.campusactivity.common.base.PageDTO;
import com.campusactivity.core.community.entity.Communitymembers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


/**
 * @Author q1hang
 * @Description 社团成员DTO
 * @create: 2020-01-25 21:13@Data
 * @NoArgsConstructor
 * @AllArgsConstructor
 * @ToString
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CMDTO extends PageDTO {

    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 社团ID
     */
    private Integer communityId;

    /**
     * 到达时间
     */
    private Date arrivalTime;

    /**
     * 状态:1.审核中2.在职3.已退出4.毕业
     */
    private String state;

    /**
     * 职位
     */
    private String position;

    private String communitymemberscol;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 创建者
     */
    private Integer createUser;

    /**
     * 更新者
     */
    private Integer updateUser;

    public CMDTO(Communitymembers communitymembers){
        this.id=communitymembers.getId();
        this.userId=communitymembers.getUserId();
        this.communityId=communitymembers.getCommunityId();
        this.arrivalTime=communitymembers.getArrivalTime();
        this.state=communitymembers.getState();
        this.position=communitymembers.getPosition();
        this.communitymemberscol=communitymembers.getCommunitymemberscol();
        this.createDate=communitymembers.getCreateDate();
        this.updateDate=communitymembers.getUpdateDate();
        this.createUser=communitymembers.getCreateUser();
        this.updateUser=communitymembers.getUpdateUser();
    }
}
