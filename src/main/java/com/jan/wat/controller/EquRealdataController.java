package com.jan.wat.controller;

import com.jan.wat.service.IEquEquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @hor:ZXJ
 * @time:2021/8/9下午3:53
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("equ/realdata")
public class EquRealdataController {

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @ApiOperation(value = "getRealDataQuery")
    @GetMapping("{current}/{size}/{equipmentGroupId}/{equipmentId}/{usercode}")
    @ResponseBody
    public String getRealDataQuery(@PathVariable long current,@PathVariable long size,@PathVariable String equipmentGroupId,@PathVariable String equipmentId,@PathVariable String usercode) {
        return iEquEquipmentService.getRealDataQuery( current,  size, equipmentGroupId,  equipmentId,  usercode);
    }

}
