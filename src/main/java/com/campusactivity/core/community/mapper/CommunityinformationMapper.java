package com.campusactivity.core.community.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.core.community.dto.CIDTO;
import com.campusactivity.core.community.entity.Communityinformation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 社团信息表 Mapper 接口
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
public interface CommunityinformationMapper extends BaseMapper<Communityinformation> {

    IPage<CIDTO> pageCommunityinfo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
