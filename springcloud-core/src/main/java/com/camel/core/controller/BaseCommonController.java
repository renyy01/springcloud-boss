package com.camel.core.controller;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.toolkit.MapUtils;
import com.camel.core.BaseProcessService;
import com.camel.core.config.ProcessProperties;
import com.camel.core.entity.BaseEntity;
import com.camel.core.entity.BaseProcessPaginationEntity;
import com.camel.core.entity.Result;
import com.camel.core.entity.process.ActivitiForm;
import com.camel.core.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @author baily */
public abstract class BaseCommonController {

    public static final String USER_ID_PROP_NAME = "id";

    /**
     * 获取服务
     * @return
     */
    abstract public IService getiService();

    /**
     * 获取模块名称
     * @return
     */
    abstract public String getMouduleName();

    public Result details(Serializable serializable) {
        Object obj = getiService().selectById(serializable);
        if (ObjectUtils.isEmpty(obj)) {
            return ResultUtil.notFound();
        }
        return ResultUtil.success(obj);
    }

    public Result save(BaseEntity entity) {
        boolean insert = getiService().insert(entity);
        if (insert) {
            return ResultUtil.success("新增" + getMouduleName() + "成功");
        }
        return ResultUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"新增" + getMouduleName() + "失败");
    }

    public Result update(BaseEntity entity) {
        if (getiService().updateById(entity)) {
            return ResultUtil.success("修改" + getMouduleName() + "成功");
        }
        return ResultUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"修改" + getMouduleName() + "失败");
    }

    public Result delete(Serializable serializable) {
        if (getiService().deleteById(serializable)) {
            return ResultUtil.success("删除" + getMouduleName() + "成功");
        }
        return ResultUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"删除" + getMouduleName() + "失败");

    }

    public Result currented(Integer id){
        return getProcessService().current(id, getBusinessKey());
    }

    public Result applyed(Integer id, String flowId) {
        Result result = getProcessService().apply(id, flowId, getBusinessKey()) ? ResultUtil.success("发起流程成功") : ResultUtil.error(HttpStatus.BAD_REQUEST.value(), "发起流程失败");
        return result;
    }

    public Result passed(Integer id, ActivitiForm activitiForm){
        Result result = getProcessService().current(id, getBusinessKey());
        if(!ObjectUtils.isEmpty(result.getData())){
            List<Map<String, Object>> list = (List) result.getData();
            Map<String, Object> userTask = list.get(0);
            if(StringUtils.isEmpty(userTask.get(USER_ID_PROP_NAME))){
                return ResultUtil.success("审批失败");
            }
            Result r = getProcessService().pass(userTask.get(USER_ID_PROP_NAME).toString(), activitiForm, getBusinessKey());
            return ResultUtil.success("审批成功", ((List) result.getData()).get(0));
        }
        return ResultUtil.success("审批失败");
    }

    public Result commen(String id) {
        return getProcessService().comment(id);
    }

    public Result backed(String taskId, ActivitiForm activitiForm) {
        Result result = getProcessService().back(taskId, activitiForm, getBusinessKey());
        return result;
    }

    public Result todoies(){
        return getProcessService().toDO();
    }

    public String getBusinessKey() {
        return "";
    }




    public BaseProcessService getProcessService(){
        return null;
    }
}
