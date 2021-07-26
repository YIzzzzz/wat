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
@RequestMapping("/sys/parameter")
public class SysParameterController {

    @Autowired
    ISysParameterService iSysParameterService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public Page<SysParameter> getAllSysParameterPage(@PathVariable long current, @PathVariable long size)
    {
        Page<SysParameter> page = new Page<>(current, size);
        return iSysParameterService.page(page);
    }

    @ApiOperation(value = "查询系统参数")
    @GetMapping("getall")
    public List<SysParameter> getSysParameter(){
        return iSysParameterService.list();
    }

    @ApiOperation(value = "添加系统参数")
    @PostMapping("/add")
    public RespBean addSysParameter(@RequestBody SysParameter sysParameter){
        if(iSysParameterService.save(sysParameter)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更改系统参数信息")
    @PutMapping("/update")
    public RespBean updateSysParameter(@RequestBody SysParameter sysParameter){
        if (iSysParameterService.updateById(sysParameter)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新系统参数信息")
    @PutMapping("/updatebatch")
    public RespBean updateBatchSysParameter(@RequestBody List<SysParameter> sysParameterList){
        if(iSysParameterService.updateBatchById(sysParameterList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除系统参数信息")
    @DeleteMapping("/{id}")
    public RespBean deleteSysParameter(@PathVariable String id){
        if (iSysParameterService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除系统参数信息")
    @DeleteMapping("/delete")
    public RespBean deleteSysParameterByIds(@RequestBody String[] ids){
        if (iSysParameterService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
