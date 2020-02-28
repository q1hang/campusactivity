package com.campusactivity.core.reimbursement.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.core.community.dto.TaskDto;
import com.campusactivity.core.reimbursement.dto.ProcessDTO;
import com.campusactivity.core.reimbursement.dto.ReimbursementDTO;
import com.campusactivity.core.reimbursement.service.impl.ActivityReimbursementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 活动报销表 前端控制器
 * </p>
 *
 * @author qihang
 * @since 2020-02-26
 */
@RestController
@RequestMapping("/reimbursement")
public class ActivityReimbursementController {

    @Autowired
    private ActivityReimbursementServiceImpl activityReimbursementService;

    @PostMapping("add")
    public ReimbursementDTO addReimbursement(@RequestBody ReimbursementDTO dto) throws Exception{
        return activityReimbursementService.saveReimbursementInfo(dto);
    }

    @PostMapping("approval")
    public TaskDto approval(@RequestBody ProcessDTO dto){
        return activityReimbursementService.approval(dto);
    }

    @PostMapping("search")
    public IPage<ReimbursementDTO> search(@RequestBody ReimbursementDTO dto) throws Exception{
        Page page=new Page(dto.getPageNum(),dto.getPageSize());
        return activityReimbursementService.pageReimbursementList(page, dto);
    }

}

