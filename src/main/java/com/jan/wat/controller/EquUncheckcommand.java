package com.jan.wat.controller;

import com.jan.wat.pojo.vo.EquUncheckcommandQuery;
import com.jan.wat.pojo.vo.EuipmentsQuery;
import com.jan.wat.service.IEquCommandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/equ/uncheckcommand")
public class EquUncheckcommand {
    @Autowired
    IEquCommandService iEquCommandService;

    @ApiOperation(value = "")
    @GetMapping("/getequipments/{euipmentGroupID}/{equipmentId}/{userCode}")
    public List<EquUncheckcommandQuery> getEuipments(@PathVariable String euipmentGroupID, @PathVariable String equipmentId, @PathVariable String userCode){
        return iEquCommandService.getEquUncheckcommand(userCode, equipmentId,euipmentGroupID);
    }
}
