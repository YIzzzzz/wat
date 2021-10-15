package com.jan.wat.controller;

import com.jan.wat.mapper.EquAlarmrecordMapper;
import com.jan.wat.pojo.SysOrganize;
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
@RequestMapping("/equ/yesterdayalarm")
public class EquYesterdayalarmController {

    @Autowired
    IEquAlarmrecordService iEquAlarmrecordService;

    @ApiOperation(value = "查询原始数据昨日报警")
    @GetMapping("/{usercode}/{equipment_id}/{equipmentgroup_id}")
    public List<EquAlarmQuery> getEquYesterdayalarm(@PathVariable String usercode, @PathVariable String equipment_id, @PathVariable String equipmentgroup_id){
//        usercode = "shbf";
//        equipment_id = "0";
//        equipmentgroup_id = "0";
        return iEquAlarmrecordService.getEquYesterdayalarm(usercode, equipment_id, equipmentgroup_id);
    }

}
