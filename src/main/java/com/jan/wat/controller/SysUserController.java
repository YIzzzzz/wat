package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jan.wat.pojo.*;
import com.jan.wat.pojo.vo.SysRegisterQuerry;
import com.jan.wat.pojo.vo.SysRoleeditQuery;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    ISysRoleService iSysRoleService;

    @Autowired
    ISysUserrolemapService iSysUserrolemapService;

    @Autowired
    ISysUserorganizemapService iSysUserorganizemapService;

    @Autowired
    ISysOrganizeService iSysOrganizeService;

    @Autowired
    ISysRolemenumapService iSysRolemenumapService;

    @Autowired
    ISysUsermenumapService iSysUsermenumapService;

    @ApiOperation(value = "用户管理")
    @PutMapping("getall/{usercode}")
    @ResponseBody
    public List<SysRegisterQuerry> getAllUserbyusercode(@PathVariable String usercode, @RequestBody String[] organizecodeList){
        String roleseq = iSysRoleService.getroleseqbyusercode(usercode);

        List<SysRegisterQuerry> sysRegisterQuerries = new ArrayList<>();

        for(String organizecode: organizecodeList){
            List<SysRegisterQuerry> getuserbyorganizecode = iSysUserService.getuserbyorganizecode(organizecode, roleseq);
            for(SysRegisterQuerry e: getuserbyorganizecode){
                sysRegisterQuerries.add(e);
            }
        }
        return sysRegisterQuerries;
    }


    @ApiOperation(value = "获取全部用户")
    @GetMapping("getalluser/{usercode}")
    @ResponseBody
    public List<SysRegisterQuerry> getalluser(@PathVariable String usercode){
        String roleseq = iSysRoleService.getroleseqbyusercode(usercode);
        return iSysUserService.getalluser(roleseq);
    }

    @ApiOperation(value = "添加用户")
    @ResponseBody
    @PostMapping("/add/{organizecode}/{rolecode}")
    public RespBean addUser(@RequestBody SysUser sysUser, @PathVariable String organizecode, @PathVariable String rolecode) throws ParseException {

//        sysUser.setPassword("123456");
        sysUser.setCreatedate(LocalDateTime.now());
        sysUser.setUpdatedate(LocalDateTime.now());
        LambdaQueryWrapper<SysRolemenumap> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRolemenumap::getRolecode,rolecode);
        List<SysRolemenumap> sysRolemenumaps= iSysRolemenumapService.list(wrapper);
        List<SysUsermenumap> sysUsermenumaps = new ArrayList<>();
        for(SysRolemenumap sysRolemenumap : sysRolemenumaps){
            SysUsermenumap sysUsermenumap = new SysUsermenumap();
            sysUsermenumap.setUsercode(sysUser.getUsercode());
            sysUsermenumap.setMenucode(sysRolemenumap.getMenucode());
            sysUsermenumap.setStatus(1);
            sysUsermenumaps.add(sysUsermenumap);
        }

        if(iSysUserService.save(sysUser) && iSysUsermenumapService.saveBatch(sysUsermenumaps)){
            SysUserorganizemap sysUserorganizemap = new SysUserorganizemap();
            sysUserorganizemap.setUsercode(sysUser.getUsercode());
            sysUserorganizemap.setOrganizecode(organizecode);

            SysUserrolemap sysUserrolemap = new SysUserrolemap();
            sysUserrolemap.setUsercode(sysUser.getUsercode());
            sysUserrolemap.setRolecode(rolecode);
            if(iSysUserorganizemapService.save(sysUserorganizemap) && iSysUserrolemapService.saveOrUpdate(sysUserrolemap)){

                return RespBean.success("添加成功");
            }else{
                return RespBean.error("添加失败");
            }
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "修改用户信息")
    @PutMapping("/update")
    @ResponseBody
    public RespBean updateUser(@RequestBody SysUser sysUser){
        if(iSysUserService.updateById(sysUser)){

            return RespBean.success("成功");
        }
        return RespBean.error("失败");
    }

    @ApiOperation(value = "修改用户信息")
    @PutMapping("/update/{rolecode}/{usercode}")
    @ResponseBody
    public String updateUser(@RequestBody SysUser sysUser, @PathVariable String rolecode, @PathVariable String usercode){
        if(iSysUserService.updateById(sysUser)&&iSysUserrolemapService.updateRolecode(sysUser.getUsercode(),rolecode)){
            return iSysUserrolemapService.getroleseqbyusercode(usercode);
        }
        return null;
    }

//    @ApiOperation(value = "修改用户信息")
//    @PutMapping("/update")
//    @ResponseBody
//    public String updateUser(@RequestBody JSONObject json){
//
//        SysUser sysUser = (SysUser) json.get("sysUser");
//        String rolecode = (String) json.get("rolecode");
//        String usercode = (String) json.get("usercode");
//
//        if(iSysUserService.updateById(sysUser)&&iSysUserrolemapService.updateRolecode(sysUser.getUsercode(),rolecode)){
//
//            return iSysUserrolemapService.getroleseqbyusercode(usercode);
//        }
//        return null;
//    }

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
    public RespBean deleteSysUserByIds(@RequestBody String[] ids){
        if (iSysUserService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/updatepassword")
    @ResponseBody
    public RespBean updatepassword(@RequestBody JSONObject json){

        String usercode = (String) json.get("usercode");
        String password = (String) json.get("password");
        if(iSysUserService.updatepassword(usercode,password)){
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }


}
