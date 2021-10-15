package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.*;
import com.jan.wat.pojo.vo.EquipmentQuery;
import com.jan.wat.pojo.vo.EquipmentTree;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/wat/alarmtype")
public class WatAlarmtypeController {

    @Autowired
    IWatAlarmtypeService iWatAlarmtypeService;

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;

    @Autowired
    IWatAlarmtypeFlowMapService iWatAlarmtypeFlowMapService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public Page<WatAlarmtype> getAllWatAlarmtypePage(@PathVariable long current, @PathVariable long size)
    {
        Page<WatAlarmtype> page = new Page<>(current, size);
        return iWatAlarmtypeService.page(page);
    }

    @ApiOperation(value = "查询报警类型列表")
    @GetMapping("getall")
    public List<WatAlarmtype> getAllWatAlarmtype(){
        return iWatAlarmtypeService.list(null);
    }

    @ApiOperation(value = "添加报警类型信息")
    @PostMapping("/add")
    public RespBean addWatAlarmtype(@RequestBody WatAlarmtype watAlarmtype){
        if (iWatAlarmtypeService.save(watAlarmtype)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新报警类型信息")
    @PutMapping("/update")
    public RespBean updateWatAlarmtype(@RequestBody WatAlarmtype watAlarmtype){
        if (iWatAlarmtypeService.updateById(watAlarmtype)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新报警类型信息")
    @PutMapping("updatebatch")
    public RespBean updateBatchWatAlarmtype(@RequestBody List<WatAlarmtype> watAlarmtypeList){
        if(iWatAlarmtypeService.updateBatchById(watAlarmtypeList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除报警类型信息")
    @DeleteMapping("/{id}")
    public RespBean deleteWatAlarmtype(@PathVariable Integer id){
        if (iWatAlarmtypeService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除报警类型信息")
    @DeleteMapping("/delete")
    public RespBean deleteWatAlarmtypeByIds(@RequestBody Integer[] ids){
        if (iWatAlarmtypeService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "获取设备")
    @GetMapping("/getequipment/{equipmentgroup_id}")
    public List<EquipmentQuery> getEquipmentbyequipmentgroupid(@PathVariable Integer equipmentgroup_id)
    {
        return iEquEquipmentService.getEquipmentbyequipmentgroupid(equipmentgroup_id);
    }

    @ApiOperation(value = "获取报警类型下设备")
    @GetMapping("/getequipment_id/{equipmentgroup_id}/{alarmtype_id}")
    public List<String> getEquipmentbyequipmentgroupid(@PathVariable Integer equipmentgroup_id, @PathVariable Integer alarmtype_id)
    {
        return iWatAlarmtypeService.getEquipment(alarmtype_id,equipmentgroup_id);
    }

    @ApiOperation(value = "获取全部设备组")
    @GetMapping("/getallequipmentgroup")
    public List<EquipmentTree> getallequipmentgrouop()
    {
        List<EquEquipmentgroup> list = iEquEquipmentgroupService.list();
        List<EquipmentTree> tree = new ArrayList<>();
        EquipmentTree.createTree(list,tree);
        return tree;
    }

    @ApiOperation(value = "新增删除设备")
    @PostMapping("/setequipment")
    @ResponseBody
    public RespBean Equipmentset(@RequestBody JSONObject json){

        Integer alarmtype_id = (Integer) json.get("alarmtype_id");
        ArrayList<String> delEquipment_id = (ArrayList<String>) json.get("delete");
        ArrayList<String> addEquipment_id = (ArrayList<String>) json.get("add");
        if(delEquipment_id != null) {
            for (String equipment_id : delEquipment_id) {
                LambdaQueryWrapper<WatAlarmtypeFlowMap> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(WatAlarmtypeFlowMap::getAlarmtypeId, alarmtype_id);
                wrapper.eq(WatAlarmtypeFlowMap::getEquipmentId, equipment_id);
                if (!iWatAlarmtypeFlowMapService.remove(wrapper)) {
                    return RespBean.error("删除失败！");
                }
            }
        }

        if(addEquipment_id != null) {
            for (String equipment_id : addEquipment_id) {
                WatAlarmtypeFlowMap watAlarmtypeFlowMap = new WatAlarmtypeFlowMap();
                watAlarmtypeFlowMap.setAlarmtypeId(alarmtype_id);
                watAlarmtypeFlowMap.setEquipmentId(equipment_id);
                if (!iWatAlarmtypeFlowMapService.save(watAlarmtypeFlowMap)) {
                    return RespBean.error("添加失败！");
                }
            }
        }
        return RespBean.success("成功");
    }

    @ApiOperation(value = "全部水表")
    @PostMapping("/allflow/{alarmtype_id}")
    @ResponseBody
    public RespBean Allflow(@PathVariable Integer alarmtype_id) {

        List<String> equipmentid = iEquEquipmentService.getEquipmentid();
        LambdaQueryWrapper<WatAlarmtypeFlowMap> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WatAlarmtypeFlowMap::getAlarmtypeId, alarmtype_id);
        if (!iWatAlarmtypeFlowMapService.remove(wrapper)) {
            return RespBean.error("删除失败！");
        }

        for (String equipment_id : equipmentid) {
            WatAlarmtypeFlowMap watAlarmtypeFlowMap = new WatAlarmtypeFlowMap();
            watAlarmtypeFlowMap.setAlarmtypeId(alarmtype_id);
            watAlarmtypeFlowMap.setEquipmentId(equipment_id);
            if (!iWatAlarmtypeFlowMapService.save(watAlarmtypeFlowMap)) {
                return RespBean.error("添加失败！");
            }
        }

        return RespBean.success("成功");
    }

}
