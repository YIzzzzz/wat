package com.jan.wat.controller;

import com.jan.wat.pojo.vo.EquipmentTree;
import com.jan.wat.service.IEquEquipmentgroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation(value = "获取设备树")
    @GetMapping("/getTree/{userCode}")
    public List<EquipmentTree> getTree(@PathVariable String userCode){
        return iEquEquipmentgroupService.createTree(userCode);
    }

    @ApiOperation(value="")
    @GetMapping("/getEquipment/{userCode}/{equipemtnGroupId}")
    public List<String> getEquipment(@PathVariable String userCode, @PathVariable String equipemtnGroupId){
        return iEquEquipmentgroupService.getChildrenId(userCode,equipemtnGroupId);
    }


}
