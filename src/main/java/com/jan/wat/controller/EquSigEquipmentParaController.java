package com.jan.wat.controller;

import com.jan.wat.mapper.EquParaMapper;
import com.jan.wat.pojo.vo.SigEuipementparaQuery;
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
    EquParaMapper equParaMapper;

    @ApiOperation(value = "获取单设备参数")
    @GetMapping("{equipment_ID}")
    public List<SigEuipementparaQuery> getSigEquipmentPara(@PathVariable String equipment_ID){
        return equParaMapper.getSigEquipmentPara(equipment_ID);
    }

}
