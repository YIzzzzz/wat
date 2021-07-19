package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.mapper.EquDatatypeMapper;
import com.jan.wat.mapper.EquEquipmentMapper;
import com.jan.wat.pojo.*;
import com.jan.wat.scheduled.ApplicationContextUtil;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * wsk
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/equipmenttype")
public class EquEquipmenttypeController {

    @Autowired
    IEquEquipmenttypeService iEquEquipmenttypeService;

    @ApiOperation(value = "查询设备类型列表")
    @GetMapping("")
    public List<EquEquipmenttype> getEquipmenttype(){
        return iEquEquipmenttypeService.list(null);
    }

    @ApiOperation(value = "添加设备类型信息")
    @PostMapping("/addEquipmenttype")
    public RespBean addEquipmenttype(@RequestBody EquEquipmenttype equEquipmenttype){
        if (iEquEquipmenttypeService.save(equEquipmenttype)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新设备类型信息")
    @PostMapping("/updateEquipmenttype")
    public RespBean updateEquipmenttype(@RequestBody EquEquipmenttype equEquipmenttype){
        if (iEquEquipmenttypeService.updateById(equEquipmenttype)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除设备类型信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquSimpackage(@PathVariable Integer id){
        if (iEquEquipmenttypeService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
