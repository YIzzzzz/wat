package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.*;
import com.jan.wat.pojo.vo.RoleTree;
import com.jan.wat.service.ISysRoleService;
import com.jan.wat.service.ISysRolemenumapService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    ISysRolemenumapService iSysRolemenumapService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public Page<SysRole> getAllSysRolePage(@PathVariable long current, @PathVariable long size)
    {
        Page<SysRole> page = new Page<>(current, size);
        return iSysRoleService.page(page);
    }

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
    @ResponseBody
    public void updateSysRole(@RequestBody JSONObject json){
        String oldCode = (String)json.get("oldCode");
        System.out.println("----------------------------------------------------------");
        System.out.println(oldCode);

//        if (iSysRoleService.updateById(sysRole)){
//            return RespBean.success("更新成功");
//        }
//        return RespBean.error("更新失败！");
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
    public RespBean deleteSysRoleByIds(@RequestBody String[] ids){
        if (iSysRoleService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "编辑权限")
    @GetMapping("edit/{rolecode}")
    public List<RoleTree> selectByRolecode(@PathVariable String rolecode){
        return iSysRoleService.selectByRolecode(rolecode);
    }

    @ApiOperation(value = "添加删除接口")
    @PostMapping(value="/adddelapi", produces = "application/json;charset=UTF-8")
    public RespBean addAndDelApi(@RequestBody JSONObject json){

        System.out.println(json);
        String role = (String) json.get("role");
        List<String> add = (List<String>) json.get("add");
        List<String> delete = (List<String>) json.get("delete");
        List<SysRolemenumap> list = new ArrayList<>();
        SysRolemenumap sysRolemenumap;
        for (String str : add){
            sysRolemenumap = new SysRolemenumap();
            sysRolemenumap.setRolecode(role);
            sysRolemenumap.setMenucode(str);
            list.add(sysRolemenumap);
        }
        iSysRolemenumapService.saveBatch(list);

        for (String str : delete){
            LambdaQueryWrapper<SysRolemenumap> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysRolemenumap::getRolecode,role);
            wrapper.eq(SysRolemenumap::getMenucode,str);
            iSysRolemenumapService.remove(wrapper);
        }
        return RespBean.success("success");
    }

}
