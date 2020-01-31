package com.campusactivity.core.community.controller;


import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 部署请假流程
     */
    @RequestMapping("deploy")
    public void deploy(){
        repositoryService.createDeployment().addClasspathResource("recruitNew.bpmn20.xml").deploy();
    }

    /**
     * 禁止请假流程
     */
    @RequestMapping("stop")
    public String stopStratProcess(){
        repositoryService.suspendProcessDefinitionByKey("recruitNew");
        return "已禁止";
    }

    /**
     * 恢复请假流程
     */
    @RequestMapping("recover")
    public String recoverStratProcess(){
        repositoryService.activateProcessDefinitionByKey("recruitNew");
        return "已恢复";
    }
}

