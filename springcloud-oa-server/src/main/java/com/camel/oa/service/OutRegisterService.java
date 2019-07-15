package com.camel.oa.service;

import com.baomidou.mybatisplus.service.IService;
import com.camel.oa.model.OutRegister;
import com.github.pagehelper.PageInfo;

public interface OutRegisterService extends IService<OutRegister> {

    //创建外出登记信息
    int insertSelective(OutRegister outRegister);

    //修改外出登记信息
    int updateByPrimaryKeySelective(OutRegister outRegister);

    //逻辑删除或批量删除
    int deleteById(Integer id);

    //查看外出登记详情信息
    OutRegister selectByPrimaryKey(Integer id);

    //分页查询外出登记信息
    PageInfo<OutRegister> selectPage(OutRegister outRegister);
}
