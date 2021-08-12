package com.jan.wat.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.*;
import com.jan.wat.pojo.vo.EquParavalueQuery;
import com.jan.wat.pojo.vo.EuipmentsQuery;
import com.jan.wat.pojo.vo.ParaTree;
import com.jan.wat.service.IEquEquipmentService;
import com.jan.wat.service.IEquParaService;
import com.jan.wat.service.IEquParavalueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/7/27上午10:09
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/paravalue")
public class EquParavalueController {

    @Autowired
    IEquParaService iEquParaService;

    @Autowired
    IEquParavalueService iEquParavalueService;

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @ApiOperation(value = "获取参数树")
    @GetMapping("/getTree")
    public List<ParaTree> getTree(){
        return iEquParaService.getTree();
    }

    @ApiOperation(value = "查询参数值管理")
    @GetMapping("getall/{para_id}")
    public IPage<EquParavalueQuery> getAllEquServer(@PathVariable Integer para_id){

        Page<EquParavalueQuery> page = new Page<>(1, 30);

        return iEquParavalueService.selectByPage(page, para_id);
    }

    @ApiOperation(value = "添加参数值管理信息")
    @PostMapping("/add")
    public RespBean addEquParavalue(@RequestBody EquParavalue equParavalue){
        if (iEquParavalueService.save(equParavalue)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新参数值管理信息")
    @PutMapping("/update")
    public RespBean updateEquParavalue(@RequestBody EquParavalue equParavalue){
        if (iEquParavalueService.updateByIdAndParaID(equParavalue)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }
}
