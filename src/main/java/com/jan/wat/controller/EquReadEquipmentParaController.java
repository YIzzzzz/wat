package com.jan.wat.controller;

import com.jan.wat.pojo.vo.EquipmentTree;
import com.jan.wat.pojo.vo.EuipmentsQuery;
import com.jan.wat.service.IEquEquipmentService;
import com.jan.wat.service.IEquEquipmentgroupService;
import com.jan.wat.service.IEquParaService;
import com.jan.wat.service.IEquParavalueService;
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
    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @ApiOperation(value = "获取设备树")
    @GetMapping("/getTree/{userCode}")
    public List<EquipmentTree> getTree(@PathVariable String userCode){
        return iEquEquipmentgroupService.createTree(userCode);
    }

    @ApiOperation(value="获取设备列表")
    @GetMapping("/getEquipmentList/{userCode}/{equipemtnGroupId}")
    public List<String> getEquipmentList(@PathVariable String userCode, @PathVariable String equipemtnGroupId){
        return iEquEquipmentgroupService.getChildrenId(userCode,equipemtnGroupId);
    }



    @ApiOperation(value = "查询参数值管理")
    @GetMapping("/getequipments/{euipmentGroupID}/{equipmentId}/{userCode}")
    public List<EuipmentsQuery> getEuipments(@PathVariable String euipmentGroupID, @PathVariable String equipmentId, @PathVariable String userCode){
        return iEquEquipmentService.getEuipments(euipmentGroupID, equipmentId,userCode);
    }


}
