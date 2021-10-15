package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.vo.EquHistoryUpdateProgramRecord;
import com.jan.wat.service.IEquCommandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;





import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/equ/historyupdateprogramrecord")
public class EquHistoryupdateprogramrecordController {

    @Autowired
    IEquCommandService iEquCommandService;

    @ApiOperation(value = "获取历史记录")
    @PutMapping("/get")
    public List<EquHistoryUpdateProgramRecord> getHistoryupdateprogramrecord(@RequestBody JSONObject json) {

        String usercode = (String) json.get("usercode");

        String status = (String) json.get("status");

        //有就传设备组id，没有就传0
        String equipmentgroup_id = (String) json.get("equipmentgroup_id");

        //有就传设备id，没有就传0
        String equipment_id = (String) json.get("equipment_id");

        //没日期传0,有日期传个不是0的
        String datarange = (String) json.get("datarange");

        //有就传，没有就传""(空字符串)
        String startTime = (String) json.get("starttime");

        //有就传，没有就传""(空字符串)
        String endTime = (String) json.get("endtime");

        return iEquCommandService.getHistoryupdateprogramrecord(usercode, status,equipment_id,equipmentgroup_id,startTime,endTime);
    }
}
