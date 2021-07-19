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
import java.util.Date;

import java.text.SimpleDateFormat;



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
    @GetMapping("")
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
    @PostMapping("/adduser")
    public RespBean addUser(@RequestBody SysUser sysUser) throws ParseException {

        sysUser.setPassword("123456");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        sysUser.setCreatedate(df.parse(df.format(new Date())));
        sysUser.setUpdatedate(df.parse(df.format(new Date())));
        if(iSysUserService.save(sysUser)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/updateuser")
    public RespBean updateUser(@RequestBody SysUser sysUser){
        if(iSysUserService.updateById(sysUser)){
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @ApiOperation(value = "删除用户信息")
    @DeleteMapping("/{userCode}")
    public RespBean deleteSysUser(@PathVariable String userCode){
        if(iSysUserService.removeById(userCode)){
            return RespBean.success("删除成功");
        }
        return  RespBean.error("删除失败");
    }


}
