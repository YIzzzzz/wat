package com.jan.wat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.*;
import com.jan.wat.pojo.vo.RoleQuery;
import com.jan.wat.pojo.vo.RoleTree;
import com.jan.wat.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    @Autowired
    ISysUserrolemapService iSysUserrolemapService;




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
        return iSysRoleService.getallrole();
    }

    @ApiOperation(value = "添加角色管理信息")
    @PostMapping("/add")
    public RespBean addSysRole(@RequestBody SysRole sysRole){
        sysRole.setCreatedate(LocalDateTime.now());
        if (iSysRoleService.save(sysRole)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新角色管理信息")
    @PutMapping("/update")
    @ResponseBody
    public RespBean updateSysRole(@RequestBody JSONObject json){
//        if(iSysRoleService.updateById(sysRole)){
//            return RespBean.success("修改成功");
//        }
//        return RespBean.error("修改失败");
        String oldcode = (String)json.get("oldCode");
        String rolename = (String)json.get("rolename");
        String rolecode = (String)json.get("rolecode");
        String updateperson = (String)json.get("updateperson");
        LocalDateTime updatedate = LocalDateTime.now();
        String roleseq = (String)json.get("roleseq");
        String description = (String)json.get("description");
        if(iSysRoleService.updateRole(oldcode, rolecode, roleseq, rolename, description, updateperson, updatedate)){
            return RespBean.success("更新成功");
        }
        return  RespBean.error("更新失败");

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

        List<RoleTree> roleTreeList = new ArrayList<>();
        List<RoleTree> roleTrees = iSysRoleService.selectByRolecode(rolecode);

        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("55")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("66")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("77")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("88")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("10")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("44")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("99")){
                roleTreeList.add(role);
                break;
            }
        }
        return roleTreeList;
//        return iSysRoleService.selectByRolecode(rolecode);
    }

    @ApiOperation(value = "添加删除接口")
    @PostMapping(value="/adddelapi", produces = "application/json;charset=UTF-8")
    public RespBean addAndDelApi(@RequestBody JSONObject json){

        String role = (String) json.get("role");
        List<String> add = (List<String>) json.get("add");
        List<String> delete = (List<String>) json.get("delete");
        List<SysRolemenumap> listSysRolemenumap = new ArrayList<>();
        List<SysUsermenumap> listSysUsermenumap = new ArrayList<>();
        LambdaQueryWrapper<SysUserrolemap> sysUserrolemapLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysUserrolemapLambdaQueryWrapper.eq(SysUserrolemap::getRolecode,role);
        List<SysUserrolemap> list = iSysUserrolemapService.list(sysUserrolemapLambdaQueryWrapper);
        SysRolemenumap sysRolemenumap;
        SysUsermenumap sysUserrolemap;
        for (String str : add){
            sysRolemenumap = new SysRolemenumap();
            sysRolemenumap.setRolecode(role);
            sysRolemenumap.setMenucode(str);
            listSysRolemenumap.add(sysRolemenumap);
            for(SysUserrolemap s : list){
                sysUserrolemap = new SysUsermenumap();
                sysUserrolemap.setUsercode(s.getUsercode());
                sysUserrolemap.setMenucode(str);
                sysUserrolemap.setStatus(1);
                listSysUsermenumap.add(sysUserrolemap);
            }
        }
        iSysRolemenumapService.saveBatch(listSysRolemenumap);
        iSysUsermenumapService.saveBatch(listSysUsermenumap);

        for (String str : delete){
            LambdaQueryWrapper<SysRolemenumap> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysRolemenumap::getRolecode,role);
            wrapper.eq(SysRolemenumap::getMenucode,str);
            for(SysUserrolemap s : list){
                LambdaQueryWrapper<SysUsermenumap> wrapperSysUsermenumap = new LambdaQueryWrapper<>();
                wrapperSysUsermenumap.eq(SysUsermenumap::getMenucode,str);
                wrapperSysUsermenumap.eq(SysUsermenumap::getUsercode,s.getUsercode());
                iSysUsermenumapService.remove(wrapperSysUsermenumap);
            }
            iSysRolemenumapService.remove(wrapper);
        }
        return RespBean.success("success");
    }

    @ApiOperation(value = "getRoleUser")
    @GetMapping("/getRoleUser/{role}")
    public RoleQuery getRoleUser(@PathVariable String role){

        return iSysUserrolemapService.getRoleUserhelp(role);
    }

    @ApiOperation(value = "deleteRoleUser")
    @DeleteMapping("/deleteRoleUser")
    @ResponseBody
    public RespBean deleteRoleUser(@RequestBody JSONObject json){
        String role = json.getString("role");
        String usercodes = json.getString("usercodes");
        String[] split = usercodes.substring(1, usercodes.length() - 1).split(", ");



        for(String str : split){
            LambdaQueryWrapper<SysUserrolemap> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysUserrolemap::getRolecode,role);
            wrapper.eq(SysUserrolemap::getUsercode,str);
            if(!iSysUserrolemapService.remove(wrapper))
                return RespBean.error("error!");
        }


        return RespBean.success("sucess");
    }

    @ApiOperation(value = "添加修改角色")
    @PutMapping("/editrole")
    @ResponseBody
    public RespBean addSysRole(@RequestBody JSONObject json){
        String usercode = (String) json.get("usercode");
        String rolecode = (String) json.get("rolecode");
        if (iSysUserrolemapService.updateRolecode(usercode, rolecode)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @Autowired
    ISysUsermenumapService iSysUsermenumapService;

    @ApiOperation(value = "获取用户菜单")
    @GetMapping("/getUsermenu/{usercode}")
    @ResponseBody
    public List<RoleTree> getUsermenu(@PathVariable String usercode){

//        10  命令管理      55
//        44  SIM卡管理     66
//        55  原始数据      77
//        66  分析数据      88
//        77  水表管理      10
//        88  设备管理      44
//        99  系统设置      99
        List<RoleTree> roleTreeList = new ArrayList<>();

        List<RoleTree> roleTrees = iSysRoleService.selectByUsercode(usercode);
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("55")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("66")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("77")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("88")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("10")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("44")){
                roleTreeList.add(role);
                break;
            }
        }
        for(RoleTree role : roleTrees){
            if(role.getIndex().equals("99")){
                roleTreeList.add(role);
                break;
            }
        }

        return roleTreeList;
    }

    @ApiOperation(value = "添加用户菜单")
    @PutMapping("/putUsermenu/{usercode}/{menucode}")
    @ResponseBody
    public RespBean getUsermenu(@PathVariable String usercode,@PathVariable String menucode){
        SysUsermenumap map = new SysUsermenumap();
        map.setMenucode(menucode);
        map.setUsercode(usercode);
        map.setStatus(1);
        if(iSysUsermenumapService.save(map))
            return RespBean.success("succeed");
        return RespBean.error("error");
    }

    @ApiOperation(value = "user添加删除接口")
    @PostMapping(value="/userAddAndDelApi", produces = "application/json;charset=UTF-8")
    public RespBean userAddAndDelApi(@RequestBody JSONObject json){

        String usercode = (String) json.get("usercode");
        List<String> add = (List<String>) json.get("add");
        List<String> delete = (List<String>) json.get("delete");
//        System.out.println(add);
//        System.out.println(delete);
        for (String str : add){
            LambdaUpdateWrapper<SysUsermenumap> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(SysUsermenumap::getUsercode,usercode);
            wrapper.eq(SysUsermenumap::getMenucode,str);
            wrapper.set(SysUsermenumap::getStatus,1);
            iSysUsermenumapService.update(wrapper);
        }

        for (String str : delete){
            LambdaUpdateWrapper<SysUsermenumap> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(SysUsermenumap::getUsercode,usercode);
            wrapper.eq(SysUsermenumap::getMenucode,str);
            wrapper.set(SysUsermenumap::getStatus,0);
            iSysUsermenumapService.update(wrapper);
        }
        return RespBean.success("success");
    }

}
