package com.camel.auth.dao;

import com.camel.auth.model.SysMenu;

import java.util.List;

/**
 * 〈权限Dao〉
 *
 * @author Curise
 * @create 2018/12/13
 * @since 1.0.0
 */
public interface SysMenuDao {

    /**
     * 根据角色id查找权限列表
     * @param roleId 角色id
     * @return 权限列表
     */
    List<SysMenu> findByRoleId(Integer roleId);
}
