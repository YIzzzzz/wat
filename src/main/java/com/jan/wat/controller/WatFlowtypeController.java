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

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@Controller
@RequestMapping("/wat/flowtype")
public class WatFlowtypeController {

    @Autowired
    IWatFlowtypeService iWatFlowtypeService;

    @ApiOperation(value = "查询表具型号列表")
    @GetMapping("/getall")
    @ResponseBody
    public List<WatFlowtype> getAllFlowtypeList(){
        return iWatFlowtypeService.list(null);
    }

    @ApiOperation(value = "添加表具型号")
    @PostMapping("addFlowtype")
    public RespBean addFlowtype(@RequestBody WatFlowtype watFlowtype){
        if(iWatFlowtypeService.save(watFlowtype) ){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更改表具型号")
    @PostMapping("/updateFlowtype")
    public RespBean updateFlowtype(@RequestBody WatFlowtype watFlowtype){
        if(iWatFlowtypeService.updateById(watFlowtype)){
            System.out.println("1");
            return RespBean.success("更新成功");
        }
        System.out.println("2");
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除表具型号")
    @DeleteMapping("/{id}")
    public RespBean deleteFlowtypeById(@PathVariable Integer id){
        if(iWatFlowtypeService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除表具型号")
    @DeleteMapping("/deleteFlowtypeByIds")
    public RespBean deleteFlowtypeByIds(Integer[] ids){
        if(iWatFlowtypeService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
