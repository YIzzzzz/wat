package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.vo.AccumulateDataQuery;
import com.jan.wat.service.IEquDatatypeService;
import com.jan.wat.service.IEquEquipmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/12/10下午2:53
 * @description
 */
@CrossOrigin
@Controller
@RequestMapping("/equ/equHistoryAccumulateDayReport")
public class EquHistoryAccumulateDayReportController {

    @Autowired
    IEquEquipmentService iEquEquipmentService;
    /**
    * @return: java.lang.String
    * @author: zxj
    * @time: 2021/12/12 下午8:20
     * @description:
    {
        "id":"8944",
        "startTime":"2021-01-05",
        "endTime":"2022-01-05",
        "interval":"5"
    }
     */

    @ApiOperation(value = "getAccumulateData")
    @PostMapping("getAccumulateData")
    @ResponseBody
    public String getAccumulateData(@RequestBody JSONObject json){
        String equipmentId = (String) json.get("id");
        String startTime = (String)json.get("startTime")+" 00:00:00";
        String endTime = (String)json.get("endTime")+" 00:00:00";
        int interval = Integer.valueOf((String)json.get("interval"));

        return iEquEquipmentService.getAccumulateData(equipmentId, startTime, endTime, interval);
    }
}
