package com.jan.wat.controller;


import com.alibaba.fastjson.JSONObject;
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
@RequestMapping("/equ/historyalarm")
public class EquHistoryalarmController {

    @Autowired
    IEquAlarmrecordService iEquAlarmrecordService;

    @ApiOperation(value = "查询原始数据报警数据")
    @PutMapping("/getall")
    public List<EquAlarmQuery> getEquHistroyalarm(@RequestBody JSONObject json){

        String usercode = (String) json.get("usercode");
        String equipmentgroup_id = (String) json.get("equipmentgroup_id");
        String equipment_id = (String) json.get("equipment_id");
        //  设备报警传1，离线报警传2，数据超限报警传3，全部传0
        String alarmtype = (String) json.get("alarmtype");

        //没时间传空字符串 ""
        String startTime = (String) json.get("startTime");
        String endTime = (String) json.get("endTime");
        return iEquAlarmrecordService.getEquhistoryalarm(usercode,equipment_id,equipmentgroup_id,alarmtype,startTime,endTime);
    }

//    @ApiOperation(value = "查询原始数据报警数据")
//    @GetMapping("/{usercode}/{equipment_id}/{equipmentgroup_id}/{alarmtype}/{daterange}/{start}/{end}")
//    public List<EquAlarmQuery> getEquHistroyalarm(@PathVariable String usercode, @PathVariable String equipment_id, @PathVariable String equipmentgroup_id, String alarmtype, String daterange, String start, String end){
//        return iEquAlarmrecordService.getEquhistoryalarm(usercode,equipment_id,equipmentgroup_id,alarmtype,daterange,start,end);
//    }

}
