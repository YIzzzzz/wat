package com.jan.wat.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquSim;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.service.IEquSimService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * phm
 * 2021/7/19 下午3:26
 * 1.0
 */

@RestController
@RequestMapping("/equ/sim")
public class EquSimController {
    @Autowired
    IEquSimService equSimService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public IPage<EquSim> getAllEquSimPage(@PathVariable Integer current, @PathVariable Integer size)
    {
        Page<EquSim> page = new Page<>(current, size);
        return equSimService.page(page);
    }

    @ApiOperation(value = "查询SIM卡管理列表")
    @GetMapping("/getall")
    public List<EquSim> getAllEquSimlist(){
        return equSimService.list();
    }

    @ApiOperation(value = "添加SIM卡")
    @PostMapping("/add")
    public RespBean addEquSim(@RequestBody EquSim equSim){
        if (equSimService.save(equSim)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新SIM卡信息")
    @PutMapping("/update")
    public RespBean updateEquSim(@RequestBody EquSim equSim){
        if (equSimService.updateById(equSim)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新SIM卡信息")
    @PutMapping("/updatabatch")
    public RespBean updateBatchEquSim(@RequestBody List<EquSim> equSimList)
    {
        if (equSimService.updateBatchById(equSimList))
        {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }


    @ApiOperation(value = "删除SIM信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquSim(@PathVariable String id){
        if (equSimService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除SIM信息")
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
