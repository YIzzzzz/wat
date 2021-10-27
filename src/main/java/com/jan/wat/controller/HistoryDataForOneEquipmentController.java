package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.service.IEquEquipmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @hor:ZXJ
 * @time:2021/10/27下午4:17
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/historydataforoneequipmentcontroller")
public class HistoryDataForOneEquipmentController {

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    /**
    * @return: com.jan.wat.pojo.RespBean
    * @author: zxj
    * @time: 2021/10/27 下午4:19
     * @description
    {
        "usercode":"zxj",
        "id":"77777777",
        "startTime":"",
        "endTime":""
    }
     */

    @ApiOperation(value = "getValue")
    @PostMapping("/getValue")
    @ResponseBody
    public String getValue(@RequestBody JSONObject json){
        String usercode = (String) json.get("usercode");
        String id = (String) json.get("id");
        String startTime = (String) json.get("startTime");
        String endTime = (String) json.get("endTime");
        return iEquEquipmentService.getValue(usercode, id, startTime, endTime);

    }
}
