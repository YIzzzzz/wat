package com.jan.wat.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 * wsk
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/equipmenttype")
public class EquEquipmenttypeController {

    @Autowired
    IEquEquipmenttypeService iEquEquipmenttypeService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public Page<EquEquipmenttype> getAllEquEquipmenttypePage(@PathVariable long current, @PathVariable long size)
    {
        Page<EquEquipmenttype> page = new Page<>(current, size);
        return iEquEquipmenttypeService.page(page);
    }

    @ApiOperation(value = "查询设备类型列表")
    @GetMapping("getall")
    public List<EquEquipmenttype> getallEquipmenttype(){
        return iEquEquipmenttypeService.list(null);
    }

    @ApiOperation(value = "添加设备类型信息")
    @PostMapping("/add")
    public RespBean addEquipmenttype(@RequestBody EquEquipmenttype equEquipmenttype){
        if (iEquEquipmenttypeService.save(equEquipmenttype)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新设备类型信息")
    @PutMapping("/update")
    public RespBean updateEquipmenttype(@RequestBody EquEquipmenttype equEquipmenttype){
        if (iEquEquipmenttypeService.updateById(equEquipmenttype)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新设备类型信息")
    @PutMapping("updatebatch")
    public RespBean updateBatchEquipmenttype(@RequestBody List<EquEquipmenttype> equEquipmenttypeList){
        if(iEquEquipmenttypeService.updateBatchById(equEquipmenttypeList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除设备类型信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquipmenttype(@PathVariable Integer id){
        if (iEquEquipmenttypeService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除用水性质信息")
    @DeleteMapping("/delete")
    public RespBean deleteEquipmenttypeByIds(@RequestBody Integer[] ids){
        if (iEquEquipmenttypeService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
