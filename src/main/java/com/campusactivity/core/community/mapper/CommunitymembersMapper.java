package com.campusactivity.core.community.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.core.community.dto.CMDTO;
import com.campusactivity.core.community.entity.Communitymembers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 社团成员表 Mapper 接口
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
public interface CommunitymembersMapper extends BaseMapper<Communitymembers> {

    IPage<CMDTO> pageCommunitymem(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
