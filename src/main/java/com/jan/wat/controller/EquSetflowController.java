package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.EquEquipment;
import com.jan.wat.service.IEquEquipmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/setflow")
public class EquSetflowController {

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @ApiOperation(value = "查询设置水表列表")
    @GetMapping("getall")
    public List<EquEquipment> getallEquipment(@RequestBody JSONObject json){

        String usercode = (String) json.get("usercode");
        String equipment_id = (String) json.get("equipment_id");
        String equipmentgroup_id = (String) json.get("equipmentgroup_id");

        return iEquEquipmentService.getEquEquipment(usercode, equipment_id, equipmentgroup_id);

    }
}
