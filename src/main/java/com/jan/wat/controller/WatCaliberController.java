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
@Controller
@RequestMapping("/wat/caliber")
public class WatCaliberController {

    @Autowired
    IWatCaliberService iWatCaliberService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public Page<WatCaliber> getAllWatCaliberPage(@PathVariable long current, @PathVariable long size)
    {
        Page<WatCaliber> page = new Page<>(current, size);
        return iWatCaliberService.page(page);
    }

    @ApiOperation(value = "查询水表口径信息")
    @GetMapping("getall")
    @ResponseBody
    public List<WatCaliber> getAllCaliber(){
        return iWatCaliberService.list(null);
    }

    @ApiOperation(value = "添加水表口径信息")
    @PostMapping("/add")
    public RespBean addWatCaliber(@RequestBody WatCaliber watCaliber){
        if (iWatCaliberService.save(watCaliber)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新水表口径信息")
    @PutMapping("/update")
    public RespBean updateWatCaliber(@RequestBody WatCaliber watCaliber){
        if (iWatCaliberService.updateById(watCaliber)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量水表口径信息")
    @PutMapping("updatebatch")
    public RespBean updateBatchWatCaliber(@RequestBody List<WatCaliber> watCaliberList){
        if(iWatCaliberService.updateBatchById(watCaliberList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除水表口径信息")
    @DeleteMapping("/{id}")
    public RespBean deleteWatCaliber(@PathVariable Integer id){
        if (iWatCaliberService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除水表口径信息")
    @DeleteMapping("/delete")
    public RespBean deleteWatCaliberByIds(@RequestBody Integer[] ids){
        if (iWatCaliberService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
