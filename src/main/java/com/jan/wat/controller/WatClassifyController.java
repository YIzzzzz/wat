package com.jan.wat.controller;


import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.WatClassify;
import com.jan.wat.service.IWatClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/wat-classify")
public class WatClassifyController {

    @Autowired
    IWatClassifyService iWatClassifyService;

    @RequestMapping("/classify")
    @ResponseBody
    public String getClassify(){
        JSONObject jsonObject = new JSONObject();
        List<WatClassify> list = iWatClassifyService.list(null);

        jsonObject.put("data",list);
        return jsonObject.toJSONString();
    }
}
