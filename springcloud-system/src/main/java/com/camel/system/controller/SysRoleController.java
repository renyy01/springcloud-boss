package com.camel.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.camel.core.controller.BaseCommonController;
import com.camel.core.entity.Result;
import com.camel.core.utils.ResultUtil;
import com.camel.system.model.SysRole;
import com.camel.system.service.SysRoleService;
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
 * <角色注释>
 * @author baily
 * @since 1.0
 * @date 2019/7/5
 **/
@RestController
@RequestMapping(("/sysRole"))
public class SysRoleController extends BaseCommonController {

    @Autowired
    private SysRoleService service;

    @Override
    public IService getiService() {
        return service;
    }

    @Override
    public String getMouduleName() {
        return "角色";
    }

    @GetMapping
    public Result index(SysRole sysUser){
        return ResultUtil.success(service.pageQuery(sysUser));
    }

    @PostMapping
    public Result save(@RequestBody SysRole sysRole){
        return super.save(sysRole);
    }

    @PutMapping
    public Result update(@RequestBody SysRole sysRole){
        return super.update(sysRole);
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable(required = true) Integer id){
        return super.details(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(required = true) Integer id){
        if(service.delete(id)){
            return ResultUtil.deleteSuccess(getMouduleName());
        }else{
            return ResultUtil.deleteError(getMouduleName());
        }
    }

    @GetMapping("/valid/{name}")
    public Result nameValid(@PathVariable String name, Integer id){
        return ResultUtil.success(service.exist(name, id));
    }

    @GetMapping("/all/list")
    public Result all(){
        System.out.println(service.selectList(new EntityWrapper<>()));
        return ResultUtil.success(service.selectList(new EntityWrapper<>()));
    }
}

