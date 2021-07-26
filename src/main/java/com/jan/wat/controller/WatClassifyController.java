package com.jan.wat.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 * wsk
 */
@RestController
@CrossOrigin
@RequestMapping("/wat/classify")
@Slf4j
public class WatClassifyController {
    @Autowired
    IWatClassifyService iWatClassifyService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public Page<WatClassify> getAllWatClassifyPage(@PathVariable long current, @PathVariable long size)
    {
        Page<WatClassify> page = new Page<>(current, size);
        return iWatClassifyService.page(page);
    }

    @ApiOperation(value = "查询用水性质")
    @GetMapping("getall")
    public List<WatClassify> getWatClassify(){
        return iWatClassifyService.list();
    }

    @ApiOperation(value = "添加用水性质")
    @PostMapping("/add")
    public RespBean addWatClassify(@RequestBody WatClassify watClassify){
        if(iWatClassifyService.save(watClassify)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更改用水性质信息")
    @PutMapping("/update")
    public RespBean updateWatClassify(@RequestBody WatClassify watClassify){
        if (iWatClassifyService.updateById(watClassify)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新用水性质信息")
    @PutMapping("/updatebatch")
    public RespBean updateBatchWatClassify(@RequestBody List<WatClassify> watClassifyList){
        if(iWatClassifyService.updateBatchById(watClassifyList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除用水性质信息")
    @DeleteMapping("/{id}")
    public RespBean deleteWatClassify(@PathVariable Integer id){
        if (iWatClassifyService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除用水性质信息")
    @DeleteMapping("/delete")
    public RespBean deleteWatClassifyByIds(@RequestBody Integer[] ids){
        if (iWatClassifyService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
