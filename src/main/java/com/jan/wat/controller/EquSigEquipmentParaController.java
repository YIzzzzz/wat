package com.jan.wat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jan.wat.mapper.EquParaMapper;
import com.jan.wat.pojo.EquEquipmentpara;
import com.jan.wat.pojo.EquPara;
import com.jan.wat.pojo.EquParavalue;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.vo.SigEuipementparaQuery;
import com.jan.wat.service.IEquEquipmentparaService;
import com.jan.wat.service.IEquParaService;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/8/4下午3:23
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/sigequipmentpara")
public class EquSigEquipmentParaController {

    @Autowired
    IEquParaService iEquParaService;


    @Autowired
    IEquEquipmentparaService iEquEquipmentparaService;

    @ApiOperation(value = "获取单设备参数")
    @GetMapping("{equipment_ID}")
    public List<SigEuipementparaQuery> getSigEquipmentPara(@PathVariable String equipment_ID){
        return iEquParaService.getSigEquipmentPara(equipment_ID);
    }

    @ApiOperation(value = "发送")
    @PutMapping("/send")
    public RespBean send(@RequestBody JSONObject json){

        String usercode = (String) json.get("usercode");
//        String data =  (String) json.get("data");
//        JSONArray jsonArray = JSONArray.parseArray(data);
//
//        ArrayList<SigEuipementparaQuery> sigEuipementparaQueries = (ArrayList<SigEuipementparaQuery>) jsonArray.toJavaList(SigEuipementparaQuery.class);

        ArrayList<SigEuipementparaQuery> sigEuipementparaQueries = (ArrayList<SigEuipementparaQuery>)json.get("data");
        if(iEquParaService.sendCommand(sigEuipementparaQueries,usercode))
            return RespBean.success("success!");
        return RespBean.error("error!");

    }


    @ApiOperation(value = "发送命令")
    @PostMapping("/sendCommand")
    public RespBean sendCommand(@RequestBody String usercode, @RequestBody List<SigEuipementparaQuery> data){
        if(iEquParaService.sendCommand(data,usercode))
            return RespBean.success("success!");
        return RespBean.error("error!");
    }

}
