package com.camel.system.controller;


import com.baomidou.mybatisplus.service.IService;
import com.camel.core.controller.BaseCommonController;
import com.camel.core.entity.Result;
import com.camel.core.utils.ResultUtil;
import com.camel.system.enums.MenuStatus;
import com.camel.system.enums.MenuType;
import com.camel.system.model.SysMenu;
import com.camel.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 *                 ___====-_  _-====___
 *           _--^^^#####//      \\#####^^^--_
 *        _-^##########// (    ) \\##########^-_
 *       -############//  |\^^/|  \\############-
 *     _/############//   (@::@)   \\############\_
 *    /#############((     \\//     ))#############\
 *   -###############\\    (oo)    //###############-
 *  -#################\\  / VV \  //#################-
 * -###################\\/      \//###################-
 *_#/|##########/\######(   /\   )######/\##########|\#_
 *|/ |#/\#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
 *`  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
 *   `   `  `      `   / | |  | | \   '      '  '   '
 *                    (  | |  | |  )
 *                   __\ | |  | | /__
 *                  (vvv(VVV)(VVV)vvv)
 * <菜单控制器>
 * @author baily
 * @since 1.0
 * @date 2019/7/5
 **/
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseCommonController {

    @Autowired
    private SysMenuService service;

    @GetMapping
    public Result index(SysMenu sysMenu) {
        sysMenu.setStatus(MenuStatus.NORMAL.getCode().toString());
        return ResultUtil.success(service.selectPage(sysMenu));
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        return super.details(id);
    }

    @PostMapping
    public Result save(@RequestBody SysMenu sysMenu) {
        return super.save(sysMenu);
    }

    @PutMapping
    public Result update(@RequestBody SysMenu sysMenu) {
        return super.update(sysMenu);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        if (service.delete(id)) {
            return ResultUtil.deleteSuccess(getMouduleName());
        } else {
            return ResultUtil.deleteError(getMouduleName());
        }
    }

    @GetMapping("/tops")
    public Result tops(SysMenu sysMenu) {
        return ResultUtil.success(service.tops());
    }

    @GetMapping("/subs")
    public Result subs(SysMenu sysMenu) {
        return ResultUtil.success(service.subs());
    }

    @GetMapping("/typies")
    public Result typies() {
        return ResultUtil.success(MenuType.all());
    }

    @GetMapping("/valid/{name}")
    public Result nameValid(@PathVariable String name, Integer id) {
        return ResultUtil.success(service.exist(name, id));
    }


    @Override
    public IService getiService() {
        return service;
    }

    @Override
    public String getMouduleName() {
        return "菜单";
    }
}

