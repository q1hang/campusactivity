package com.campusactivity.core.community.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusactivity.common.exception.CustomException;
import com.campusactivity.common.exception.ParamException;
import com.campusactivity.core.community.dto.CIDTO;
import com.campusactivity.core.community.dto.CMDTO;
import com.campusactivity.core.community.dto.TaskDto;
import com.campusactivity.core.community.entity.Communitymembers;
import com.campusactivity.core.community.service.impl.CommunitymembersServiceImpl;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 社团成员表 前端控制器
 * </p>
 *
 * @author qihang
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/community/communitymembers")
public class CommunitymembersController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private CommunitymembersServiceImpl communitymembersService;

    /**
     * 添加成员
     * @param dto
     * @return
     */
    @PostMapping("/addMember")
    public CMDTO addMenber(@RequestBody CMDTO dto) throws Exception{
        //判断是否已在这个社团
        if(communitymembersService.checkIsExist(dto.getUserId(),dto.getCommunityId())){
            throw new CustomException("你已存在该社团");
        }
        Communitymembers communitymembers = new Communitymembers(dto);
        communitymembers.setArrivalTime(new Date());
        //发起审批流程
        communitymembersService.startProcess(dto.getCommunityId());
        //保存信息到数据库
        communitymembersService.save(communitymembers);
        return new CMDTO(communitymembers);
    }

    @PostMapping("/approval")
    public TaskDto approval(@RequestParam String business,@RequestParam String opinion){
        if(!(opinion.equals("yes")||opinion.equals("no"))){
            throw new ParamException("审批参数错误");
        }
        TaskDto result = communitymembersService.approval(business, opinion);
        if(opinion.equals("yes")){
            result.pass();
        }else {
            result.reject();
        }
        return result;
    }

    @PostMapping("editPosition")
    public String editPosition(Integer id,Integer communityId,String position){
        return "success";
    }

    /**
     * 根据流程标识查询当前流程进度
     * @param business
     * @return
     */
    @GetMapping("selectProcess/{business}")
    public TaskDto selectProcess(@PathVariable String business){
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(business).singleResult();
        return task==null?null:new TaskDto(task).pending();
    }


    @GetMapping("/getAlreadyJoined")
    public List<CIDTO> getAlreadyJoined() throws Exception{
        return communitymembersService.getAlreadyJoined();
    }

    @PostMapping("/getCurrentMembers")
    public IPage<CMDTO> getCurrentMembers(@RequestBody CMDTO dto)throws Exception{
        Page page = new Page(dto.getPageNum(), dto.getPageSize());
        return communitymembersService.getCurrentMembers(page,dto.getCommunityId());
    }


}

