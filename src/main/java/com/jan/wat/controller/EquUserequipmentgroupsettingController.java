package com.jan.wat.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jan.wat.pojo.EquEquipmentgroupEquipmentMap;
import com.jan.wat.pojo.EquUserEquipmentgroupMap;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.vo.SysRegisterQuerry;
import com.jan.wat.service.IEquEquipmentgroupService;
import com.jan.wat.service.IEquUserEquipmentgroupMapService;
import com.jan.wat.service.ISysRoleService;
import com.jan.wat.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * wsk
 */
@RestController
@CrossOrigin
@Controller
@RequestMapping("/sys/userequipmentgroupsetting")
public class EquUserequipmentgroupsettingController {

    @Autowired
    ISysUserService iSysUserService;

    @Autowired
    ISysRoleService iSysRoleService;

    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;

    @Autowired
    IEquUserEquipmentgroupMapService iEquUserEquipmentgroupMapService;

    @ApiOperation(value = "获取全部用户")
    @GetMapping("getalluser/{usercode}")
    @ResponseBody
    public List<SysRegisterQuerry> getalluser(@PathVariable String usercode){
        String roleseq = iSysRoleService.getroleseqbyusercode(usercode);
        return iSysUserService.getalluser(roleseq);
    }

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

    @ApiOperation(value = "查询设备组")
    @GetMapping("/getequipmentgroup/{usercode}")
    public List<Integer> getEquipmentgroup(@PathVariable String usercode){

        LambdaQueryWrapper<EquUserEquipmentgroupMap> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EquUserEquipmentgroupMap::getUsercode,usercode);

        List<EquUserEquipmentgroupMap> list = iEquUserEquipmentgroupMapService.list(wrapper);

        List<Integer> equipmentgroup_id = new ArrayList<>();

        for(EquUserEquipmentgroupMap equUserEquipmentgroupMap : list){
            equipmentgroup_id.add(equUserEquipmentgroupMap.getEquipmentgroupId());
        }

        return equipmentgroup_id;
    }

    @ApiOperation(value = "分配设备组")
    @PostMapping("/equipmentgroupset")
    @ResponseBody
    public RespBean Equipmentgroupset(@RequestBody JSONObject json){

        String usercode = (String) json.get("usercode");
        ArrayList<Integer> delEquipmentgroup_id = (ArrayList<Integer>) json.get("delete");
        ArrayList<Integer> addEquipmentgroup_id = (ArrayList<Integer>) json.get("add");
        if(delEquipmentgroup_id != null) {
            for (Integer equipmentgroup_id : delEquipmentgroup_id) {
                LambdaQueryWrapper<EquUserEquipmentgroupMap> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(EquUserEquipmentgroupMap::getUsercode, usercode);
                wrapper.eq(EquUserEquipmentgroupMap::getEquipmentgroupId, equipmentgroup_id);
                if (!iEquUserEquipmentgroupMapService.remove(wrapper)) {
                    return RespBean.error("删除失败！");
                }
            }
        }

        if(addEquipmentgroup_id != null) {
            for (Integer equipmentgroup_id : addEquipmentgroup_id) {
                EquUserEquipmentgroupMap equUserEquipmentgroupMap = new EquUserEquipmentgroupMap();
                equUserEquipmentgroupMap.setEquipmentgroupId(equipmentgroup_id);
                equUserEquipmentgroupMap.setUsercode(usercode);
                if (!iEquUserEquipmentgroupMapService.save(equUserEquipmentgroupMap)) {
                    return RespBean.error("添加失败！");
                }
            }
        }
        return RespBean.success("成功");
    }

}
