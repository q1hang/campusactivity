package com.campusactivity.core.community.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 社团信息表
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Communityinformation extends Model {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    @TableField("CommunityName")
    private String CommunityName;

    /**
     * 社团类型:1.科技型2.文艺型3.体育型4.公益型5.综合型
     */
    private String type;

    /**
     * 发起人
     */
    @TableField("Originator")
    private Integer Originator;

    /**
     * 宗旨
     */
    @TableField("Purpose")
    private String Purpose;

    /**
     * 联系人电话
     */
    @TableField("ContactsNumber")
    private String ContactsNumber;

    /**
     * 创建时间
     */
    @TableField("CreateDate")
    private LocalDateTime CreateDate;

    /**
     * 更新时间
     */
    @TableField("UpdateDate")
    private LocalDateTime UpdateDate;

    /**
     * 创建者
     */
    @TableField("CreateUser")
    private Integer CreateUser;

    /**
     * 更新者
     */
    @TableField("UpdateUser")
    private Integer UpdateUser;


}
