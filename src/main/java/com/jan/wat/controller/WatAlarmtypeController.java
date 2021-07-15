package com.jan.wat.controller;


import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.WatAlarmtype;
import com.jan.wat.service.IWatAlarmtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Controller
@RequestMapping("/wat")
public class WatAlarmtypeController {

    @Autowired
    IWatAlarmtypeService iWatAlarmtypeService;

    @RequestMapping("alarmtype")
    @ResponseBody
    public String getAlarmtype(){
        JSONObject jsonObject = new JSONObject();
        List<WatAlarmtype> list = iWatAlarmtypeService.list(null);

        jsonObject.put("data",list);
        return jsonObject.toJSONString();
    }
}
