package com.camel.bpm.controller;

import com.camel.bpm.model.WorkFlow;
import com.camel.bpm.service.WorkFlowService;
import com.camel.core.entity.Result;
import com.camel.core.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baily
 */
@RestController
@RequestMapping("/deployedWorkflows")
public class DeployedWorkflows {
    @Autowired
    private WorkFlowService service;

    @GetMapping
    public Result index(WorkFlow workFlow){
        return ResultUtil.success(service.pageQueryDeployed(workFlow));
    }
}
