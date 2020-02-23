package com.campusactivity.core.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.common.util.Constant;
import com.campusactivity.common.util.ContextUtil;
import com.campusactivity.core.community.dto.CIDTO;
import com.campusactivity.core.community.entity.Communityinformation;
import com.campusactivity.core.community.entity.Communitymembers;
import com.campusactivity.core.community.entity.Permission;
import com.campusactivity.core.community.service.CommunityinformationService;
import com.campusactivity.core.community.service.impl.CommunityinformationServiceImpl;
import com.campusactivity.core.community.service.impl.CommunitymembersServiceImpl;
import com.campusactivity.core.community.service.impl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private CommunityinformationServiceImpl communityinformationService;
    @Autowired
    private PermissionServiceImpl permissionService;
    @Autowired
    private CommunitymembersServiceImpl communitymembersService;

    @PostMapping("/search")
    public IPage<CIDTO> search(@RequestBody(required = false)  CIDTO dto) throws Exception{
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        IPage<CIDTO> result = communityinformationService.search(page, dto);
        return result;
    }



    /**
     * 新增社团信息
     * @param dto
     * @return
     */
    @PostMapping("/save")
    public CIDTO save(@RequestBody CIDTO dto){
        Communityinformation communityinformation = new Communityinformation(dto);
        communityinformation.setCreateDate(new Date());
        communityinformation.setUpdateDate(new Date());
        communityinformation.setCreateUser(ContextUtil.getCurrentUserId());
        communityinformation.setUpdateUser(ContextUtil.getCurrentUserId());
        //保存社团信息
        communityinformationService.save(communityinformation);
        Integer currentUserId = ContextUtil.getCurrentUserId();
        Permission createUser=new Permission(currentUserId,communityinformation.getId(),
                Constant.PRIVILEGELEVEL_ADMIN,communityinformation.getCommunityName()+"创始人");
        //添加创建人权限
        permissionService.save(createUser);
        //添加社团成员表信息
        Communitymembers cb=new Communitymembers();
        cb.setUserId(currentUserId);
        cb.setCommunityId(communityinformation.getId());
        cb.setArrivalTime(new Date());
        cb.setState("2");
        cb.setPosition("社长");
        cb.setCreateDate(new Date());
        cb.setUpdateDate(new Date());
        cb.setCreateUser(currentUserId);
        cb.setUpdateUser(currentUserId);
        communitymembersService.save(cb);
        return new CIDTO(communityinformation);
    }

    /**
     * 更新社团信息
     * @param dto
     * @return
     */
    @PostMapping("/update")
    public CIDTO update(@RequestBody CIDTO dto){
        Communityinformation communityinformation = new Communityinformation(dto);
        //更新到数据库
        QueryWrapper<Communityinformation> wrapper = new QueryWrapper<>();
        communityinformation.setUpdateDate(new Date());
        wrapper.eq("id",dto.getId());
        communityinformationService.update(communityinformation,wrapper);
        return new CIDTO(communityinformation);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam Integer id){
        communityinformationService.removeById(id);
        //利用逻辑删除
    }
}

