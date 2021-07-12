package com.jan.wat.controller;

import com.jan.wat.pojo.EquEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @ResponseBody
    @RequestMapping("/set")
    public String set(String key, String value){

        redisTemplate.opsForValue().set(key,value);

        return "OK";
    }

    @ResponseBody
    @RequestMapping("/get")
    public Object get(String key){

        Object o = redisTemplate.opsForValue().get(key);

        return o;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public boolean delete(String key){

        Boolean delete = redisTemplate.delete(key);

        return delete;
    }

    @ResponseBody
    @RequestMapping("/classTest")
    public String classTest(EquEquipment equEquipment){
        redisTemplate.opsForList().leftPush("DataList", equEquipment);

        return "OK";
    }

    @ResponseBody
    @RequestMapping("/listPop")
    public Object listPop(){
        List dataList = redisTemplate.opsForList().range("DataList", 0, -1);
        return dataList;
    }

}
