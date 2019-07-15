package com.camel.oa.controller;

import com.baomidou.mybatisplus.service.IService;
import com.camel.common.entity.Member;
import com.camel.common.utils.PoiExcelUtil;
import com.camel.core.BaseProcessService;
import com.camel.core.controller.BaseCommonController;
import com.camel.core.entity.Result;
import com.camel.core.utils.ResultUtil;
import com.camel.oa.model.OutRegister;
import com.camel.oa.service.OutRegisterService;
import com.camel.redis.utils.SessionContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author renyy
 * @version 1.0
 * @Title: OutRegisterController.java
 * @Description: TODO
 * @time 2019/7/15 9:21
 */
@RestController
@RequestMapping("/outRegister")
public class OutRegisterController extends BaseCommonController {

    private static final Logger logger = LoggerFactory.getLogger(OutRegisterController.class);

    @Autowired
    private OutRegisterService outRegisterService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BaseProcessService baseProcessService;

    @Override
    public IService getiService() {
        return outRegisterService;
    }

    @Override
    public String getMouduleName() {
        return "外出登记";
    }

    @Override
    public BaseProcessService getProcessService() {
        return baseProcessService;
    }

    /**
     * @MethodName outList
     * @Description //根据条件查询某部门下参与登记注册的人
     * @Author renyy
     * @Date 2019/7/15 9:45
     * @Param [outRegister]
     * @return com.camel.core.entity.Result
     */
    @GetMapping
    @ResponseBody
    public Result outList(OutRegister outRegister){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //拿到当前用户的部门负责人，如果是同一个人则
        if (username.equals(outRegister.getOrgDirector())){
            return ResultUtil.success(outRegisterService.selectPage(outRegister));
        }else{
            outRegister.setUserName(username);
            return ResultUtil.success(outRegisterService.selectPage(outRegister));
        }
    }

    /**
     * @MethodName outList
     * @Description //根据条件查询某部门下参与登记注册的人并导出
     * @Author renyy
     * @Date 2019/7/15 9:45
     * @Param [outRegister]
     * @return com.camel.core.entity.Result
     */
    @GetMapping
    @ResponseBody
    public void outListExcel(OutRegister outRegister){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //拿到当前用户的部门负责人，如果是同一个人则
        if (username.equals(outRegister.getOrgDirector())){
           List<OutRegister> list = null;
           list.add(outRegister);
            if(null!=list && list.size()>0){
                String[] titles=new String[]{"日期","外出时间","回公司时间","姓名","部门","地点","外出事由","部门负责人签字"};
                String[] attrs=new String[]{"pPlanName","whName","pStartDateStr","createTime"};

                HSSFWorkbook wb = PoiExcelUtil.createWorkbook("公司员工外出登记表（办公地/部门：           ）", titles,attrs, list,DateOperator.FORMAT_STR_WITH_TIME_S);
                PoiExcelUtil.write(wb, resp, "公司员工外出登记表");
            }
        }else{
            outRegister.setUserName(username);
            List<OutRegister> list = null;
            list.add(outRegister);
            if(null!=list && list.size()>0){
                String[] titles=new String[]{"盘点计划名称","库房","计划盘点时间","计划创建时间"};
                String[] attrs=new String[]{"pPlanName","whName","pStartDateStr","createTime"};

                HSSFWorkbook wb = PoiExcelUtil.createWorkbook("盘点计划列表信息", titles,attrs, list,DateOperator.FORMAT_STR_WITH_TIME_S);
                PoiExcelUtil.write(wb, resp, "盘点计划列表信息");
            }
        }
    }

