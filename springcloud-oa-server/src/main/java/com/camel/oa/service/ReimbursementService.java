package com.camel.oa.service;

import com.baomidou.mybatisplus.service.IService;
import com.camel.core.entity.Result;
import com.camel.core.entity.process.ActivitiForm;
import com.camel.oa.model.Reimbursement;
import com.github.pagehelper.PageInfo;

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
 * <报销服务类>
 * @author baily
 * @since 1.0
 * @date 2019/7/7
 **/
public interface ReimbursementService extends IService<Reimbursement> {
    /**
     分页查询报销信息
     @param entity
     @return
     */
    PageInfo<Reimbursement> selectPage(Reimbursement entity);

    /**
     发起申请
     @param id
     @param flowId
     @return
     */
    Boolean apply(Integer id, String flowId);

    /**
     查询当前流程
     @param id
     @return
     */
    Result current(Integer id);

    /**
     查询当前流程
     @param id
     @return
     */
    Result current(String id);

    /**
     * 通过
     * @param id
     * @param activitiForm
     * @return
     */
    Result pass(String id, ActivitiForm activitiForm);

    /**
     * 驳回
     * @param id
     * @param activitiForm
     * @return
     */
    Result back(String id, ActivitiForm activitiForm);

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
