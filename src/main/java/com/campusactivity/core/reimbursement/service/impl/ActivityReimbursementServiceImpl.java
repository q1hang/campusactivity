package com.campusactivity.core.reimbursement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.common.exception.CustomException;
import com.campusactivity.common.exception.NullTaskException;
import com.campusactivity.common.util.ContextUtil;
import com.campusactivity.core.community.dto.TaskDto;
import com.campusactivity.core.community.entity.Permission;
import com.campusactivity.core.community.entity.ProcessStatus;
import com.campusactivity.core.community.service.impl.PermissionServiceImpl;
import com.campusactivity.core.community.service.impl.ProcessStatusServiceImpl;
import com.campusactivity.core.reimbursement.dto.CostDTO;
import com.campusactivity.core.reimbursement.dto.ProcessDTO;
import com.campusactivity.core.reimbursement.dto.ReimbursementDTO;
import com.campusactivity.core.reimbursement.entity.ActivityCost;
import com.campusactivity.core.reimbursement.entity.ActivityReimbursement;
import com.campusactivity.core.reimbursement.mapper.ActivityReimbursementMapper;
import com.campusactivity.core.reimbursement.service.IActivityReimbursementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.campusactivity.common.util.Constant.*;

/**
 * <p>
 * 活动报销表 服务实现类
 * </p>
 *
 * @author qihang
 * @since 2020-02-26
 */
@Service
public class ActivityReimbursementServiceImpl extends ServiceImpl<ActivityReimbursementMapper, ActivityReimbursement> implements IActivityReimbursementService {


    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProcessStatusServiceImpl processStatusService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private PermissionServiceImpl permissionService;
    @Autowired
    private ActivityCostServiceImpl activityCostService;
    @Resource
    private ActivityReimbursementMapper activityReimbursementMapper;

    @Override
    public IPage<ReimbursementDTO> pageReimbursementList(Page page, ReimbursementDTO dto) throws Exception {
        QueryWrapper<ActivityReimbursement> wrapper = new QueryWrapper<>();
        wrapper.eq("ac.delete_status", DELETE_STATUS_NORMAL)
                .eq(dto.getCommunityId() != null, "ac.community_id", dto.getCommunityId())
                .eq(dto.getActivityId() != null, "ac.activity_id", dto.getActivityId())
                .like(StringUtils.isNotBlank(dto.getReimbursementCode()), "ac.reimbursement_code", dto.getReimbursementCode())
                .like(StringUtils.isNotBlank(dto.getStatus()), "ac.status", dto.getStatus())
        ;
        IPage<ReimbursementDTO> reimbursementDTOIPage = activityReimbursementMapper.pageReimbursementList(page, wrapper);

        reimbursementDTOIPage.getRecords().forEach(x->{
            QueryWrapper<ActivityCost> wrapperOfcost = new QueryWrapper<>();
            wrapperOfcost.eq(x.getId()!=null, "reimbursement_id", x.getId());
            List<ActivityCost> list = activityCostService.list(wrapperOfcost);
            List<CostDTO> result = list.stream().map(y -> {
                return new CostDTO(y);
            }).collect(Collectors.toList());

            x.setCostList(result);
            Task nextTask = taskService.createTaskQuery().processInstanceBusinessKey(x.getReimbursementCode()).singleResult();
            if(nextTask!=null)
                x.setNodeName(nextTask.getName());
        });
        return reimbursementDTOIPage;
    }

