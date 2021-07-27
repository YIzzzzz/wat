package com.jan.wat.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.mapper.EquParaMapper;
import com.jan.wat.pojo.EquPara;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.service.IEquParaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/para")
public class EquParaController {

    @Autowired
    IEquParaService iEquParaService;

    @ApiOperation(value = "分页")
    @GetMapping("{current}/{size}")
    public IPage<EquParaQuery> getAllEquParaPage(@PathVariable long current, @PathVariable long size) {
        Page<EquParaQuery> page = new Page<>(current, size);
        return iEquParaService.selectByPage(page);
    }

    @ApiOperation(value = "查询参数管理列表")
    @GetMapping("/getall")
    public List<EquParaQuery> getAllEquParalist(){
        return iEquParaService.queryParaList();
    }

    @ApiOperation(value = "添加参数管理")
    @PostMapping("/add")
    public RespBean addEquPara(@RequestBody EquPara equPara){
        if (iEquParaService.save(equPara)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新参数管理信息")
    @PutMapping("/update")
    public RespBean updateEquPara(@RequestBody EquPara equPara){
        if (iEquParaService.updateById(equPara)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新参数管理信息")
    @PutMapping("/updatabatch")
    public RespBean updateBatchEquPara(@RequestBody List<EquPara> equParaList)
    {
        if (iEquParaService.updateBatchById(equParaList))
        {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除参数管理信息")
    @DeleteMapping("/{id}")
    public RespBean deleteEquPara(@PathVariable String id){
        if (iEquParaService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除参数管理信息")
    @DeleteMapping("/delete")
    public RespBean deleteEquParaByIds(@RequestBody String[] ids)
    {
        if (iEquParaService.removeByIds(Arrays.asList(ids)))
        {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败！");
    }
}
