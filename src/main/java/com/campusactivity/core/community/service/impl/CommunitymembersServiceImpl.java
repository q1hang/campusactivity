package com.campusactivity.core.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.common.exception.CustomException;
import com.campusactivity.common.exception.NullTaskException;
import com.campusactivity.common.util.ContextUtil;
import com.campusactivity.core.community.dto.CIDTO;
import com.campusactivity.core.community.dto.CMDTO;
import com.campusactivity.core.community.dto.TaskDto;
import com.campusactivity.core.community.entity.Communityinformation;
import com.campusactivity.core.community.entity.Communitymembers;
import com.campusactivity.core.community.entity.Permission;
import com.campusactivity.core.community.entity.ProcessStatus;
import com.campusactivity.core.community.mapper.CommunitymembersMapper;
import com.campusactivity.core.community.service.CommunitymembersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.campusactivity.common.util.Constant.PRIVILEGELEVEL_ADMIN;
import static com.campusactivity.common.util.Constant.PRIVILEGELEVEL_ORDINARY;

/**
 * <p>
 * 社团成员表 服务实现类
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@Service
public class CommunitymembersServiceImpl extends ServiceImpl<CommunitymembersMapper, Communitymembers> implements CommunitymembersService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProcessStatusServiceImpl processStatusService;
    @Autowired
    private PermissionServiceImpl permissionService;
    @Autowired
    private CommunityinformationServiceImpl communityinformationService;
    @Resource
    private CommunitymembersMapper communitymembersMapper;

    @Override
    public void startProcess(Integer communityId) throws Exception{
        ProcessInstanceBuilder processInstanceBuilder = runtimeService.createProcessInstanceBuilder();
        //生成流程标识
        String business=createProcessCode(communityId);
        //判断历史数据以及当前流程中是否有重复的business
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(business).singleResult();
        if(historicProcessInstance!=null){
            throw new CustomException("该流程已经存在: "+business);
        }
        ProcessInstance isExist = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(business).singleResult();
        if(isExist!=null){
            throw new CustomException("该流程已经存在: "+business);
        }
        processInstanceBuilder.businessKey(business).processDefinitionKey("recruitNew").start();
    }

    @Override
    public TaskDto approval(String business,String opinion) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(business).singleResult();
        if(task==null){
            throw new NullTaskException("当前Business并无任务");
        }
        //验证身份
        //根据business得到社团Id
        Integer communityId=Integer.valueOf(business.substring(2, business.indexOf("_")));
        if (!PermissionOrNot(ContextUtil.getCurrentUserId(),communityId)) {
            throw new CustomException("当前账号无权限操作该流程");
        }


        Map<String, Object> variables1 = new HashMap<>();
        variables1.put("opinion", opinion);
        taskService.complete(task.getId(), variables1);

        Task nextTask = taskService.createTaskQuery().processInstanceBusinessKey(business).singleResult();


        //保存任务信息
        ProcessStatus ps=new ProcessStatus();
        ps.setTaskId(task.getId());
        ps.setTaskName(task.getName());
        ps.setRemark("审批说明");
        ps.setProcessBusiness(business);
        ps.setApprover(ContextUtil.getCurrentUserId());
        ps.setApproveTime(new Date());
        if(opinion.equals("yes")){
            ps.setApproveResult("2");
        }else {
            ps.setApproveResult("0");
        }
        processStatusService.save(ps);
        if(nextTask==null){
            Communitymembers cm=new Communitymembers();
            cm.setState("2");
            cm.setUpdateDate(new Date());
            cm.setUpdateUser(ContextUtil.getCurrentUserId());
            //根据business得到用户Id
            Integer userId=Integer.valueOf(business.substring(business.indexOf("_")+1));
            update(cm,new QueryWrapper<Communitymembers>().eq("UserId",userId)
            .eq("CommunityId",communityId));
        }

        return new TaskDto(task);
    }

    private String createProcessCode(Integer communityId){
        Integer userId=  ContextUtil.getCurrentUserId();
        String prefixCode = "AM" + communityId + "_" + userId;
        return prefixCode;
    }


    /**
     * 判断该用户是否有权限管理该社团
     * @param userId
     * @param communityId
     * @return 拥有则返回true
     */
    private boolean PermissionOrNot(Integer userId,Integer communityId){
        Permission one = permissionService.getOne(new QueryWrapper<Permission>().eq("UserId", userId)
                .eq("CommunityId", communityId));
        if(one!=null&&(one.getPrivilegeLevel().equals(PRIVILEGELEVEL_ORDINARY)||one.getPrivilegeLevel().equals(PRIVILEGELEVEL_ADMIN))){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 判断该用户是否在该社团中。
     * @param userId
     * @param communityId
     * @return 存在则返回true
     */
    public boolean checkIsExist(Integer userId,Integer communityId){
        Communitymembers one = getOne(new QueryWrapper<Communitymembers>().eq("UserId", userId)
                .eq("CommunityId", communityId));
        return one!=null;
    }

    /**
     * 获取当前用户已加入的社团信息列表
     * @return
     * @throws Exception
     */
    @Override
    public List<CIDTO> getAlreadyJoined() throws Exception {
        Integer currentUserId = ContextUtil.getCurrentUserId();
        List<Communitymembers> list = list(new QueryWrapper<Communitymembers>()
        .eq("UserId", currentUserId));
        List<Integer> comunityIdList = list.stream().map(x -> {
            return x.getCommunityId();
        }).collect(Collectors.toList());
        Collection<Communityinformation> communityinformations = communityinformationService.listByIds(comunityIdList);
        List<CIDTO> result = communityinformations.stream().map(x -> {
            return new CIDTO(x);
        }).collect(Collectors.toList());
        return result;
    }

    /**
     * 获取某个社团的所有社员信息
     * @return
     * @throws Exception
     */
    @Override
    public IPage<CMDTO> getCurrentMembers(Page page,Integer communityId) throws Exception {
        QueryWrapper<Communitymembers> wrapper = new QueryWrapper<>();
        wrapper.eq("CommunityId", communityId);
        return communitymembersMapper.pageCommunitymem(page, wrapper);
    }
}
