package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.*;
import com.jan.wat.pojo.vo.EquParavalueQuery;
import com.jan.wat.service.IEquEquipmentService;
import com.jan.wat.service.IEquEquipmentgroupEquipmentMapService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/equipment")
public class EquEquipmentController {

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @Autowired
    IEquEquipmentgroupEquipmentMapService iEquEquipmentgroupEquipmentMapService;

    @ApiOperation(value = "查询设备管理列表")
    @PostMapping("/getall")
    @ResponseBody
    public List<EquEquipment> getallEquipment(@RequestBody JSONObject json){

        System.out.println(json.toJSONString());
        String usercode = (String) json.get("usercode");
        String equipment_id = (String) json.get("equipment_id");
        String equipmentgroup_id = (String) json.get("equipmentgroup_id");
//        System.out.println(usercode);
//        return  null;
        return iEquEquipmentService.getEquEquipment(usercode, equipment_id, equipmentgroup_id);
    }

    @ApiOperation(value = "设置经纬度")
    @PostMapping("/setjingweidu")
    @ResponseBody
    public RespBean setjingweidu(@RequestBody JSONObject json){


        String equipment_id = (String) json.get("equipment_id");
        double longitude = (double) json.get("longitude");
        double latitude = (double) json.get("latitude");
        LambdaUpdateWrapper<EquEquipment> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(EquEquipment::getId,equipment_id);
        wrapper.set(EquEquipment::getLatitude, latitude);
        wrapper.set(EquEquipment::getLongitude, longitude);
        if (iEquEquipmentService.update(wrapper)){
            return RespBean.success("设置成功！");
        }
        return RespBean.error("设置失败！");
    }

    @ApiOperation(value = "更新设备管理信息")
    @PutMapping("/update")
    public RespBean updateEquEquipment(@RequestBody JSONObject json){

        String id = (String) json.getString("id");
        String name = (String) json.getString("n");
        String model = (String) json.getString("model");
        Integer manufactureId = Integer.valueOf(json.getString("manufacturerId"));
        String setupperson = (String) json.getString("setupperson");
        String setuptime = (String) json.getString("setuptime");
        String setupaddress = (String) json.getString("setupaddress");
        String manager = (String) json.getString("manager");
        String telephone = (String) json.getString("telephone");
        Integer offlinetimelimit = Integer.valueOf(json.getString("offlinetimelimit"));
        String des = (String) json.getString("des");

        LambdaUpdateWrapper<EquEquipment> wrapper = new LambdaUpdateWrapper<>();

        wrapper.eq(EquEquipment::getId,id);
        wrapper.set(EquEquipment::getAname,name);
        wrapper.set(EquEquipment::getModel,model);
        wrapper.set(EquEquipment::getManufacturerId,manufactureId);
        wrapper.set(EquEquipment::getSetupaddress,setupaddress);
        wrapper.set(EquEquipment::getSetuptime,setuptime);
        wrapper.set(EquEquipment::getSetupperson,setupperson);
        wrapper.set(EquEquipment::getManager,manager);
        wrapper.set(EquEquipment::getTelephone,telephone);
        wrapper.set(EquEquipment::getOfflinetimelimit,offlinetimelimit);
        wrapper.set(EquEquipment::getDes,des);


        if (iEquEquipmentService.update(wrapper)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "查询设备组")
    @GetMapping("/getequipmentgroup/{equipment_id}")
    public List<Integer> getEquipmentgroup(@PathVariable String equipment_id){


        LambdaQueryWrapper<EquEquipmentgroupEquipmentMap> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EquEquipmentgroupEquipmentMap::getEquipmentId,equipment_id);

        List<EquEquipmentgroupEquipmentMap> list = iEquEquipmentgroupEquipmentMapService.list(wrapper);

        List<Integer> equipmentgroup_id = new ArrayList<>();

        for(EquEquipmentgroupEquipmentMap equEquipmentgroupEquipmentMap : list){
            equipmentgroup_id.add(equEquipmentgroupEquipmentMap.getEquipmentgroupId());
        }

        return equipmentgroup_id;
    }


    @ApiOperation(value = "设置设备组")
    @PostMapping("/equipmentgroupset")
    @ResponseBody
    public RespBean Equipmentgroupset(@RequestBody JSONObject json){

        String equipment_id = (String) json.get("equipment_id");
        ArrayList<Integer> delEquipmentgroup_id = (ArrayList<Integer>) json.get("delete");
        ArrayList<Integer> addEquipmentgroup_id = (ArrayList<Integer>) json.get("add");
        if(delEquipmentgroup_id != null) {
            for (Integer equipmentgroup_id : delEquipmentgroup_id) {
                LambdaQueryWrapper<EquEquipmentgroupEquipmentMap> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(EquEquipmentgroupEquipmentMap::getEquipmentId, equipment_id);
                wrapper.eq(EquEquipmentgroupEquipmentMap::getEquipmentgroupId, equipmentgroup_id);
                if (!iEquEquipmentgroupEquipmentMapService.remove(wrapper)) {
                    return RespBean.error("删除失败！");
                }
            }
        }

        if(addEquipmentgroup_id != null) {
            for (Integer equipmentgroup_id : addEquipmentgroup_id) {
                EquEquipmentgroupEquipmentMap equEquipmentgroupEquipmentMap = new EquEquipmentgroupEquipmentMap();
                equEquipmentgroupEquipmentMap.setEquipmentgroupId(equipmentgroup_id);
                equEquipmentgroupEquipmentMap.setEquipmentId(equipment_id);
                if (!iEquEquipmentgroupEquipmentMapService.save(equEquipmentgroupEquipmentMap)) {
                    return RespBean.error("添加失败！");
                }
            }
        }
        return RespBean.success("成功");
    }

    @ApiOperation(value = "获取用户下设备")
    @PostMapping("/getequipmentbyusercode/{usercode}")
    @ResponseBody
    public List<EquEquipment> getEquipmentbyusercode(@PathVariable String usercode){

        return iEquEquipmentService.getEquEquipmentByUsercode(usercode);
    }


}
