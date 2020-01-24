package com.campusactivity.core.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.core.community.dto.CIDTO;
import com.campusactivity.core.community.entity.Communityinformation;
import com.campusactivity.core.community.service.CommunityinformationService;
import com.campusactivity.core.community.service.impl.CommunityinformationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(dto.toString());
        Communityinformation communityinformation = new Communityinformation(dto);
        //保存到数据库
        communityinformationService.save(communityinformation);
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
        wrapper.eq("id",dto.getId());
        communityinformationService.update(communityinformation,wrapper);
        return dto;
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id){
        communityinformationService.removeById(id);
    }
}

