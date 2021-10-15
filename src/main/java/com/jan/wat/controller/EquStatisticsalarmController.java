package com.jan.wat.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jan.wat.pojo.*;
import com.jan.wat.service.IEquEquipmentgroupService;
import com.jan.wat.service.IEquUserEquipmentgroupMapService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/statisticalarm")
public class EquStatisticsalarmController {

    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;

    @Autowired
    IEquUserEquipmentgroupMapService iEquUserEquipmentgroupMapService;

    @ApiOperation(value = "添加设备组")
    @PostMapping("/add/{usercode}")
    public RespBean addEquipmentgroup(@RequestBody EquEquipmentgroup equEquipmentgroup, @PathVariable String usercode){
        if(iEquEquipmentgroupService.save(equEquipmentgroup)){

            Integer id = iEquEquipmentgroupService.getMaxId();
            EquUserEquipmentgroupMap equUserEquipmentgroupMap = new EquUserEquipmentgroupMap();
            equUserEquipmentgroupMap.setEquipmentgroupId(id);
            equUserEquipmentgroupMap.setUsercode(usercode);

            if(iEquUserEquipmentgroupMapService.save(equUserEquipmentgroupMap)){
                return RespBean.success("添加成功");
            }
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更新设备组名字")
    @PutMapping("/updateequipmentgroupname")
    public RespBean updateEquEquipmentgroupename(@RequestBody EquEquipmentgroup equEquipmentgroup){
        LambdaUpdateWrapper<EquEquipmentgroup> wrapper = new LambdaUpdateWrapper<>();
        System.out.println(equEquipmentgroup);
        wrapper.eq(EquEquipmentgroup::getId,equEquipmentgroup.getId());
        wrapper.set(EquEquipmentgroup::getName,equEquipmentgroup.getName());
        if (iEquEquipmentgroupService.update(wrapper)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "更新设备组父节点")
    @PutMapping("/updateequipmentgroupparentid")
    public RespBean updateEquEquipmentgroupeparentid(@RequestBody EquEquipmentgroup equEquipmentgroup){
        LambdaUpdateWrapper<EquEquipmentgroup> wrapper = new LambdaUpdateWrapper<>();
        System.out.println(equEquipmentgroup);
        wrapper.eq(EquEquipmentgroup::getId,equEquipmentgroup.getId());
        wrapper.set(EquEquipmentgroup::getParentid,equEquipmentgroup.getParentid());
        if (iEquEquipmentgroupService.update(wrapper)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除设备组")
    @DeleteMapping("/{id}")
    public RespBean deleteEquEquipmentgroupe(@PathVariable Integer id){
        if (iEquEquipmentgroupService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除设备组")
    @DeleteMapping("/delete")
    public RespBean deleteEquEquipmentgroupeByIds(@RequestBody Integer[] ids){
        if (iEquEquipmentgroupService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
