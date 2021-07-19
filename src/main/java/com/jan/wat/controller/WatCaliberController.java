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

    @ApiOperation(value = "查询水表口径信息")
    @GetMapping("")
    @ResponseBody
    public List<WatCaliber> getAllCaliber(){
        return iWatCaliberService.list(null);
    }

    @ApiOperation(value = "添加水表口径信息")
    @PostMapping("/addCaliber")
    public RespBean addWatCaliber(@RequestBody WatCaliber watCaliber){
        if (iWatCaliberService.save(watCaliber)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新水表口径信息")
    @PostMapping("/updateCaliber")
    public RespBean updateWatCaliber(@RequestBody WatCaliber watCaliber){
        if (iWatCaliberService.updateById(watCaliber)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除水表口径信息")
    @DeleteMapping("/{id}")
    public RespBean deleteWatCaliber(@PathVariable Integer id){
        if (iWatCaliberService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
