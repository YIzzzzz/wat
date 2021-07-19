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
@RequestMapping("/wat/classify")
public class WatClassifyController {
    @Autowired
    IWatClassifyService iWatClassifyService;

    @ApiOperation(value = "查询用水性质")
    @GetMapping("")
    public List<WatClassify> getClassify(){
        return iWatClassifyService.list();
    }

    @ApiOperation(value = "添加用水性质")
    @PostMapping("/addClassify")
    public RespBean addWatClassify(@RequestBody WatClassify watClassify){
        if(iWatClassifyService.save(watClassify)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更改用水性质信息")
    @PostMapping("/updateClassify")
    public RespBean updateWatClassify(@RequestBody WatClassify watClassify){
        if (iWatClassifyService.updateById(watClassify)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除用水性质信息")
    @DeleteMapping("/{id}")
    public RespBean deleteWatClassify(@PathVariable Integer id){
        if (iWatClassifyService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
