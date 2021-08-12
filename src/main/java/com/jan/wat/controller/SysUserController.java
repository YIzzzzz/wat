package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.SysOrganize;
import com.jan.wat.pojo.SysUser;
import com.jan.wat.service.ISysOrganizeService;
import com.jan.wat.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * wsk
 */
@RestController
@CrossOrigin
@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    ISysUserService iSysUserService;

    @Autowired
    ISysOrganizeService iSysOrganizeService;

    @ApiOperation(value = "用户管理")
    @GetMapping("getall")
    @ResponseBody
    public String getAllUser(){
        JSONObject jsonObject = new JSONObject();
        List<SysUser> userList = iSysUserService.list(null);
        List<SysOrganize> organizeList = iSysOrganizeService.list(null);
        jsonObject.put("userList",userList);
        jsonObject.put("organizeList",organizeList);
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "添加用户")
    @ResponseBody
    @PostMapping("/add")
    public RespBean addUser(@RequestBody SysUser sysUser) throws ParseException {

        sysUser.setPassword("123456");
        sysUser.setCreatedate(LocalDateTime.now());
        sysUser.setUpdatedate(LocalDateTime.now());
        if(iSysUserService.save(sysUser)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "修改用户信息")
    @PutMapping("/update")
    public RespBean updateUser(@RequestBody SysUser sysUser){
        if(iSysUserService.updateById(sysUser)){
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @ApiOperation(value = "批量更新用户信息")
    @PutMapping("updatebatch")
    public RespBean updateBatchSysUser(@RequestBody List<SysUser> sysUserList){
        if(iSysUserService.updateBatchById(sysUserList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除用户信息")
    @DeleteMapping("/{userCode}")
    public RespBean deleteSysUser(@PathVariable String userCode){
        if(iSysUserService.removeById(userCode)){
            return RespBean.success("删除成功");
        }
        return  RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除用户信息")
    @DeleteMapping("/delete")
    public RespBean deleteSysUserByIds(@RequestBody Integer[] ids){
        if (iSysUserService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }


}
