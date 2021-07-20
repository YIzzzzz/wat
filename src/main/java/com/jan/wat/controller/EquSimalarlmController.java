package com.jan.wat.controller;

import com.jan.wat.pojo.EquSim;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.service.IEquSimService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * phm
 * 2021/7/20 上午10:27
 * 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("equ/simalarm")
public class EquSimalarlmController {
    @Autowired
    IEquSimService equSimService;

    @ApiOperation(value = "查询SIM卡报警管理列表")
    @GetMapping("/getall")
    public List<EquSim> getAllEquSimlist(){
        return equSimService.list();
    }

    @ApiOperation(value = "添加SIM卡报警信息")
    @PostMapping("/add")
    public RespBean addEquSim(@RequestBody EquSim equSim){
        if (equSimService.save(equSim)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新SIM卡报警信息")
    @PutMapping("/update")
    public RespBean updateEquSim(@RequestBody EquSim equSim){
        if (equSimService.updateById(equSim)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新SIM卡报警信息")
    @PutMapping("/updatabatch")
    public RespBean updateBatchEquSim(@RequestBody List<EquSim> equSimList)
    {
        if (equSimService.updateBatchById(equSimList))
        {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }


    @ApiOperation(value = "删除SIM卡报警信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquSim(@PathVariable String id){
        if (equSimService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除SIM卡报警信息")
    @DeleteMapping("/delete")
    public RespBean deleteEquSimByIds(@RequestBody String[] ids)
    {
        if (equSimService.removeByIds(Arrays.asList(ids)))
        {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败！");
    }
}
