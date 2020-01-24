package com.campusactivity.core.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.core.community.dto.CIDTO;
import com.campusactivity.core.community.entity.Communityinformation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 社团信息表 服务类
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
public interface CommunityinformationService extends IService<Communityinformation> {
    public IPage<CIDTO> search(Page page, CIDTO dto) throws Exception;
}
