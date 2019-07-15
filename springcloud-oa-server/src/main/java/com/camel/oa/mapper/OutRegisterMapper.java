package com.camel.oa.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.camel.oa.model.OutRegister;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OutRegisterMapper extends BaseMapper<OutRegister> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(OutRegister record);

    OutRegister selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OutRegister record);

    int updateByPrimaryKey(OutRegister record);

    List<OutRegister> outList(OutRegister outRegister);

    int deleteById (Integer id);
}