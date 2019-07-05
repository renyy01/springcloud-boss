package com.camel.bpm.controller;


import com.baomidou.mybatisplus.service.IService;
import com.camel.bpm.enums.WorkFlowStatus;
import com.camel.bpm.enums.WorkFlowType;
import com.camel.bpm.exceptions.UnAuthenticationException;
import com.camel.bpm.model.WorkFlow;
import com.camel.bpm.service.WorkFlowService;
import com.camel.core.controller.BaseCommonController;
import com.camel.core.entity.Result;
import com.camel.core.enums.ResultEnum;
import com.camel.core.utils.ResultUtil;
import com.camel.redis.entity.RedisUser;
import com.camel.redis.utils.SerizlizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Principal;

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
 * <工作流 基础控制器>
 * @author baily
 * @since 1.0
 * @date 2019/7/5
 **/
@RestController
@RequestMapping("/workFlow")
public class WorkFlowController extends BaseCommonController {

    @Autowired
    private WorkFlowService service;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public Result index(WorkFlow entity) {
        entity.setStatus(WorkFlowStatus.CREATED.getValue());
        return ResultUtil.success(service.pageQuery(entity));
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        return ResultUtil.success(service.selectById(id));
    }

    /**
     * describe: 新建流程
     * creat_user: baily
     * creat_date: 2018/8/17
     **/
    @PostMapping
    public Result create(@RequestBody WorkFlow workFlow, Principal principal) throws UnAuthenticationException {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        byte[] cu = (byte[]) operations.get("CURRENT_USER");
        RedisUser currentUser = (RedisUser) SerizlizeUtil.unserizlize(cu);
        if (ObjectUtils.isEmpty(currentUser)) {
            throw new UnAuthenticationException("当前用户不存在");
        }
        workFlow.setCreator(currentUser.getId());
        WorkFlow.readKey(workFlow);
        if (service.insert(workFlow)) {
            return ResultUtil.success("新建流程成功");
        } else {
            return ResultUtil.error(ResultEnum.BAD_REQUEST.getCode(), "新建流程失败，请检查后重试");
        }
    }

    /**
     * describe: 修改更新工作流
     * creat_user: baily
     * creat_date: 2018/8/18
     **/
    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody WorkFlow workFlow) {
        workFlow.setId(id);
        if (service.updateById(workFlow)) {
            return ResultUtil.updateSuccess(getMouduleName());
        } else {
            return ResultUtil.updateError(getMouduleName());
        }
    }

    /**
     * describe: 删除流程
     * creat_user: baily
     * creat_date: 2018/8/19
     **/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        if (service.deleteById(id)) {
            return ResultUtil.deleteSuccess(getMouduleName());
        } else {
            return ResultUtil.deleteError(getMouduleName());
        }
    }


    /**
     * describe: 已发布流程
     * creat_user: baily
     * creat_date: 2018/8/19
     **/
    @GetMapping("/deployed")
    public Result deployed(WorkFlow workFlow) {
        return ResultUtil.success(service.deployed(workFlow));
    }

    @Override
    public IService getiService() {
        return service;
    }

    @Override
    public String getMouduleName() {
        return "工作流程";
    }

    /**
     * describe: 菜单级别
     * creat_user: baily
     * creat_date: 2018/8/19
     **/
    @GetMapping("/typies")
    public Result menuLevel(HttpServletRequest request) {
        return ResultUtil.success(WorkFlowType.all());
    }
}

