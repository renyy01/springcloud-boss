package com.camel.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.camel.system.enums.RoleStatus;
import com.camel.system.mapper.SysRoleMapper;
import com.camel.system.model.SysRole;
import com.camel.system.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.List;

/**
 <p>
 服务实现类
 </p>
 @author ${author}
 @since 2019-04-19 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper mapper;

    @Override
    public PageInfo<SysRole> pageQuery(SysRole entity) {
        entity.setStatus(RoleStatus.NORMAL.getCode());
        PageInfo pageInfo = PageHelper.startPage(entity.getPageNum(), entity.getPageSize()).doSelectPageInfo(() -> mapper.list(entity));
        return pageInfo;
    }

    @Override
    public List<SysRole> loadRolesByRoleIds(List<Integer> ids) {
        Wrapper<SysRole> roleWrapper = new EntityWrapper<>();
        roleWrapper.in("role_id", ids);
        return mapper.selectList(roleWrapper);
    }

    @Override
    public boolean exist(String name, Integer id) {
        Wrapper<SysRole> roleWrapper = new EntityWrapper<>();
        roleWrapper.eq("role_name", name);
        if (!ObjectUtils.isEmpty(id)) {
            roleWrapper.notIn("role_id", id);
        }
        Integer count = mapper.selectCount(roleWrapper);
        return count <= 0;
    }

    @Override
    public boolean delete(Serializable serializable) {
        SysRole sysRole = new SysRole((Integer) serializable, RoleStatus.UNVALID.getCode());
        return mapper.updateById(sysRole) > -1;
    }
}
