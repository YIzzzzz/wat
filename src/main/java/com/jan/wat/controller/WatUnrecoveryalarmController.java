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
@RequestMapping("/wat/unrecoveryalarm")
public class WatUnrecoveryalarmController {

    @Autowired
    IWatAlarmrecordService iWatAlarmrecordService;

    @ApiOperation(value = "查询分析数据当前报警")
    @GetMapping("/{usercode}")
    public List<WatAlarmQuery> getWatUnrecoveryalarm(@PathVariable String usercode){
        return iWatAlarmrecordService.getWatUnrecoveryalarm(usercode);
    }

}
