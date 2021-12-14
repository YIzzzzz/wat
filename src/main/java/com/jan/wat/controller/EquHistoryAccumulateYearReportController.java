package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.service.IEquEquipmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/12/14下午4:28
 * @description
 */
@CrossOrigin
@Controller
@RequestMapping("/equ/equHistoryAccumulateYearReport")
public class EquHistoryAccumulateYearReportController {
    @Autowired
    IEquEquipmentService iEquEquipmentService;
    /**
     * @return: java.lang.String
     * @author: zxj
     * @time: 2021/12/12 下午8:20
     * @description:
    {
    "id": [
    "8944",
    "9901",
    "9902"
    ],
    "startTime": "2021",
    "endTime": "2022"
    }
     */

    @ApiOperation(value = "getAccumulateData")
    @PostMapping("getAccumulateData")
    @ResponseBody
    public String getAccumulateData(@RequestBody JSONObject json){
        List<String> equipmentIds = (List<String>) json.get("id");
        String startTime = (String)json.get("startTime")+"-01-01 00:00:00";
        String endTime = (String)json.get("endTime")+"-01-01 00:00:00";
//        endTime = DateTime.getTime(endTime).plusMonths(1).format(DateTime.getPattern());
        return iEquEquipmentService.getAccumulateDataYear(equipmentIds, startTime, endTime);
    }
}
