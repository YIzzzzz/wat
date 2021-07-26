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
@RequestMapping("/equ/server")
public class EquServerController {

    @Autowired
    IEquServerService iEquServerService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public Page<EquServer> getAllEquServerPage(@PathVariable long current, @PathVariable long size)
    {
        Page<EquServer> page = new Page<>(current, size);
        return iEquServerService.page(page);
    }

    @ApiOperation(value = "查询客户端管理")
    @GetMapping("getall")
    public List<EquServer> getAllEquServer(){
        return iEquServerService.list();
    }

    @ApiOperation(value = "添加客户端管理")
    @PostMapping("/add")
    public RespBean addEquServer(@RequestBody EquServer equServer){
        if(iEquServerService.save(equServer)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更改客户端管理信息")
    @PutMapping("/update")
    public RespBean updateEquServer(@RequestBody EquServer equServer){
        if (iEquServerService.updateById(equServer)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新客户端管理信息")
    @PutMapping("/updatebatch")
    public RespBean updateBatchEquServer(@RequestBody List<EquServer> equServerList){
        if(iEquServerService.updateBatchById(equServerList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除客户端管理信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquServer(@PathVariable String id){
        if (iEquServerService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除客户端管理信息")
    @DeleteMapping("/delete")
    public RespBean deleteEquServerByIds(@RequestBody String[] ids){
        if (iEquServerService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
