package com.campusactivity.core.community.controller;


import com.campusactivity.core.community.dto.CMDTO;
import com.campusactivity.core.community.entity.Communitymembers;
import com.campusactivity.core.community.service.impl.CommunitymembersServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 社团成员表 前端控制器
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/community/communitymembers")
public class CommunitymembersController {

    private CommunitymembersServiceImpl communitymembersService;

    @PostMapping("/addMember")
    public CMDTO addMenber(CMDTO dto){
        Communitymembers communitymembers = new Communitymembers(dto);
        //保存到数据库
        communitymembersService.save(communitymembers);

        //TODO 发起审批流程

        return new CMDTO(communitymembers);
    }

}

