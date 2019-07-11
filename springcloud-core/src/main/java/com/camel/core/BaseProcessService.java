package com.camel.core;

import com.camel.core.entity.Result;
import com.camel.core.entity.process.ActivitiForm;

public interface BaseProcessService {
    /**
     发起申请
     @param id
     @param flowId
     @return
     */
    Boolean apply(Integer id, String flowId, String businessKey);

    /**
     查询当前流程
     @param id
     @return
     */
    Result current(Integer id, String businessKey);

    /**
     查询当前流程
     @param id
     @return
     */
    Result current(String id, String businessKey);

    /**
     * 通过
     * @param id
     * @param activitiForm
     * @return
     */
    Result pass(String id, ActivitiForm activitiForm, String businessKey);

    /**
     * 驳回
     * @param id
     * @param activitiForm
     * @return
     */
    Result back(String id, ActivitiForm activitiForm, String businessKey);

    /**
     * 添加审核意见
     * @param id
     * @return
     */
    Result comment(String id);

    /**
     * 获取代办事项
     * @return
     */
    Result toDO();
}
