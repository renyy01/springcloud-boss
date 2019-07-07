package com.camel.bpm.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.camel.bpm.model.WorkFlow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
 * <工作流基础MAPPER>
 * @author baily
 * @since 1.0
 * @date 2019/7/7
 **/
@Mapper
@Repository
public interface WorkFlowMapper extends BaseMapper<WorkFlow> {
    /**
     * 查询工作流列表
     * @param workFlow
     * @return
     */
    List<WorkFlow> list(WorkFlow workFlow);
}
