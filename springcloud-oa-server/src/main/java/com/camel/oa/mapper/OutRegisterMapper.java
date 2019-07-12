package com.camel.oa.mapper;

import com.camel.oa.model.OutRegister;
import java.util.List;

public interface OutRegisterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OutRegister record);

    int insertSelective(OutRegister record);

    OutRegister selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OutRegister record);

    int updateByPrimaryKey(OutRegister record);

    List<OutRegister> outList(OutRegister outRegister);
}