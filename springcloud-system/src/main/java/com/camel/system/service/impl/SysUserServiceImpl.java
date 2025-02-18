package com.camel.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.camel.system.mapper.SysUserMapper;
import com.camel.system.mapper.SysUserRoleMapper;
import com.camel.system.model.SysRole;
import com.camel.system.model.SysUser;
import com.camel.system.model.SysUserRole;
import com.camel.system.service.SysRoleService;
import com.camel.system.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-04-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper mapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysRoleService roleService;

    @Override
    public PageInfo<SysUser> pageQuery(SysUser entity) {
        PageInfo pageInfo = PageHelper.startPage(entity.getPageNum(), entity.getPageSize()).doSelectPageInfo(()-> mapper.list(entity));
        return pageInfo;
    }

    @Override
    public boolean exist(String name, Integer id) {
        Wrapper<SysUser> userWrapper = new EntityWrapper<>();
        userWrapper.eq("username", name);
        if(!ObjectUtils.isEmpty(id)){
            userWrapper.notIn("uid", id);
        }

        Integer count = mapper.selectCount(userWrapper);
        return count <= 0;
    }

    @Override
    public void getRolesByUser(SysUser user) {
        Wrapper<SysUserRole> sysUserRoleWrapper = new EntityWrapper<>();
        sysUserRoleWrapper.eq("user_id", user.getUid());
        List<SysUserRole> userRoleList = userRoleMapper.selectList(sysUserRoleWrapper);
        List<Integer> roleIds = new ArrayList<>();
        List<SysRole> roles = new ArrayList<>();
        userRoleList.forEach(userRole -> {
            roleIds.add(userRole.getRoleId());
        });
        user.setRoleIds(roleIds);
        user.setSysRoles(roleService.loadRolesByRoleIds(roleIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class )
    public boolean addRoles(SysUser user) {
        Wrapper<SysUserRole> userRoleWrapper = new EntityWrapper<>();
        userRoleWrapper.eq("user_id", user.getUid());

        userRoleMapper.delete(userRoleWrapper);
        removeRepeat(user.getRoleIds()).forEach(roleId -> {
            userRoleMapper.insert(new SysUserRole(user.getUid(), (Integer) roleId));
        });
        return true;
    }

    public List removeRepeat(List list){
        LinkedHashSet lhs = new LinkedHashSet();
        lhs.addAll(list);
        list.clear();
        list.addAll(lhs);
        return list;
    }

    @Override
    public List<SysUser> all(SysUser user) {
        Wrapper<SysUser> userWrapper = new EntityWrapper<>();
        return mapper.selectList(userWrapper);
    }
}
