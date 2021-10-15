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
@RequestMapping("/wat/yesterdayrecoveryalarm")
public class WatYesterdayrecoveryalarmController {

    @Autowired
    IWatAlarmrecordService iWatAlarmrecordService;

    @ApiOperation(value = "查询分析数据昨日恢复")
    @GetMapping("/{usercode}")
    public List<WatAlarmQuery> getWatYesterdayrecoveryalarm(@PathVariable String usercode){
        return iWatAlarmrecordService.getWatYesterdayrecoveryalarm(usercode);
    }


}
