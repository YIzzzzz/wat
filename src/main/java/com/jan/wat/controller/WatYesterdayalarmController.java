package com.jan.wat.controller;

import com.jan.wat.pojo.vo.EquAlarmQuery;
import com.jan.wat.pojo.vo.WatAlarmQuery;
import com.jan.wat.service.IWatAlarmrecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/wat/yesterdayalarm")
public class WatYesterdayalarmController {


    @Autowired
    IWatAlarmrecordService iWatAlarmrecordService;

    @ApiOperation(value = "查询分析数据昨日报警")
    @GetMapping("/{usercode}")
    public List<WatAlarmQuery> getWatYesterdayalarm(@PathVariable String usercode){
        return iWatAlarmrecordService.getWatYesterdayalarm(usercode);
    }

}