    @Override
    public TaskDto approval(ProcessDTO dto) {
        String business = dto.getBusiness();
        String opinion = dto.getOpinion();
        String remark = dto.getRemark();
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(business).singleResult();
        if(task==null){
            throw new NullTaskException("当前Business并无任务");
        }
        //TODO 验证身份
        Integer currentUserId = ContextUtil.getCurrentUserId();
        if(task.getName().equals("社长审批")){
            if(!IsMatchLevel(currentUserId,business,PRIVILEGELEVEL_ADMIN)){
                throw new CustomException("没有操作权限");
            }
        }
        if(task.getName().equals("财务审批")){
            if(!IsMatchLevel(currentUserId,business,PRIVILEGELEVEL_FINANCE)){
                throw new CustomException("没有操作权限");
            }
        }
        if(task.getName().equals("填写报销单")){
            if(!IsOneself(currentUserId,business)){
                throw new CustomException("没有操作权限");
            }
        }
        Map<String, Object> variables1 = new HashMap<>();
        variables1.put("opinion", opinion);
        taskService.complete(task.getId(), variables1);
        Task nextTask = taskService.createTaskQuery().processInstanceBusinessKey(business).singleResult();
        //保存任务信息
        ProcessStatus ps=new ProcessStatus();
        ps.setTaskId(task.getId());
        ps.setTaskName(task.getName());
        ps.setRemark(remark);
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
           ActivityReimbursement ar=new ActivityReimbursement();
            ar.setStatus("2");
            ar.setUpdateDate(new Date());
            ar.setUpdateUser(ContextUtil.getCurrentUserId());
            update(ar,new QueryWrapper<ActivityReimbursement>().eq("reimbursement_code",business));
        }
        return new TaskDto(task);
    }

    @Override
    @Transactional
    public ReimbursementDTO saveReimbursementInfo(ReimbursementDTO dto) throws Exception {

        //TODO 身份校验

        List<CostDTO> costList = dto.getCostList();
        //加锁
        RLock lock = redissonClient.getLock("lock_generate_remibursement_code");
        //生成报销单号
        String reimbursementCode = createReimbursementCode();
        //启动流程
        startReimbursementProcess(reimbursementCode);
        //保存报销单信息
        ActivityReimbursement entity = new ActivityReimbursement(dto);
        entity.setReimbursementCode(reimbursementCode);
         BigDecimal temp=new BigDecimal("0");
        if (costList != null && !costList.isEmpty()) {
            for(CostDTO costDTO:costList){
                 temp = temp.add(costDTO.getCostPrice());
            }
        }
        entity.setTotalAmount(temp);
        entity.setCreateDate(new Date());
        entity.setUpdateDate(new Date());
        Integer currentUserId = ContextUtil.getCurrentUserId();
        entity.setCreateUser(currentUserId);
        entity.setUpdateUser(currentUserId);
        this.save(entity);
        //保存费用列表信息
        if (costList != null && !costList.isEmpty()) {
            List<ActivityCost> costEntityList = costList.stream().map(x -> {
                x.setReimbursementId(entity.getId());
                return new ActivityCost(x);
            }).collect(Collectors.toList());
            activityCostService.saveBatch(costEntityList);
        }
        //释放锁
        lock.unlock();
        if(dto.getStatus().equals("1")){
            approval(new ProcessDTO(reimbursementCode,"yes","无"));
        }
        return dto;
    }

    /**
     * 创建一个新的报销流程
     * @param business
     * @throws Exception
     */
    @Override
    public void startReimbursementProcess(String business) throws Exception {
        ProcessInstanceBuilder processInstanceBuilder = runtimeService.createProcessInstanceBuilder();
        //判断历史数据以及当前流程中是否有重复的business
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(business).singleResult();
        if(historicProcessInstance!=null){
            throw new CustomException("该流程已经存在: "+business);
        }
        ProcessInstance isExist = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(business).singleResult();
        if(isExist!=null){
            throw new CustomException("该流程已经存在: "+business);
        }
        processInstanceBuilder.businessKey(business).processDefinitionKey("reimbursement").start();
    }

    /**
     * 报销单号生成规则
     * @return 报销单号
     */
    private String createReimbursementCode(){
        String newReimbursementCode = null;
        Integer counts = 0;
        SimpleDateFormat formats = new SimpleDateFormat("yyyyMMdd");
        String prefixCode = "MK" + formats.format(new Date());
        String reimbursementNo = activityReimbursementMapper.getReimbursementCode(prefixCode);
        if (reimbursementNo != null) {
            counts = Integer.parseInt(reimbursementNo.substring(10));
            if (counts >= 9 && counts < 99) {
                newReimbursementCode = (prefixCode + "00000" + (counts + 1));
            } else if (counts >= 99 && counts < 999) {
                newReimbursementCode = (prefixCode + "0000" + (counts + 1));
            } else if (counts >= 999 && counts < 9999) {
                newReimbursementCode = (prefixCode + "000" + (counts + 1));
            } else if (counts >= 9999 && counts < 99999) {
                newReimbursementCode = (prefixCode + "00" + (counts + 1));
            } else if (counts >= 99999 && counts < 999999) {
                newReimbursementCode = (prefixCode + "0" + (counts + 1));
            } else if (counts >= 999999 && counts < 9999999) {
                newReimbursementCode = (prefixCode + (counts + 1));
            } else if (counts < 9) {
                newReimbursementCode = (prefixCode + "000000" + (counts + 1));
            }
        } else {
            newReimbursementCode = (prefixCode + "000000" + (counts + 1));
        }
        return newReimbursementCode;
    }

    /**
     * 判断该用户是否拥有level的等级
     * @param userId
     * @param business
     * @param level
     * @return 拥有则返回true
     */
    private boolean IsMatchLevel(Integer userId,String business,String level){
        Integer communityId = getOne(new QueryWrapper<ActivityReimbursement>().eq("reimbursement_code", business)).getCommunityId();
        Permission one = permissionService.getOne(new QueryWrapper<Permission>().eq("UserId", userId)
                .eq("CommunityId", communityId));
        if(one!=null&&(one.getPrivilegeLevel().equals(level))){
            return true;
        }else {
            return false;
        }
    }

    private boolean IsOneself(Integer userId,String business){
        Integer userid = getOne(new QueryWrapper<ActivityReimbursement>().eq("reimbursement_code", business)).getCreateUser();
        if(userId.equals(userid)){
            return true;
        }else{
            return false;
        }
    }

}
