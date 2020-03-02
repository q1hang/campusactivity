package com.campusactivity.core.reimbursement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.core.community.dto.TaskDto;
import com.campusactivity.core.reimbursement.dto.ProcessDTO;
import com.campusactivity.core.reimbursement.dto.ReimbursementDTO;
import com.campusactivity.core.reimbursement.entity.ActivityReimbursement;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 活动报销表 服务类
 * </p>
 *
 * @author qihang
 * @since 2020-02-26
 */
public interface IActivityReimbursementService extends IService<ActivityReimbursement> {

    ReimbursementDTO saveReimbursementInfo(ReimbursementDTO dto)throws Exception;

    void startReimbursementProcess(String business) throws Exception;

    TaskDto approval(ProcessDTO dto);

    IPage<ReimbursementDTO> pageReimbursementList(Page page,ReimbursementDTO dto) throws Exception;

    List<ReimbursementDTO> getMyToDoOfCM(Integer communityId);
}
