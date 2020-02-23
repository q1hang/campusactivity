package com.campusactivity.core.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.core.activity.dto.AtInfoDTO;
import com.campusactivity.core.activity.entity.ActivityInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 活动信息表 服务类
 * </p>
 *
 * @author qihang
 * @since 2020-02-22
 */
public interface IActivityInfoService extends IService<ActivityInfo> {

    public IPage<AtInfoDTO> search(Page page, AtInfoDTO dto) throws Exception;

}
