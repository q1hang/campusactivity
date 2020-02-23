package com.campusactivity.core.activity.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.core.activity.dto.AtInfoDTO;
import com.campusactivity.core.activity.entity.ActivityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 活动信息表 Mapper 接口
 * </p>
 *
 * @author qihang
 * @since 2020-02-22
 */
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {

    IPage<AtInfoDTO> pageActivityinfo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
