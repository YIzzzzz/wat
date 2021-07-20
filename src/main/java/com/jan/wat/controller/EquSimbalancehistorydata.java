package com.jan.wat.controller;

import com.jan.wat.pojo.EquSimbalancehistoryrecord;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.service.IEquSimbalancehistoryrecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * phm
 * 2021/7/20 上午10:35
 * 1.0
 */

@CrossOrigin
@RestController
@RequestMapping("equ/simbalancehistorydata")
public class EquSimbalancehistorydata {

    @Autowired
    IEquSimbalancehistoryrecordService equSimbalancehistoryrecordService;


    @ApiOperation(value = "查询SIM卡金额历史管理列表")
    @GetMapping("/getall")
    public List<EquSimbalancehistoryrecord> getAllEquSimbalancehistoryrecordlist(){
        return equSimbalancehistoryrecordService.list();
    }

    @ApiOperation(value = "添加SIM卡金额历史信息")
    @PostMapping("/add")
    public RespBean addEquSimbalancehistoryrecord(@RequestBody EquSimbalancehistoryrecord equSimbalancehistoryrecord){
        if (equSimbalancehistoryrecordService.save(equSimbalancehistoryrecord))
        {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新SIM卡金额历史信息")
    @PutMapping("/update")
    public RespBean updateEquSim(@RequestBody EquSimbalancehistoryrecord equSimbalancehistoryrecord){
        if (equSimbalancehistoryrecordService.updateById(equSimbalancehistoryrecord)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新SIM卡金额历史信息")
    @PutMapping("/updatabatch")
    public RespBean updateBatchEquSimbalancehistoryrecord(@RequestBody List<EquSimbalancehistoryrecord> equSimbalancehistoryrecordList)
    {
        if (equSimbalancehistoryrecordService.updateBatchById(equSimbalancehistoryrecordList))
        {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }


    @ApiOperation(value = "删除SIM卡金额历史信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquSimbalancehistoryrecord(@PathVariable Integer id){
        if (equSimbalancehistoryrecordService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除SIM卡金额历史信息")
    @DeleteMapping("/delete")
    public RespBean deleteEquSimbalancehistoryrecordByIds(@RequestBody Integer[] ids)
    {
        if (equSimbalancehistoryrecordService.removeByIds(Arrays.asList(ids)))
        {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败！");
    }
}
