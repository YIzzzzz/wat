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

import java.util.Arrays;
import java.util.List;

/**
 * wsk
 */

@CrossOrigin
@RestController
@RequestMapping("/equ/datatype")
public class EquDatatypeController {

    @Autowired
    IEquDatatypeService iEquDatatypeService;

    @ApiOperation(value = "查询数据类型列表")
    @GetMapping("getall")
    public List<EquDatatype> getallDatatype(){
        return iEquDatatypeService.list(null);

    }

    @ApiOperation(value = "添加数据类型信息")
    @PostMapping("/add")
    public RespBean addEquDatatype(@RequestBody EquDatatype equDatatype){
        if (iEquDatatypeService.save(equDatatype)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新数据类型信息")
    @PutMapping("/update")
    public RespBean updateEquDatatype(@RequestBody EquDatatype equDatatype){
        if (iEquDatatypeService.updateById(equDatatype)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新数据类型信息")
    @PutMapping("updatebatch")
    public RespBean updateBatchEquDatatype(@RequestBody List<EquDatatype> equDatatypeList){
        if(iEquDatatypeService.updateBatchById(equDatatypeList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除数据类型信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquDatatype(@PathVariable Integer id){
        if (iEquDatatypeService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除数据类型信息")
    @DeleteMapping("/delete")
    public RespBean deleteEquDatatypeByIds(@RequestBody Integer[] ids){
        if (iEquDatatypeService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
