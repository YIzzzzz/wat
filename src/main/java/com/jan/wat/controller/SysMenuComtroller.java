package com.jan.wat.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
/**
 * wsk
 */
@RestController
@CrossOrigin
@RequestMapping("/sys/menu")
public class SysMenuComtroller {

    @Autowired
    ISysMenuService iSysMenuService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public Page<SysMenu> getAllSysMenuPage(@PathVariable long current, @PathVariable long size)
    {
        Page<SysMenu> page = new Page<>(current, size);
        return iSysMenuService.page(page);
    }

    @ApiOperation(value = "查询菜单导航")
    @GetMapping("getall")
    public List<SysMenu> getAllSysmenu(){
        return iSysMenuService.list();
    }

    @ApiOperation(value = "添加菜单导航")
    @PostMapping("/add")
    public RespBean addSysMenu(@RequestBody SysMenu sysMenu){
        if(iSysMenuService.save(sysMenu)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更改菜单导航信息")
    @PutMapping("/update")
    public RespBean updateSysMenu(@RequestBody SysMenu sysMenu){
        if (iSysMenuService.updateById(sysMenu)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新菜单导航信息")
    @PutMapping("/updatebatch")
    public RespBean updateBatchSysMenu(@RequestBody List<SysMenu> sysMenuList){
        if(iSysMenuService.updateBatchById(sysMenuList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除菜单导航信息")
    @DeleteMapping("/{id}")
    public RespBean deleteSysMenu(@PathVariable String id){
        if (iSysMenuService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除菜单导航信息")
    @DeleteMapping("/delete")
    public RespBean deleteSysMenuByIds(@RequestBody String[] ids){
        if (iSysMenuService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
