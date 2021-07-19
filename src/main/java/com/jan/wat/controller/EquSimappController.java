package com.jan.wat.controller;

import com.jan.wat.pojo.EquSimapp;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.service.IEquSimappService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * phm
 * 2021/7/19 上午9:41
 * 1.0
 */
@RestController
@RequestMapping("equ/simapp")
public class EquSimappController {
    @Autowired
    IEquSimappService equSimappService;

    @ApiOperation(value = "查询API列表")
    @GetMapping("/getall")
    public List<EquSimapp> getAllEquSimapplist(){
        return equSimappService.list();
    }

    @ApiOperation(value = "添加API")
    @PostMapping("/add")
    public RespBean addEquSimapp(@RequestBody EquSimapp equSimapp){
        if (equSimappService.save(equSimapp)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新API信息")
    @PutMapping("/update")
    public RespBean updateEquSimapp(@RequestBody EquSimapp equSimapp){
        if (equSimappService.updateById(equSimapp)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新API信息")
    @PutMapping("/updatabatch")
    public RespBean updateBatchEquSimapp(@RequestBody List<EquSimapp> equSimappList)
    {
        if (equSimappService.updateBatchById(equSimappList))
        {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除API信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquSimapp(@PathVariable String id){
        if (equSimappService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除API信息")
    @DeleteMapping("/delete")
    public RespBean deleteEquSimappByIds(String[] ids)
    {
        if (equSimappService.removeByIds(Arrays.asList(ids)))
        {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败！");
    }
}
