package com.jan.wat.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.mapper.EquDatatypeMapper;
import com.jan.wat.mapper.EquEquipmentMapper;
import com.jan.wat.pojo.*;
import com.jan.wat.scheduled.ApplicationContextUtil;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/equ/datatype")
public class EquDatatypeController {

    @Autowired
    IEquDatatypeService iEquDatatypeService;

    @Autowired
    EquDatatypeMapper equDatatypeMapper;


    @ApiOperation(value = "查询数据类型列表")
    @GetMapping("")
    public String getDatatype(){
        JSONObject jsonObject = new JSONObject();
        Page<EquDatatype> page = new Page<>(2, 5);
//        equDatatypeMapper.selectPage(page,null);
        equDatatypeMapper.selectPage(page, null);
        iEquDatatypeService.page(page,null);

//        if(page != null){
//            System.out.println("2");
        page.getRecords().forEach(System.out::println);
//        }else{
        System.out.println(page.getTotal());
//        }
//
//        Page<EquDatatype> EquDataTypePage = iEquDatatypeService.page(page, null);
        jsonObject.put("data",page);
        return jsonObject.toJSONString();

    }
}
