package com.jan.wat.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@RestController
@CrossOrigin
@RequestMapping("/equ/paragroup")
public class EquParagroupController {

    @Autowired
    IEquParagroupService iEquParagroupService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public Page<EquParagroup> getAllEquParagroupPage(@PathVariable long current, @PathVariable long size)
    {
        Page<EquParagroup> page = new Page<>(current, size);
        return iEquParagroupService.page(page);
    }

    @ApiOperation(value = "查询参数组管理")
    @GetMapping("getall")
    public List<EquParagroup> getEquParagroup(){
        return iEquParagroupService.list();
    }

    @ApiOperation(value = "添加参数组管理")
    @PostMapping("/add")
    public RespBean addEquParagroup(@RequestBody EquParagroup equParagroup){
        if(iEquParagroupService.save(equParagroup)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更改参数组管理")
    @PutMapping("/update")
    public RespBean updateEquParagroup(@RequestBody EquParagroup equParagroup){
        if (iEquParagroupService.updateById(equParagroup)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新参数组管理信息")
    @PutMapping("/updatebatch")
    public RespBean updateBatchEquParagroup(@RequestBody List<EquParagroup> equParagroupList){
        if(iEquParagroupService.updateBatchById(equParagroupList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除参数组管理信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquParagroup(@PathVariable String id){
        if (iEquParagroupService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除参数组管理信息")
    @DeleteMapping("/delete")
    public RespBean deleteEquParagroupByIds(@RequestBody String[] ids){
        if (iEquParagroupService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
