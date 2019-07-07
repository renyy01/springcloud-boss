package com.camel.oa.controller;


import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.toolkit.MapUtils;
import com.camel.core.config.ProcessProperties;
import com.camel.core.controller.BaseCommonController;
import com.camel.core.entity.Result;
import com.camel.core.entity.process.ActivitiForm;
import com.camel.core.utils.ResultUtil;
import com.camel.oa.enums.ReimbursementStatus;
import com.camel.oa.model.Reimbursement;
import com.camel.oa.service.ReimbursementService;
import com.camel.redis.entity.RedisUser;
import com.camel.redis.utils.SessionContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 <p>
 前端控制器
 </p>
 @author ${author}
 @since 2019-05-17 */
@RestController
@RequestMapping("/reimbursement")
public class ReimbursementController extends BaseCommonController {
    public static final String USER_ID_PROP_NAME = "id";

    @Autowired
    private ReimbursementService service;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public Result index(Reimbursement reimbursement) {
        return ResultUtil.success(service.selectPage(reimbursement));
    }

    @GetMapping("/{id}")
    public Result ditail(@PathVariable Integer id) {
        return super.details(id);
    }

    @PostMapping
    public Result save(@RequestBody Reimbursement reimbursement) {
        RedisUser currentUser = SessionContextUtils.getInstance().currentUser(redisTemplate);
        reimbursement.setCreator(currentUser.getId());
        return super.save(reimbursement);
    }

    @PutMapping
    public Result update(@RequestBody Reimbursement reimbursement) {
        return super.update(reimbursement);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return super.delete(id);
    }

    @GetMapping("/apply/{id}")
    public Result apply(@PathVariable Integer id, String flowId) {
        Result result = service.apply(id, flowId) ? ResultUtil.success("发起流程成功") : ResultUtil.error(HttpStatus.BAD_REQUEST.value(), "发起流程失败");
        Reimbursement reimbursement = new Reimbursement(id, ReimbursementStatus.APPLY.getValue());
        service.updateById(reimbursement);
        return result;
    }

    @GetMapping("/current/{id}")
    public Result current(@PathVariable Integer id){
        return service.current(id);
    }

    @GetMapping("/comment/{id}")
    public Result comment(@PathVariable String id){
        Result result = service.comment(id);
        return result;
    }

    @GetMapping("/pass/{id}")
    public Result pass(@PathVariable Integer id, ActivitiForm activitiForm){
        Result result = service.current(id);
        if(!ObjectUtils.isEmpty(result.getData())){
            List<Map<String, Object>> list = (List) result.getData();
            Map<String, Object> userTask = list.get(0);
            if(StringUtils.isEmpty(userTask.get(USER_ID_PROP_NAME))){
                return ResultUtil.success("审批失败");
            }
            Result r = service.pass(userTask.get(USER_ID_PROP_NAME).toString(), activitiForm);
            HashMap<String, Object> rMapData = (HashMap<String, Object>) r.getData();
            if(MapUtils.isNotEmpty(rMapData) && (boolean)rMapData.get(ProcessProperties.PROCESS_ISEND_KEY)){
                service.updateById(new Reimbursement(id, ReimbursementStatus.APPLY_SUCCESS.getValue()));
            }
            return ResultUtil.success("审批成功", result.getData());
        }
        return ResultUtil.success("审批失败");
    }

    @GetMapping("/back/{id}")
    public Result back(@PathVariable String id, ActivitiForm activitiForm) {
        Result result = service.back(id, activitiForm);
        if(!ObjectUtils.isEmpty(result) && result.isSuccess()) {
            service.updateById(new Reimbursement(Integer.parseInt(activitiForm.getBusinessId()), ReimbursementStatus.APPLY_FAILD.getValue()));
        }
        return result;
    }

    @GetMapping("/todo")
    public Result toDo(){
        Result result = service.toDO();
        return result;
    }

    @Override
    public IService getiService() {
        return service;
    }

    @Override
    public String getMouduleName() {
        return "报销";
    }

    @GetMapping("/status")
    public Result reimbursementStatus() {
        return ResultUtil.success(ReimbursementStatus.all());
    }
}

