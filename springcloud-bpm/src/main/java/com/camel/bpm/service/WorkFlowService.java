package com.camel.bpm.service;

import com.baomidou.mybatisplus.service.IService;
import com.camel.bpm.model.WorkFlow;
import com.github.pagehelper.PageInfo;
import org.activiti.engine.repository.Deployment;

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
 * <工作流服务>
 * @author baily
 * @since 1.0
 * @date 2019/7/7
 **/
public interface WorkFlowService extends IService<WorkFlow> {
    /**
     * 工作流分页查询
     * @param entity
     * @return
     */
    PageInfo<WorkFlow> pageQuery(WorkFlow entity);

    /**
     * 已部署流程分页查询
     * @param entity
     * @return
     */
    PageInfo<Deployment> pageQueryDeployed(WorkFlow entity);

    /**
     * 已发布流程集合
     * @param workFlow
     * @return
     */
    List<Map<String, Object>> deployed(WorkFlow workFlow);
}
