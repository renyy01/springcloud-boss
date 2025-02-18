package com.camel.bpm.service;

import com.camel.bpm.model.WorkFlow;
import com.camel.core.entity.process.ActivitiEndCallBack;
import com.camel.core.entity.process.UserTask;
import com.github.pagehelper.PageInfo;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
/**
 *
 *                 ___====-_  _-====___
 *           _--^^^#####//      \\#####^^^--_
 *        _-^##########// (    ) \\##########^-_
 *       -############//  |\^^/|  \\############-
 *     _/############//   (@::@)   \\############\_
 *    /#############((     \\//     ))#############\
 *   -###############\\    (oo)    //###############-
 *  -#################\\  / VV \  //#################-
 * -###################\\/      \//###################-
 *_#/|##########/\######(   /\   )######/\##########|\#_
 *|/ |#/\#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
 *`  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
 *   `   `  `      `   / | |  | | \   '      '  '   '
 *                    (  | |  | |  )
 *                   __\ | |  | | /__
 *                  (vvv(VVV)(VVV)vvv)
 * <BPM接口类-定义>
 * @author baily
 * @since 1.0
 * @date 2019/7/7
 **/
public interface BpmService {
    /**
     * 部署流程
     * @param path
     * @return
     */
    Deployment deploy(Integer path);

    /**
     * 部署流程
     * @param resourceName
     * @param text
     * @return
     */
    Deployment deploy(String resourceName, String text);

    /**
     * 通过KEY获取流程定义
     * @param key
     * @return
     */
    List definition(String key);

    /**
     * 通过KEY获取任务
     * @param key
     * @return
     */
    List queryTask(String key);

    /**
     * 通过groupId获取任务列表
     * @param groupId
     * @return
     */
    List queryTaskByGroupId(List<String> groupId);

    /**
     * 获取流程定义分页查询
     * @param entity
     * @return
     */
    PageInfo<Deployment> defWorkflows(WorkFlow entity);

    /**
     通过流程KEY发起流程，并绑定业务key
     @param busniessKey
     @param flowKey
     @return
     */
    boolean apply(String busniessKey, String flowKey);

    /**
     通过流程ID发起流程，并绑定业务key
     @param busniessKey
     @param flowId
     @return
     */
    boolean applyById(String busniessKey, String flowId);

    /**
     查询当前流程
     @param busniessKey
     @param processDifinitionKey
     @return
     */
    List current(String busniessKey, String processDifinitionKey);

    /**
     流程追踪图
     @param taskId 任务ID
     @return
     */
    InputStream processTraceImage(String taskId);

    /**
     审批 --> 通过
     @param taskId
     @param variables
     @param activitiEndCallBack
     @return
     */
    boolean passProcess(String taskId, Map<String, Object> variables, ActivitiEndCallBack activitiEndCallBack);


    /**
     审批 --> 驳回
     @param taskId
     @param activityId
     @param variables
     @param activitiEndCallBack
     @return
     */
    boolean backProcess(String taskId, String activityId, Map<String, Object> variables, ActivitiEndCallBack activitiEndCallBack);

    /**
     获取评论及其审核内容
     @param id
     @return
     */
    List<UserTask> comments(String id);

    /**
     获取评论及其审核内容
     @param id
     @return
     */
    List<UserTask> commentsByInstanceId(String id);

    /**
     * 通过业务KEY获取流程实例
     * @param businessKey 业务KEY 一般为 类名字符串大写 + ID
     * @return 流程实例
     */
    ProcessInstance processInstance(String businessKey);

    /**
     * 通过认证信息 获取代办事项
     * @param authentication   oauth2.0 认证信息
     * @return  用户任务 列表
     */
    List<UserTask> toDo(OAuth2Authentication authentication);
}
