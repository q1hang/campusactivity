package com.campusactivity.core.community.controller;


import com.campusactivity.core.community.dto.TaskDto;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务过程记录表 前端控制器
 * </p>
 *
 * @author qihang
 * @since 2020-01-26
 */
@RestController
@RequestMapping("/community/processStatus")
public class ProcessStatusController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    /**
     * 部署招新流程
     */
        @RequestMapping("deployRecruitNew")
    public void deployRecruitNew(){
        repositoryService.createDeployment().addClasspathResource("recruitNew.bpmn20.xml").deploy();
    }

    /**
     * 禁止招新流程
     */
    @RequestMapping("stopRecruitNew")
    public String stopRecruitNewProcess(){
        repositoryService.suspendProcessDefinitionByKey("recruitNew");
        return "已禁止";
    }

    /**
     * 恢复招新流程
     */
    @RequestMapping("recoverReimbursement")
    public String recoverReimbursementProcess(){
        repositoryService.activateProcessDefinitionByKey("recruitNew");
        return "已恢复";
    }

    /**
     * 部署报销流程
     */
    @RequestMapping("deployReimbursement")
    public void deployReimbursement(){
        repositoryService.createDeployment().addClasspathResource("reimbursement.bpmn20.xml").deploy();
    }

    /**
     * 禁止报销流程
     */
    @RequestMapping("stopReimbursement")
    public String stopReimbursementProcess(){
        repositoryService.suspendProcessDefinitionByKey("reimbursement");
        return "已禁止";
    }

    /**
     * 恢复报销流程
     */
    @RequestMapping("recoverRecruitNew")
    public String recoverRecruitNewProcess(){
        repositoryService.activateProcessDefinitionByKey("reimbursement");
        return "已恢复";
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
}

