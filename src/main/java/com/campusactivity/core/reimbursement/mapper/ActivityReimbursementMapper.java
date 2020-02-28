package com.campusactivity.core.reimbursement.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.core.community.dto.CIDTO;
import com.campusactivity.core.reimbursement.dto.ReimbursementDTO;
import com.campusactivity.core.reimbursement.entity.ActivityReimbursement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 活动报销表 Mapper 接口
 * </p>
 *
 * @author qihang
 * @since 2020-02-26
 */
public interface ActivityReimbursementMapper extends BaseMapper<ActivityReimbursement> {

    String getReimbursementCode(@Param("prefixCode") String prefixCode);

    IPage<ReimbursementDTO> pageReimbursementList(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
