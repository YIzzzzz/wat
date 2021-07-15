package com.jan.wat.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jan.wat.pojo.SysUser;
import com.jan.wat.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@CrossOrigin
@Controller
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    ISysUserService iSysUserService;

    @RequestMapping("login")
    @ResponseBody
    public boolean login(@RequestBody JSONObject json){
        String userCode = (String) json.get("username");
        String password = (String) json.get("password");

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userCode",userCode);
        queryWrapper.eq("password",password);
        SysUser sysUser = iSysUserService.getOne(queryWrapper);
        if(sysUser == null)
            return false;
        return true;
    }
}
