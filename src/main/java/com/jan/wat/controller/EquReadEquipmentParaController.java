package com.jan.wat.controller;

import com.jan.wat.pojo.vo.EquipmentTree;
import com.jan.wat.service.IEquEquipmentgroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/7/27下午4:51
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("equ/readequipmentpara")
public class EquReadEquipmentParaController {
    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;

    @ApiOperation(value = "获取参数树")
    @GetMapping("/getTree")
    public List<EquipmentTree> getTree(){
        return iEquEquipmentgroupService.createTree();
    }
}
