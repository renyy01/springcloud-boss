package com.camel.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.camel.common.utils.PaginationUtil;
import com.camel.system.enums.MenuStatus;
import com.camel.system.enums.MenuType;
import com.camel.system.mapper.SysMenuMapper;
import com.camel.system.model.SysMenu;
import com.camel.system.service.SysMenuService;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper mapper;

    @Override
    public List<SysMenu> tops() {
        Wrapper<SysMenu> menuWrapper = new EntityWrapper<>();
        menuWrapper.eq(MenuType.TOP.getColumn(), MenuType.TOP.getCode());
        menuWrapper.eq(MenuStatus.NORMAL.getColumn(), MenuStatus.NORMAL.getCode());
        return mapper.selectList(menuWrapper);
    }

    @Override
    public List<SysMenu> subs() {
        Wrapper<SysMenu> menuWrapper = new EntityWrapper<>();
        menuWrapper.eq(MenuType.SUB.getColumn(), MenuType.SUB.getCode());
        menuWrapper.eq(MenuStatus.NORMAL.getColumn(), MenuStatus.NORMAL.getCode());
        return mapper.selectList(menuWrapper);
    }

    @Override
    public PageInfo<SysMenu> selectPage(SysMenu entity) {
        PageInfo pageInfo = PaginationUtil.startPage(entity, () -> {
            mapper.list(entity);
        });
        return pageInfo;
    }

    @Override
    public boolean exist(String name, Integer id) {
        Wrapper<SysMenu> menuWrapper = new EntityWrapper<>();
        menuWrapper.eq("name", name);
        if(!ObjectUtils.isEmpty(id)){
            menuWrapper.notIn("menu_id", id);
        }
        Integer count = mapper.selectCount(menuWrapper);
        return count <= 0;
    }

    @Override
    public boolean delete(Serializable serializable) {
        SysMenu sysMenu = new SysMenu((Integer) serializable, "0");
        return mapper.updateById(sysMenu) > -1;
    }
}
