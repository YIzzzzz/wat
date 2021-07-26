package com.jan.wat.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * wsk
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/manufacturer")
public class EquManufacturerController {

    @Autowired
    IEquManufacturerService iEquManufacturerService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public Page<EquManufacturer> getAllEquManufacturerPage(@PathVariable long current, @PathVariable long size)
    {
        Page<EquManufacturer> page = new Page<>(current, size);
        return iEquManufacturerService.page(page);
    }

    @ApiOperation(value = "查询设备厂家管理")
    @GetMapping("getall")
    public List<EquManufacturer> getAllEquManufacturer(){
        return iEquManufacturerService.list();
    }

    @ApiOperation(value = "添加设备厂家管理")
    @PostMapping("/add")
    public RespBean addEquManufacturer(@RequestBody EquManufacturer equManufacturer){
        if(iEquManufacturerService.save(equManufacturer)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更改设备厂家管理信息")
    @PutMapping("/update")
    public RespBean updateEquManufacturer(@RequestBody EquManufacturer equManufacturer){
        if (iEquManufacturerService.updateById(equManufacturer)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新设备厂家管理信息")
    @PutMapping("/updatebatch")
    public RespBean updateBatchEquManufacturer(@RequestBody List<EquManufacturer> equManufacturerList){
        if(iEquManufacturerService.updateBatchById(equManufacturerList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除设备厂家管理信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquManufacturer(@PathVariable String id){
        if (iEquManufacturerService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除设备厂家管理信息")
    @DeleteMapping("/delete")
    public RespBean deleteEquManufacturerByIds(@RequestBody String[] ids){
        if (iEquManufacturerService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
