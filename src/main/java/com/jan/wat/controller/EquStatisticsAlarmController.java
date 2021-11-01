package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.vo.MapQuery;
import com.jan.wat.service.IEquAlarmrecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/10/30下午5:17
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/statisticsalarm")
public class EquStatisticsAlarmController {

    @Autowired
    IEquAlarmrecordService iEquAlarmrecordServicel;

    /**
    * @return: java.util.List<com.jan.wat.pojo.vo.MapQuery>
    * @author: zxj
    * @time: 2021/10/30 下午5:19
     * @description:
    {
        "usercode":"zxj",
        "startTime":"",
        "endTime":"",
        "groupIds":["123","321"],
        "groupNames":["123","312"]
    }
     */
    @ApiOperation(value = "getRealDataQuery")
    @PostMapping("/getJW")
    @ResponseBody
    public String getRealDataQuery(@RequestBody JSONObject json) {
        String usercode = (String) json.get("usercode");
        String startTime = (String) json.get("startTime");
        String endTime = (String) json.get("endTime");
        List<String> groupId = (List<String>)json.get("groupIds");
        List<String> groupName = (List<String>)json.get("groupNames");
        return iEquAlarmrecordServicel.statisticsAlarm(usercode,groupId, groupName, startTime, endTime);
    }
}
