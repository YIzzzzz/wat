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
    @GetMapping("/getRealDataQuery")
    @ResponseBody
    public String getRealDataQuery(@RequestBody long current,@RequestBody long size,@RequestBody String equipmentGroupId,@RequestBody String equipmentId,@RequestBody String usercode) {
        return iEquEquipmentService.getRealDataQuery( current,  size, equipmentGroupId,  equipmentId,  usercode);
    }

}
