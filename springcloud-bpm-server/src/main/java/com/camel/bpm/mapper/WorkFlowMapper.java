package com.camel.bpm.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.camel.bpm.model.WorkFlow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-05-16
 */
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
