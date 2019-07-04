package com.camel.core.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.camel.core.enums.ProcessStatus;

/**
 @author baily */
public class BaseProcessPaginationEntity extends BasePaginationEntity {
    @TableField(exist = false)
    public ProcessStatus processStatus;
}
