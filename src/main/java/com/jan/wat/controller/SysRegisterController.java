package com.jan.wat.controller;

import com.jan.wat.pojo.SysUser;
import com.jan.wat.pojo.vo.OrganizeTree;
import com.jan.wat.pojo.vo.SysRegisterQuerry;
import com.jan.wat.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/sys/register")
public class SysRegisterController {

    @Autowired
    ISysUserService iSysUserService;


    @ApiOperation(value = "查询用户")
    @PutMapping()
    public List<SysRegisterQuerry> getuserbyorganize(@RequestBody String[] organizecodeList){

        List<SysRegisterQuerry> userList = new ArrayList<>();

        for(String organizecode : organizecodeList){
            List<SysRegisterQuerry> getuserbyorganizecode = iSysUserService.getuserbyorganizecode(organizecode);
            for(SysRegisterQuerry e : getuserbyorganizecode){
                userList.add(e);
            }
        }
        return userList;
    }

    @ApiOperation(value = "用户管理")
    @GetMapping("getall")
    @ResponseBody
    public List<SysUser> getAllUser(){
        return iSysUserService.list(null);
    }

}
