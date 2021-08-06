package com.jan.wat.controller;

import com.jan.wat.pojo.vo.EquFailurecommandQuery;
import com.jan.wat.pojo.vo.EquUncheckcommandQuery;
import com.jan.wat.service.IEquCommandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/equ/failurecommand")
public class EquFailurecommandController {

    @Autowired
    IEquCommandService iEquCommandService;

    @ApiOperation(value = "查询失败命令")
    @GetMapping("/{euipmentGroupID}/{equipmentId}/{userCode}")
    public List<EquFailurecommandQuery> getEuipments(@PathVariable String euipmentGroupID, @PathVariable String equipmentId, @PathVariable String userCode) {
        return iEquCommandService.getEquFailurecommand(userCode, equipmentId, euipmentGroupID);
    }

}
