package com.jan.wat.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.mapper.EquDatatypeMapper;
import com.jan.wat.mapper.EquEquipmentMapper;
import com.jan.wat.pojo.*;
import com.jan.wat.scheduled.ApplicationContextUtil;
import com.jan.wat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/sys")
public class SysController {

//    @Autowired
//    ISysUserService iSysUserService;
//
//    @RequestMapping("login")
//    @ResponseBody
//    public boolean login(@RequestBody JSONObject json){
//        String userCode = (String) json.get("username");
//        String password = (String) json.get("password");
//
//        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("userCode",userCode);
//        queryWrapper.eq("password",password);
//        SysUser sysUser = iSysUserService.getOne(queryWrapper);
//        if(sysUser == null)
//            return false;
//        return true;
//    }
}
