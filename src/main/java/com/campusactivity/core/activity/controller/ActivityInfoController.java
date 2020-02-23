package com.campusactivity.core.activity.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.common.util.ContextUtil;
import com.campusactivity.core.activity.dto.AtInfoDTO;
import com.campusactivity.core.activity.entity.ActivityInfo;
import com.campusactivity.core.activity.service.impl.ActivityInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 活动信息表 前端控制器
 * </p>
 *
 * @author qihang
 * @since 2020-02-22
 */
@RestController
@RequestMapping("/activity/activityInfo")
public class ActivityInfoController {

    @Autowired
    private ActivityInfoServiceImpl activityInfoService;

    /**
     * 查询活动信息
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("search")
    public IPage<AtInfoDTO> search(@RequestBody AtInfoDTO dto) throws Exception{
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        IPage<AtInfoDTO> result = activityInfoService.search(page, dto);
        return result;
    }

    /**
     * 新增活动信息
     * @param dto
     * @return
     */
    @PostMapping("save")
    public AtInfoDTO save(@RequestBody AtInfoDTO dto){
        ActivityInfo activityInfo = new ActivityInfo(dto);
        activityInfo.setCreateDate(new Date());
        activityInfo.setUpdateDate(new Date());
        Integer currentUserId = ContextUtil.getCurrentUserId();
        activityInfo.setCreateUser(currentUserId);
        activityInfo.setUpdateUser(currentUserId);
        activityInfoService.save(activityInfo);
        System.out.println(activityInfo.toString());
        return new AtInfoDTO(activityInfo);
    }

    /**
     * 更新活动信息
     * @param dto
     * @return
     */
    @PostMapping("/update")
    public AtInfoDTO update(@RequestBody AtInfoDTO dto){
        ActivityInfo activityInfo = new ActivityInfo(dto);
        //更新到数据库
        QueryWrapper<ActivityInfo> wrapper = new QueryWrapper<>();
        activityInfo.setUpdateDate(new Date());
        wrapper.eq("id",dto.getId());
        activityInfoService.update(activityInfo,wrapper);
        return new AtInfoDTO(activityInfo);
    }

    /**
     * 删除活动
     * @param id
     */
    @PostMapping("delete")
    public void delete(@RequestParam Integer id){
        activityInfoService.removeById(id);
    }

}

