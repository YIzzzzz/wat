package com.jan.wat.controller;

import com.jan.wat.pojo.EquSim;
import com.jan.wat.pojo.EquSimpackage;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.service.IEquSimpackageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;


/**
 * phm
 * 2021/7/17 下午3:23
 * 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/simpackage")
public class EquSimpackageController {
    @Autowired
    IEquSimpackageService equSimpackageService;

    @ApiOperation(value = "查询套餐管理列表")
    @GetMapping("/getall")
    public List<EquSimpackage> getAllEquSimpackagelist(){
        return equSimpackageService.list();
    }

    @ApiOperation(value = "添加套餐管理信息")
    @PostMapping("/add")
    public RespBean addEquSimpackage(@RequestBody EquSimpackage equSimpackage){
        if (equSimpackageService.save(equSimpackage)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新套餐信息")
    @PutMapping("/update")
    public RespBean updateEquSimpackage(@RequestBody EquSimpackage equSimpackage){
        if (equSimpackageService.updateById(equSimpackage)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新套餐卡信息")
    @PutMapping("/updatabatch")
    public RespBean updateBatchEquSimpackage(@RequestBody List<EquSimpackage> equSimpackageList)
    {
        if (equSimpackageService.updateBatchById(equSimpackageList))
        {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除套餐信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquSimpackage(@PathVariable Integer id){
        if (equSimpackageService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "；批量删除套餐信息")
    @DeleteMapping("/delete")
    public RespBean deleteEquSimpackageByIds(@RequestBody Integer[] ids){
        if (equSimpackageService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}

