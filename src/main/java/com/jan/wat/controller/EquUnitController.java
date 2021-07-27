package com.jan.wat.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * wsk
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/unit")
public class EquUnitController {

    @Autowired
    IEquUnitService iEquUnitService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public Page<EquUnit> getAllEquUnitPage(@PathVariable long current, @PathVariable long size)
    {
        Page<EquUnit> page = new Page<>(current, size);
        return iEquUnitService.page(page);
    }

    @ApiOperation(value = "查询数据单位管理")
    @GetMapping("getall")
    public List<EquUnit> getAllEquUnit(){
        return iEquUnitService.list();
    }

    @ApiOperation(value = "添加数据单位管理")
    @PostMapping("/add")
    public RespBean addEquUnit(@RequestBody EquUnit equUnit){
        if(iEquUnitService.save(equUnit)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更改数据单位管理信息")
    @PutMapping("/update")
    public RespBean updateEquUnit(@RequestBody EquUnit equUnit){
        if (iEquUnitService.updateById(equUnit)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新数据单位管理信息")
    @PutMapping("/updatebatch")
    public RespBean updateBatchEquUnit(@RequestBody List<EquUnit> equUnitList){
        if(iEquUnitService.updateBatchById(equUnitList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

//    @ApiOperation(value = "删除数据单位管理信息")
//    @DeleteMapping("/{id}")
//    public RespBean deleteEquUnit(@PathVariable String id){
//        if (iEquUnitService.removeById(id)){
//            return RespBean.success("删除成功");
//        }
//        return RespBean.error("删除失败");
//    }
//
//    @ApiOperation(value = "批量删除数据单位管理信息")
//    @DeleteMapping("/delete")
//    public RespBean deleteEquUnitByIds(@RequestBody String[] ids){
//        if (iEquUnitService.removeByIds(Arrays.asList(ids))){
//            return RespBean.success("删除成功");
//        }
//        return RespBean.error("删除失败");
//    }

}
