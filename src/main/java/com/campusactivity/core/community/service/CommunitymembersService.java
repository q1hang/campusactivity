package com.campusactivity.core.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.core.community.dto.CIDTO;
import com.campusactivity.core.community.dto.CMDTO;
import com.campusactivity.core.community.dto.TaskDto;
import com.campusactivity.core.community.entity.Communitymembers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 社团成员表 服务类
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
public interface CommunitymembersService extends IService<Communitymembers> {


    public void startProcess(Integer CommunityId) throws Exception;

    public TaskDto approval(String business,String opinion);


    public List<CIDTO> getAlreadyJoined() throws Exception;

    public IPage<CMDTO> getCurrentMembers(Page page,Integer communityId) throws Exception;

}
