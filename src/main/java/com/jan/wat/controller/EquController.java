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
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/equ")
public class EquController {


    @Autowired
    EquEquipmentMapper equEquipmentMapper;

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @Autowired
    RedisTemplate redisTemplate;


    @ResponseBody
    @RequestMapping("/getAll")
    public String getAll(){
        JSONObject json = new JSONObject();
        json.put("findByNoteId",equEquipmentMapper.selectList(null));
        return json.toJSONString();
    }
    @ResponseBody
    @RequestMapping("/insert")
    public String insert(EquEquipment equEquipment){
        equEquipmentMapper.insert(equEquipment);
        return "OK";
    }
    @ResponseBody
    @RequestMapping("/redisInsert")
    public String redisInsert(EquEquipment equEquipment){

        Long equEquipmentList = redisTemplate.opsForList().leftPush("equList", equEquipment);

        return String.valueOf(equEquipmentList);
    }

    //    @Scheduled(cron="5/5 * * * * ?")
    public void insertDB(){

        IEquEquipmentService iEquEquipmentService = (IEquEquipmentService) ApplicationContextUtil.getBean("EquEquipmentServiceImpl");
        /**
         * 存在问题：读出和删除之间容易被插入数据。
         * 解决办法：
         */
        List<EquEquipment> dataList = redisTemplate.opsForList().range("equList", 0, -1);
        if(null != dataList){
            redisTemplate.delete("equList");
        }
        System.out.println(dataList);
        if(null != dataList){
            iEquEquipmentService.saveBatch(dataList);
        }
    }
}
