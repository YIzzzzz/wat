package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.SysUser;
import com.jan.wat.pojo.vo.SysRegisterQuerry;
import com.jan.wat.service.ISysRoleService;
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

    @Autowired
    ISysRoleService iSysRoleService;


//    @ApiOperation(value = "查询用户")
//    @PutMapping()
//    public List<SysRegisterQuerry> getuserbyorganize(@RequestBody String[] organizecodeList){
//
//        List<SysRegisterQuerry> userList = new ArrayList<>();
//
//        for(String organizecode : organizecodeList){
//            List<SysRegisterQuerry> getuserbyorganizecode = iSysUserService.getuserbyorganizecode(organizecode);
//            for(SysRegisterQuerry e : getuserbyorganizecode){
//                userList.add(e);
//            }
//        }
//        return userList;
//    }

    @ApiOperation(value = "查询用户")
    @PutMapping()
    @ResponseBody
    public List<SysRegisterQuerry> getuserbyorganize(@RequestBody JSONObject json){

        ArrayList<String> organizecodeList = (ArrayList<String>) json.get("organizecode");
        String usercode = (String) json.get("usercode");

        String roleseq = iSysRoleService.getroleseqbyusercode(usercode);

        List<SysRegisterQuerry> userList = new ArrayList<>();

        for(String organizecode : organizecodeList){
            List<SysRegisterQuerry> getuserbyorganizecode = iSysUserService.getuserbyorganizecode(organizecode, roleseq);
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
