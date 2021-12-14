package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.service.IEquEquipmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/12/14下午2:06
 * @description
 */
@CrossOrigin
@Controller
@RequestMapping("/equ/equHistoryAccumulateMonthReport")
public class EquHistoryAccumulateMonthReportController {
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
        "startTime": "2021-01",
        "endTime": "2022-01"
    }
     */

    @ApiOperation(value = "getAccumulateData")
    @PostMapping("getAccumulateData")
    @ResponseBody
    public String getAccumulateData(@RequestBody JSONObject json){
        List<String> equipmentIds = (List<String>) json.get("id");
        String startTime = (String)json.get("startTime")+"-01 00:00:00";
        String endTime = (String)json.get("endTime")+"-01 00:00:00";
//        endTime = DateTime.getTime(endTime).plusMonths(1).format(DateTime.getPattern());
        return iEquEquipmentService.getAccumulateDataMonth(equipmentIds, startTime, endTime);
    }
}
