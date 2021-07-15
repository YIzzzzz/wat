package com.jan.wat.controller;


import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.WatFlowtype;
import com.jan.wat.service.IWatFlowtypeService;
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
@RequestMapping("/wat-flowtype")
public class WatFlowtypeController {

    @Autowired
    IWatFlowtypeService iWatFlowtypeService;

    @RequestMapping("/flowtype")
    @ResponseBody
    public String getFlowtype(){
        List<WatFlowtype> list = iWatFlowtypeService.list(null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",list);
        return jsonObject.toJSONString();
    }
}
