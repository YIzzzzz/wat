package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.mapper.EquDatatypeMapper;
import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
}
