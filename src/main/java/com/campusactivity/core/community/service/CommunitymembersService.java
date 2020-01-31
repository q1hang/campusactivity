package com.campusactivity.core.community.service;

import com.campusactivity.core.community.dto.TaskDto;
import com.campusactivity.core.community.entity.Communitymembers;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
