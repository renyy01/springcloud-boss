package com.camel.common.utils;

import com.camel.common.entity.BasePaginationEntity;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/** @author baily */
public class PaginationUtil {

    public static PageInfo<BasePaginationEntity> startPage(BasePaginationEntity entity, ISelect iSelect){
        return PageHelper.startPage(entity.getPageNum(), entity.getPageSize())
                .doSelectPageInfo(iSelect);
    }
}
