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
public class EquUncheckcommandController {
    @Autowired
    IEquCommandService iEquCommandService;

    @ApiOperation(value = "查询命令确认")
    @GetMapping("/{euipmentGroupID}/{equipmentId}/{userCode}")
    public List<EquUncheckcommandQuery> getEuipments(@PathVariable String euipmentGroupID, @PathVariable String equipmentId, @PathVariable String userCode){
        return iEquCommandService.getEquUncheckcommand(userCode, equipmentId,euipmentGroupID);
    }
}
