package com.campusactivity.core.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.common.util.ContextUtil;
import com.campusactivity.common.util.DateUtils;
import com.campusactivity.core.community.dto.CIDTO;
import com.campusactivity.core.community.entity.Communityinformation;
import com.campusactivity.core.community.mapper.CommunityinformationMapper;
import com.campusactivity.core.community.service.CommunityinformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 社团信息表 服务实现类
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@Service
public class CommunityinformationServiceImpl extends ServiceImpl<CommunityinformationMapper, Communityinformation> implements CommunityinformationService {

    @Resource
    private CommunityinformationMapper communityinformationMapper;

    @Override
    public IPage<CIDTO> search(Page page, CIDTO dto) throws Exception{
        QueryWrapper<Communityinformation> wrapper=new QueryWrapper<>();
        Integer id = dto.getId();
        String communityName = dto.getCommunityName();
        String contactsNumber = dto.getContactsNumber();
        String originator = dto.getOriginator();
        String purpose = dto.getPurpose();
        String type = dto.getType();
        Date createDate = dto.getCreateDate();
        Integer createUser = dto.getCreateUser();
        Date updateDate = dto.getUpdateDate();
        Integer updateUser = dto.getUpdateUser();

        wrapper.eq(id!=null,"ci.id",id)
                .like(StringUtils.isNotBlank(communityName),"ci.CommunityName",communityName)
                .like(StringUtils.isNotBlank(contactsNumber),"ci.ContactsNumber",contactsNumber)
                .like(StringUtils.isNotBlank(originator),"su.username",originator)
                .like(StringUtils.isNotBlank(purpose),"ci.Purpose",purpose)
                .eq(StringUtils.isNotBlank(type),"ci.type",type)
                .between(createDate!=null,"ci.CreateDate",
                        DateUtils.toMinDay(createDate),DateUtils.toMaxDay(createDate))
                .between(updateDate!=null,"ci.UpdateDate",
                        DateUtils.toMinDay(updateDate),DateUtils.toMaxDay(updateDate))
        //TODO 用户人搜索
        ;

        IPage<CIDTO> result = communityinformationMapper.pageCommunityinfo(page, wrapper);
        return result;
    }

}
