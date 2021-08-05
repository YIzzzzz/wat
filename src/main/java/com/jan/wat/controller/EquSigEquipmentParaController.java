package com.jan.wat.controller;

import com.jan.wat.mapper.EquParaMapper;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.vo.SigEuipementparaQuery;
import com.jan.wat.service.IEquParaService;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "获取单设备参数")
    @GetMapping("{equipment_ID}")
    public List<SigEuipementparaQuery> getSigEquipmentPara(@PathVariable String equipment_ID){
        return iEquParaService.getSigEquipmentPara(equipment_ID);
    }

    @ApiOperation(value = "发送命令")
    @PostMapping("/sendCommand/{userCode}/{queries}")
    public RespBean sendCommand(@PathVariable String userCode, @PathVariable List<SigEuipementparaQuery> queries){
        if(iEquParaService.sendCommand(queries,userCode))
            return RespBean.success("success!");
        return RespBean.error("error!");
    }

}
