package com.camel.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.camel.system.model.SysLog;
import com.github.pagehelper.PageInfo;

/**
 <p>
 服务类
 </p>
 @author ${author}
 @since 2019-05-09 */
public interface SysLogService extends IService<SysLog> {
    /**
     * 分页查询
     * @param entity
     * @return
     */
    PageInfo<SysLog> pageQuery(SysLog entity);
}
