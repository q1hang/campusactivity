package com.campusactivity.core.community.service.impl;

import com.campusactivity.core.community.entity.ProcessStatus;
import com.campusactivity.core.community.mapper.ProcessStatusMapper;
import com.campusactivity.core.community.service.IProcessStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务过程记录表 服务实现类
 * </p>
 *
 * @author qihang
 * @since 2020-01-26
 */
@Service
public class ProcessStatusServiceImpl extends ServiceImpl<ProcessStatusMapper, ProcessStatus> implements IProcessStatusService {

}
