package com.jan.wat.controller;


import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.WatCaliber;
import com.jan.wat.service.IWatCaliberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@Controller
@RequestMapping("/wat-caliber")
public class WatCaliberController {

    @Autowired
    IWatCaliberService iWatCaliberService;

    @RequestMapping("/caliber")
    @ResponseBody
    public String getCaliber(){
        List<WatCaliber> list = iWatCaliberService.list(null);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("data",list);
        return jsonObject.toJSONString();

    }
}
