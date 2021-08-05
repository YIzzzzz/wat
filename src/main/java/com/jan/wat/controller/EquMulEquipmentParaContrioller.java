package com.jan.wat.controller;

import com.jan.wat.pojo.vo.MulEquipparaQuery;
import com.jan.wat.pojo.vo.SigEuipementparaQuery;
import com.jan.wat.service.IEquParaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/8/5下午2:04
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/mulequipmentpara")
public class EquMulEquipmentParaContrioller {

    @Autowired
    IEquParaService iEquParaService;

    @ApiOperation(value = "获取多设备参数")
    @PostMapping("/getSigEquipmentPara/{equipment_IDs}")
    public List<MulEquipparaQuery> getSigEquipmentPara(@PathVariable String equipment_IDs){
        System.out.println(equipment_IDs);
        List<String> equipment_ID = Arrays.asList(equipment_IDs.split(","));
        return iEquParaService.getMulEquipmentPara(equipment_ID);
    }

}
