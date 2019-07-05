package com.camel.common.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.camel.common.enums.ProcessStatus;

/**
 @author baily */
public class BaseProcessPaginationEntity extends BasePaginationEntity {
    @TableField(exist = false)
    public ProcessStatus processStatus;
}
