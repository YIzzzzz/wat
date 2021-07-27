package com.jan.wat.controller;

import com.jan.wat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
@CrossOrigin
@Controller
@RequestMapping("/wat")
public class WatController {

    @Autowired
    IWatClassifyService iWatClassifyService;
    @Autowired
    IWatCaliberService iWatCaliberService;
    @Autowired
    IWatFlowtypeService iWatFlowtypeService;
    @Autowired
    IWatAlarmtypeService iWatAlarmtypeService;

//    @RequestMapping("/classify")
//    @ResponseBody
//    public String getClassify(){
//        JSONObject jsonObject = new JSONObject();
//        List<WatClassify> list = iWatClassifyService.list(null);
//
//        jsonObject.put("data",list);
//        return jsonObject.toJSONString();
//    }

//    @RequestMapping("/caliber")
//    @ResponseBody
//    public String getCaliber(){
//        List<WatCaliber> list = iWatCaliberService.list(null);
//        JSONObject jsonObject = new JSONObject();
//
//        jsonObject.put("data",list);
//        return jsonObject.toJSONString();
//
//    }

//    @RequestMapping("/flowtype")
//    @ResponseBody
//    public String getFlowtype(){
//        List<WatFlowtype> list = iWatFlowtypeService.list(null);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("data",list);
//        return jsonObject.toJSONString();
//    }

//    @RequestMapping("alarmtype")
//    @ResponseBody
//    public String getAlarmtype(){
//        JSONObject jsonObject = new JSONObject();
//        List<WatAlarmtype> list = iWatAlarmtypeService.list(null);
//
//        jsonObject.put("data",list);
//        return jsonObject.toJSONString();
//    }
}
