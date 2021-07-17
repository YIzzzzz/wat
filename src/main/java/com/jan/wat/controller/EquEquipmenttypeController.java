package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.mapper.EquDatatypeMapper;
import com.jan.wat.mapper.EquEquipmentMapper;
import com.jan.wat.pojo.*;
import com.jan.wat.scheduled.ApplicationContextUtil;
import com.jan.wat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/equ")
public class EquEquipmenttypeController {

    @Autowired
    IEquEquipmenttypeService iEquEquipmenttypeService;

    @ResponseBody
    @RequestMapping("/equipmenttype")
    public String getEquipmenttype(){
        JSONObject jsonObject = new JSONObject();
        List<EquEquipmenttype> list = iEquEquipmenttypeService.list(null);
        jsonObject.put("data",list);
        return jsonObject.toJSONString();
    }
}
