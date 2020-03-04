package com.campusactivity.core.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusactivity.common.exception.CustomException;
import com.campusactivity.common.exception.ParamException;
import com.campusactivity.common.util.ContextUtil;
import com.campusactivity.core.community.dto.CMDTO;
import com.campusactivity.core.community.dto.grantDTO;
import com.campusactivity.core.community.dto.progressDTO;
import com.campusactivity.core.community.entity.Permission;
import com.campusactivity.core.community.service.impl.CommunitymembersServiceImpl;
import com.campusactivity.core.community.service.impl.PermissionServiceImpl;
import com.campusactivity.core.reimbursement.dto.ReimbursementDTO;
import com.campusactivity.core.reimbursement.service.impl.ActivityReimbursementServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 社团权限表 前端控制器
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionServiceImpl permissionService;
    @Autowired
    private CommunitymembersServiceImpl communitymembersService;
    @Autowired
    private ActivityReimbursementServiceImpl activityReimbursementService;

    @GetMapping("getApprovalProgress")
    public progressDTO getApprovalProgress(){
        progressDTO result= new progressDTO();
        Integer currentUserId = ContextUtil.getCurrentUserId();
        List<CMDTO> cmdtos = communitymembersService.likeBusiness("%\\_" + currentUserId+"%");
        result.setRucruitList(cmdtos);
        result.setReimbursementList(activityReimbursementService.getMyToDo());

        return  result;
    }

    @PostMapping("grant")
    public void grant(@RequestBody grantDTO dto){
        if(dto.getUserId()==null||dto.getCommunityId()==null|| StringUtils.isBlank(dto.getGrantLevel())){
            throw new ParamException("参数错误");
        }
        Permission one = permissionService.getOne(new QueryWrapper<Permission>().eq("UserId", dto.getUserId())
                .eq("CommunityId", dto.getCommunityId()));
        if(one==null){
            one=new Permission();
            one.setUserId(dto.getUserId());
            one.setCommunityId(dto.getCommunityId());
            one.setPrivilegeLevel(dto.getGrantLevel());
            one.setRemarks(dto.getRemark());
            one.setCreateDate(new Date());
            one.setUpdateDate(new Date());
            one.setCreateUser(ContextUtil.getCurrentUserId());
            one.setUpdateUser(ContextUtil.getCurrentUserId());
            permissionService.save(one);
        }else {
            Permission two=new Permission();
            two.setPrivilegeLevel(dto.getGrantLevel());
            two.setRemarks(dto.getRemark());
            two.setUpdateDate(new Date());
            two.setUpdateUser(ContextUtil.getCurrentUserId());
            permissionService.update(two,new QueryWrapper<Permission>().eq("UserId",dto.getUserId())
            .eq("CommunityId",dto.getCommunityId()));
        }
    }

}

