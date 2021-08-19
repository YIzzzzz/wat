package com.jan.wat.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.mapper.EquDatatypeMapper;
import com.jan.wat.mapper.EquEquipmentMapper;
import com.jan.wat.mapper.SysUserorganizemapMapper;
import com.jan.wat.pojo.*;
import com.jan.wat.scheduled.ApplicationContextUtil;
import com.jan.wat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/sys")
public class SysController {

    @Autowired
    ISysUserService iSysUserService;

    @Autowired
    ISysUserrolemapService iSysUserrolemapService;

    @Autowired
    ISysUserorganizemapService iSysUserorganizemapService;

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody JSONObject json){
        String usercode = (String) json.get("usercode");
        String password = (String) json.get("password");

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userCode",usercode);
        queryWrapper.eq("password",password);
        SysUser sysUser = iSysUserService.getOne(queryWrapper);
        JSONObject jsonObject = new JSONObject();
        if(sysUser == null){
            jsonObject.put("ifloginsuccess",false);
        }else{
            String username = sysUser.getUsername();
            jsonObject.put("ifloginsuccess",true);
            String organizecodebyusercode = iSysUserorganizemapService.getOrganizecodebyusercode(usercode);
            jsonObject.put("organizecode",organizecodebyusercode);
            String roleseq = iSysUserrolemapService.getroleseqbyusercode(usercode);
            jsonObject.put("roleseq", roleseq);
            jsonObject.put("username", username);
        }
        return jsonObject.toJSONString();
    }
}
