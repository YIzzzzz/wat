package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.vo.FailureAndHistoryCommandQuery;
import com.jan.wat.service.IEquCommandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "获取历史命令")
    @PutMapping("/get")
    public List<FailureAndHistoryCommandQuery> getHistoryCommand(@RequestBody JSONObject json){

        String usercode = (String) json.get("usercode");

        //有就传设备组id，没有就传0
        String equipmentGroupId = (String) json.get("equipmentgroup_id");

        //有就传设备id，没有就传0
        String equipmentId = (String) json.get("equipment_id");

        //全部传0,更新程序传140，读取参数传145，读取参数值传146，设置参数值传147，矫正时间传148
        Integer commandtype = (Integer) json.get("commandtype");

        //有就传，没有就传""(空字符串)
        String startTime = (String) json.get("starttime");

        //有就传，没有就传""(空字符串)
        String endTime = (String) json.get("endtime");

        return iEquCommandService.getHistoryCommand(usercode,equipmentGroupId,equipmentId,commandtype,startTime,endTime);
    }



}
