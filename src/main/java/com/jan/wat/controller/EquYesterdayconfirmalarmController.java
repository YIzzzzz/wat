package com.jan.wat.controller;


import com.jan.wat.pojo.vo.EquAlarmQuery;
import com.jan.wat.service.IEquAlarmrecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/yesterdayconfirmalarm")
public class EquYesterdayconfirmalarmController {

    @Autowired
    IEquAlarmrecordService iEquAlarmrecordService;

    @ApiOperation(value = "查询原始数据昨日确认")
    @GetMapping("/{usercode}/{equipment_id}/{equipmentgroup_id}")
    public List<EquAlarmQuery> getEquYesterdayconfirmalarm(@PathVariable String usercode, @PathVariable String equipment_id, @PathVariable String equipmentgroup_id){
        return iEquAlarmrecordService.getEquYesterdayconfirmalarm(usercode,equipment_id,equipmentgroup_id);
    }
}
