package com.jan.wat.controller;

import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.SysOrganize;
import com.jan.wat.pojo.SysRole;
import com.jan.wat.pojo.WatClassify;
import com.jan.wat.service.ISysOrganizeService;
import com.jan.wat.service.ISysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    ISysRoleService iSysRoleService;

    @ApiOperation(value = "查询角色管理列表")
    @GetMapping("/getall")
    public List<SysRole> getAllSysRole(){
        return iSysRoleService.list();
    }

    @ApiOperation(value = "添加角色管理信息")
    @PostMapping("/add")
    public RespBean addSysRole(@RequestBody SysRole sysRole){
        if (iSysRoleService.save(sysRole)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新角色管理信息")
    @PutMapping("/update")
    public RespBean updateSysRole(@RequestBody SysRole sysRole){
        if (iSysRoleService.updateById(sysRole)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新角色管理信息")
    @PutMapping("updatebatch")
    public RespBean updateBatchSysRole(@RequestBody List<SysRole> sysRoleList){
        if(iSysRoleService.updateBatchById(sysRoleList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除角色管理信息")
    @DeleteMapping("/{rolecode}")
    public RespBean deleteSysRole(@PathVariable String rolecode){
        if (iSysRoleService.removeById(rolecode)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除角色管理信息")
    @DeleteMapping("/delete")
    public RespBean deleteSysRoleByIds(@RequestBody Integer[] ids){
        if (iSysRoleService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
