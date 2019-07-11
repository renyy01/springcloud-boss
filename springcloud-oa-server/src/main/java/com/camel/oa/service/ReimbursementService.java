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
}
