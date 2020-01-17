package com.campusactivity.core.community.controller;


import com.campusactivity.core.community.dto.CIDTO;
import com.campusactivity.core.community.entity.Communityinformation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 社团信息表 前端控制器
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/community/communityinformation")
public class CommunityinformationController {

    /**
     * 创建社团
     * @return
     */
    @PostMapping("/save")
    public String save(@RequestParam CIDTO dto){
        Communityinformation communityinformation = new Communityinformation(dto);
        //保存到数据库

        return "success";
    }
}

