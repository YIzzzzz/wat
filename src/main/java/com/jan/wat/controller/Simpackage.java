package com.jan.wat.controller;

import com.jan.wat.pojo.EquSimpackage;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.service.IEquSimpackageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.support.ResourceAdapterFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.Data;

/**
 * phm
 * 2021/7/17 下午3:23
 * 1.0
 */
@RestController
@RequestMapping("/equ/simpackage")
public class Simpackage {
    @Autowired
    IEquSimpackageService equSimpackageService;

    @ApiOperation(value = "查询套餐管理列表")
    @GetMapping("/")
    public List<EquSimpackage> getAllEquSimpackagelist(){
        return equSimpackageService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public RespBean addEquSimpackage(@RequestBody EquSimpackage equSimpackage){
        if (equSimpackageService.save(equSimpackage)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }
}

