package com.camel.oa.controller;


import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.toolkit.MapUtils;
import com.camel.common.entity.Member;
import com.camel.core.BaseProcessService;
import com.camel.core.config.ProcessProperties;
import com.camel.core.controller.BaseCommonController;
import com.camel.core.entity.Result;
import com.camel.core.entity.process.ActivitiForm;
import com.camel.core.utils.ResultUtil;
import com.camel.oa.enums.ErrandStatus;
import com.camel.oa.enums.ReimbursementStatus;
import com.camel.oa.model.Errand;
import com.camel.oa.service.ErrandService;
import com.camel.oa.service.impl.BaseProcessServiceImpl;
import com.camel.redis.utils.SessionContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

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
 * <出差管理-控制器>
 * @author baily
 * @since 1.0
 * @date 2019/7/8
 **/
@RestController
@RequestMapping("/errand")
public class ErrandController extends BaseCommonController {

    public static final String TASK_DIRECTOR = "部门负责人";
    public static final String TASK_LEADER = "分管领导意见";
    public static final String TASK_OPERATION = "运营管理部确认";
    public static final String TASK_RESOURCES = "人力资源部核准";
    public static final String TASK_MANAGER = "总经理意见";

    @Autowired
    public ErrandService errandService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    public BaseProcessServiceImpl baseProcessService;

    @GetMapping
    public Result index(Errand errand) {
        return ResultUtil.success(errandService.selectPage(errand));
    }

    @PostMapping
    public Result save(@RequestBody Errand errand) {
        errand.setEno(UUID.randomUUID().toString());
        return super.save(errand);
    }

    @GetMapping("/apply/{id}")
    public Result apply(@PathVariable Integer id, String flowId) {
        Result result = super.applyed(id, flowId);
        Errand entity = new Errand(id, ReimbursementStatus.APPLY.getValue());
        errandService.updateById(entity);
        return result;
    }

    @GetMapping("/current/{id}")
    public Result current(@PathVariable Integer id) {
        return super.currented(id);
    }

    @GetMapping("/status")
    public Result status() {
        return ResultUtil.success(ErrandStatus.all());
    }

    @GetMapping("/pass/{id}")
    public Result pass(@PathVariable Integer id, ActivitiForm activitiForm) {
        Result result = super.passed(id, activitiForm);
        HashMap<String, Object> rMapData = (HashMap<String, Object>) result.getData();
        Errand errand = new Errand(id);
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object o = SessionContextUtils.getInstance().currentUser(redisTemplate, username);
        Member member = (Member) o;
        if (MapUtils.isNotEmpty(rMapData)) {
            if ((Boolean) rMapData.get(ProcessProperties.PROCESS_ISEND_KEY)) {
                errand.setStatus(ErrandStatus.APPLY_SUCCESS.getValue());
            }
            if (!StringUtils.isEmpty(rMapData.get("name")) && rMapData.get("name").equals(TASK_DIRECTOR)) {
                errand.setDirector(member.getId());
                errand.setDirectorComment(activitiForm.getComment());
            }
            if (!StringUtils.isEmpty(rMapData.get("name")) && rMapData.get("name").equals(TASK_LEADER)) {
                errand.setLeader(member.getId());
                errand.setLeaderComment(activitiForm.getComment());
            }
            if (!StringUtils.isEmpty(rMapData.get("name")) && rMapData.get("name").equals(TASK_OPERATION)) {
                errand.setOperator(member.getId());
                errand.setOperatorComment(activitiForm.getComment());
            }
            if (!StringUtils.isEmpty(rMapData.get("name")) && rMapData.get("name").equals(TASK_RESOURCES)) {
                errand.setResources(member.getId());
                errand.setResourcesComment(activitiForm.getComment());
            }
            if (!StringUtils.isEmpty(rMapData.get("name")) && rMapData.get("name").equals(TASK_MANAGER)) {
                errand.setManager(member.getId());
                errand.setManagerComment(activitiForm.getComment());
            }
            errandService.updateById(errand);
        }
        return result;
    }

    @GetMapping("/back/{id}")
    public Result back(@PathVariable String id, ActivitiForm activitiForm) {
        Result result = super.backed(id, activitiForm);
        if (!ObjectUtils.isEmpty(result) && result.isSuccess()) {
            errandService.updateById(new Errand(Integer.parseInt(activitiForm.getBusinessId()), ReimbursementStatus.APPLY_FAILD.getValue()));
        }
        return result;
    }

    @Override
    public IService getiService() {
        return errandService;
    }

    @Override
    public String getMouduleName() {
        return "出差";
    }

    @Override
    public BaseProcessService getProcessService() {
        return baseProcessService;
    }

    @Override
    public String getBusinessKey() {
        return Errand.class.getSimpleName().toUpperCase();
    }
}

