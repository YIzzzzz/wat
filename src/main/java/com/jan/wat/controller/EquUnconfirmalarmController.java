package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jan.wat.pojo.EquAlarmrecord;
import com.jan.wat.pojo.EquEquipment;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.WatClassify;
import com.jan.wat.pojo.vo.EquAlarmQuery;
import com.jan.wat.pojo.vo.EquAlarmQueryDes;
import com.jan.wat.service.IEquAlarmrecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/unconfirmalarm")
public class EquUnconfirmalarmController {

    @Autowired
    IEquAlarmrecordService iEquAlarmrecordService;

    @ApiOperation(value = "查询原始数据报警确认")
    @GetMapping("/{usercode}/{equipment_id}/{equipmentgroup_id}")
    public List<EquAlarmQuery> getEquUnconfirmalarm(@PathVariable String usercode, @PathVariable String equipment_id, @PathVariable String equipmentgroup_id){
        return iEquAlarmrecordService.getEquUnconfirmalarm(usercode,equipment_id,equipmentgroup_id);
    }

    @ApiOperation(value = "确认报警")
    @PutMapping("/confirmalarm")
    public RespBean confirmAlarm(@RequestBody Integer[] ids){


        LocalDateTime now = LocalDateTime.now();
        for(Integer confirm : ids){
            LambdaUpdateWrapper<EquAlarmrecord> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(EquAlarmrecord::getId, confirm);
            wrapper.set(EquAlarmrecord::getConfirmtime, now);
            if (!iEquAlarmrecordService.update(wrapper)){
                return RespBean.error("更新失败！");
            }
        }
        return RespBean.success("更新成功！");

    }

    @ApiOperation(value = "批量更新描述")
    @PutMapping("/updatedes")
    public RespBean updateDes(@RequestBody EquAlarmQueryDes equAlarmQueryDes){

        Integer id = equAlarmQueryDes.getId();
        String des = equAlarmQueryDes.getDes();
        LambdaUpdateWrapper<EquAlarmrecord> wrapper = new LambdaUpdateWrapper<>();

        wrapper.eq(EquAlarmrecord::getId, id);
        wrapper.set(EquAlarmrecord::getDes, des);
        if(iEquAlarmrecordService.update(wrapper)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
