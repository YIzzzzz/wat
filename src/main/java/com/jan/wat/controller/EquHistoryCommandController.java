package com.jan.wat.controller;

import com.jan.wat.pojo.vo.HistoryCommandQuery;
import com.jan.wat.service.IEquCommandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/8/6下午2:41
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/historycommand")
public class EquHistoryCommandController {
    @Autowired
    IEquCommandService iEquCommandService;

    @ApiOperation(value = "")
    @GetMapping("/{usercode}/{equipmentGroupId}/{equipmentId}/{commandtype}/{startTime}/{endTime}")
    public List<HistoryCommandQuery> getHistoryCommand(String usercode, String equipmentGroupId, String equipmentId, String commandtype, String startTime, String endTime){
        return iEquCommandService.getHistoryCommand(usercode,equipmentGroupId,equipmentId,commandtype,startTime,endTime);
    }

}
