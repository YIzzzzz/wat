package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.mapper.EquDatatypeMapper;
import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/wat")
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
