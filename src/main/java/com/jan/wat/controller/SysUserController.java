package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.SysOrganize;
import com.jan.wat.pojo.SysUser;
import com.jan.wat.service.ISysOrganizeService;
import com.jan.wat.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;

import java.text.SimpleDateFormat;



import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/sys")
public class SysUserController {

    @Autowired
    ISysUserService iSysUserService;

    @Autowired
    ISysOrganizeService iSysOrganizeService;

    @RequestMapping("user")
    @ResponseBody
    public String getAllUser(){
        JSONObject jsonObject = new JSONObject();
        List<SysUser> userList = iSysUserService.list(null);
        List<SysOrganize> organizeList = iSysOrganizeService.list(null);
        jsonObject.put("userList",userList);
        jsonObject.put("organizeList",organizeList);
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping("adduser")
    public boolean addUser(@RequestBody JSONObject json) throws ParseException {
        String userCode = (String) json.get("userCode");
        String username = (String) json.get("username");
        String description = (String) json.get("description");
        Boolean isenable = (Boolean) json.get("isenable");               //字符串转boolean
        String currentUser  = (String) json.get("currentUser");

        SysUser sysUser = new SysUser();
        sysUser.setUsercode(userCode);
        sysUser.setUsername(username);
        sysUser.setDescription(description);
        sysUser.setIsenable(isenable);
        sysUser.setCreateperson(currentUser);
        sysUser.setPassword("123456");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        sysUser.setCreatedate(df.parse(df.format(new Date())));
        sysUser.setUpdateperson(currentUser);
        sysUser.setUpdatedate(df.parse(df.format(new Date())));

        return true;
    }

}
