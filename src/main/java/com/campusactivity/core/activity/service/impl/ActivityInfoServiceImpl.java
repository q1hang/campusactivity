package com.campusactivity.core.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.common.util.DateUtils;
import com.campusactivity.core.activity.dto.AtInfoDTO;
import com.campusactivity.core.activity.entity.ActivityInfo;
import com.campusactivity.core.activity.mapper.ActivityInfoMapper;
import com.campusactivity.core.activity.service.IActivityInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 活动信息表 服务实现类
 * </p>
 *
 * @author qihang
 * @since 2020-02-22
 */
@Service
public class ActivityInfoServiceImpl extends ServiceImpl<ActivityInfoMapper, ActivityInfo> implements IActivityInfoService {

    @Resource
    private ActivityInfoMapper activityInfoMapper;

    @Override
    public IPage<AtInfoDTO> search(Page page, AtInfoDTO dto) throws Exception {
        String activityIntroduce = dto.getActivityIntroduce();
        String activityName = dto.getActivityName();
        String address = dto.getAddress();
        String type = dto.getType();
        Integer manager = dto.getManager();
        Date registrationTime = dto.getRegistrationTime();
        Date registrationDeadline = dto.getRegistrationDeadline();
        Date startTime = dto.getStartTime();
        Date endTime = dto.getEndTime();


        QueryWrapper<ActivityInfo> wrapper=new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(activityName),"activity_name",activityName)
                .like(StringUtils.isNotBlank(activityIntroduce),"activity_introduce",activityIntroduce)
                .like(StringUtils.isNotBlank(address),"address",address)
                .like(StringUtils.isNotBlank(type),"type",type)
                .eq(manager!=null,"manager",manager);
        if(registrationTime!=null&&registrationDeadline!=null){
            wrapper.between("registration_time",
                    DateUtils.toMinDay(registrationTime),DateUtils.toMaxDay(registrationDeadline))
                    .between("registration_deadline",
                            DateUtils.toMinDay(registrationTime),DateUtils.toMaxDay(registrationDeadline));
        }
        if(startTime!=null&&endTime!=null){
            wrapper.between("start_time",
                    DateUtils.toMinDay(startTime),DateUtils.toMaxDay(endTime))
                    .between("end_time",
                    DateUtils.toMinDay(startTime),DateUtils.toMaxDay(endTime));
        }
        IPage<AtInfoDTO> atInfoDTOIPage = activityInfoMapper.pageActivityinfo(page, wrapper);

        return atInfoDTOIPage;
    }
}
