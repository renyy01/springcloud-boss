package com.camel.bpm.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.camel.bpm.mapper.WorkFlowMapper;
import com.camel.bpm.model.WorkFlow;
import com.camel.bpm.service.WorkFlowService;
import com.camel.bpm.utils.ActivitiObj2HashMapUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-05-16
 */
@Service
public class WorkFlowServiceImpl extends ServiceImpl<WorkFlowMapper, WorkFlow> implements WorkFlowService {
    @Autowired
    private WorkFlowMapper mapper;

    @Autowired
    private ProcessEngine engine;

    @Override
    public PageInfo<WorkFlow> pageQuery(WorkFlow entity) {
        PageInfo pageInfo = PageHelper.startPage(entity.getPageNum(), entity.getPageSize()).doSelectPageInfo(() -> mapper.list(entity));
        return pageInfo;
    }

    @Override
    public PageInfo<Deployment> pageQueryDeployed(WorkFlow entity) {
        PageInfo<Deployment> page = new PageInfo();
        List<Deployment> list = engine.getRepositoryService().createDeploymentQuery().list();
        page.setTotal(list.size());
        Collections.reverse(list);
        list.forEach((item) -> {
            DeploymentEntity deploymentEntity = (DeploymentEntity) item;
            deploymentEntity.setResources(new HashMap<>(16));
        });
        List<Deployment> deployments = list.subList((entity.getPageNum() - 1) * entity.getPageSize(), list.size());
        page.setList(deployments);
        return page;
    }

    @Override
    public List<Map<String, Object>> deployed(WorkFlow workFlow) {
        List<Map<String, Object>> result = new ArrayList<>();
        ActivitiObj2HashMapUtils insatnce = ActivitiObj2HashMapUtils.getInstance();
        List<ProcessDefinition> list = engine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(workFlow.getKey()).latestVersion().list();
        list.forEach(processDefinition -> {
            result.add(insatnce.processDefinition2Map(processDefinition));
        });
        return result;
    }
}
