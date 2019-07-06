package com.camel.auth.dao;


import com.camel.auth.model.SysRole;

import java.util.List;

/**
 * 〈角色Dao〉
 *
 * @author Curise
 * @create 2018/12/13
 * @since 1.0.0
 */
public interface SysRoleDao {

    /**
     * 根据用户id查找角色列表
     * @param memberId 用户id
     * @return 角色列表
     */
    List<SysRole> findByUserId(Integer memberId);
}
