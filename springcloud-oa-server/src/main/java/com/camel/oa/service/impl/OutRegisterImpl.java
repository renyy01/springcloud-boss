package com.camel.oa.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.camel.core.utils.PaginationUtil;
import com.camel.oa.mapper.OutRegisterMapper;
import com.camel.oa.model.OutRegister;
import com.camel.oa.service.OutRegisterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author renyy
 * @version 1.0
 * @Title: OutRegisterImpl.java
 * @Description: TODO
 * @time 2019/7/15 9:08
 */
@Service
public class OutRegisterImpl extends ServiceImpl<OutRegisterMapper, OutRegister> implements OutRegisterService {

    @Autowired
    private OutRegisterMapper outRegisterMapper;

    //创建外出登记信息
    @Override
    public int insertSelective(OutRegister outRegister) {
        return outRegisterMapper.insertSelective(outRegister);
    }

    //修改外出登记信息
    @Override
    public int updateByPrimaryKeySelective(OutRegister outRegister) {
        return outRegisterMapper.updateByPrimaryKeySelective(outRegister);
    }

    //逻辑删除或批量删除
    @Override
    public int deleteById(Integer id) {
        return outRegisterMapper.deleteById(id);
    }

    //查看外出登记详情信息
    @Override
    public OutRegister selectByPrimaryKey(Integer id) {
        return outRegisterMapper.selectByPrimaryKey(id);
    }

    //分页查询外出登记信息
    @Override
    public PageInfo<OutRegister> selectPage(OutRegister outRegister) {
        PageInfo pageInfo = PageHelper.startPage(outRegister.getPageNum(), outRegister.getPageSize()).doSelectPageInfo(()-> outRegisterMapper.outList(outRegister));
        return pageInfo;
    }
}