    /**
     * @MethodName createOutRegister
     * @Description 创建外出登记信息
     * @Author renyy
     * @Date 2019/7/15 11:16
     * @Param [outRegister]
     * @return com.camel.core.entity.Result
     */
    @RequestMapping("/createOutRegister")
    @ResponseBody
    public Result createOutRegister(OutRegister outRegister) {
        Result result = new Result();
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = (Member) SessionContextUtils.getInstance().currentUser(redisTemplate, username);
        try {
            outRegister.setUid(member.getId());
            outRegister.setUserName(username);
            //暂时获取不到部门信息，先写一个定值
            outRegister.setOrgId(2);
            outRegister.setOrgName("开发实施服务部");
            outRegister.setOrgDirector("许桦");
            outRegister.setStatus("0");
            outRegister.setValidFlag("1");
            int i = outRegisterService.insertSelective(outRegister);
            if (i > 0) {
                result.setMsg("创建成功！");
                result.setSuccess(true);
                return result;
            } else {
                result.setMsg("创建失败！");
                result.setSuccess(false);
                return result;
            }
        } catch (RuntimeException e) {
            logger.error("操作失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * @MethodName editOutRegister
     * @Description 修改外出登记信息
     * @Author renyy
     * @Date 2019/7/15 11:39
     * @Param [outRegister, request]
     * @return com.camel.core.entity.Result
     */
    @RequestMapping("/editOutRegister")
    @ResponseBody
    public Result editOutRegister(OutRegister outRegister, HttpServletRequest request){
        Result result = new Result();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String id = request.getParameter("id");
//        String orgId = request.getParameter("orgId");
//        String outStart = request.getParameter("outStart");
//        String outInterval = request.getParameter("utInterval");
//        String outEnd = request.getParameter("outEnd");
//        String userName = request.getParameter("userName");
//        String outPlace = request.getParameter("outPlace");
//        String outReason = request.getParameter("outReason");
//        String status = request.getParameter("status");
        try {
            if (StringUtils.isNotBlank(String.valueOf(outRegister.getId()))) {
//                outRegister.setId(Integer.valueOf(id));
//                outRegister.setOrgId(Integer.valueOf(id));
//                outRegister.setOutStart(sdf.parse(outStart));
//                outRegister.setOutInterval(Integer.valueOf(outInterval));
//                outRegister.setOutEnd(sdf.parse(outEnd));
//                outRegister.setUserName(userName);
//                outRegister.setOutPlace(outPlace);
//                outRegister.setOutReason(outReason);
//                outRegister.setStatus(status);
                this.outRegisterService.updateByPrimaryKeySelective(outRegister);
                result.setMsg("修改成功！");
                result.setSuccess(true);
            }else {
                result.setMsg("修改失败！");
                result.setSuccess(true);
            }
            return result;
        } catch (Exception e) {
            logger.error("操作失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * @MethodName deleteByOutRegisterIds
     * @Description 批量删除外出登记信息
     * @Author renyy
     * @Date 2019/7/15 13:39
     * @Param [req]
     * @return com.camel.core.entity.Result
     */
    @RequestMapping("/deleteByOutRegisterIds")
    @ResponseBody
    public Result deleteByOutRegisterIds(HttpServletRequest req) {
        Result result = new Result();
        String ids = req.getParameter("ids");
        try {
            if (StringUtils.isNoneBlank(ids)) {
                String[] arr = ids.split(",");
                for (String id : arr) {
                   outRegisterService.deleteById(Integer.valueOf(id));
                }
                result.setMsg("删除成功！");
                result.setSuccess(true);
            }else {
                result.setMsg("删除失败！");
                result.setSuccess(false);
            }
        } catch (RuntimeException e) {
            logger.error("删除失败：{}", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * @MethodName deleteById
     * @Description 删除外出登记信息
     * @Author renyy
     * @Date 2019/7/15 13:40
     * @Param [id]
     * @return com.camel.core.entity.Result
     */
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id) {
        Result result = new Result();
        try {
            outRegisterService.deleteById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
        } catch (RuntimeException e) {
            logger.error("删除失败：{}", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * @MethodName selectById
     * @Description 查看外出登记详情信息
     * @Author renyy
     * @Date 2019/7/15 14:29
     * @Param [id]
     * @return com.camel.core.entity.Result
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Result result = new Result();
        OutRegister outRegister = outRegisterService.selectByPrimaryKey(id);
        result.setData(outRegister);
        return result;
    }
}
