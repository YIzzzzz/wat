package com.jan.wat.controller;

import com.jan.wat.pojo.vo.MapQuery;
import com.jan.wat.service.IEquEquipmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/10/30下午2:07
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/map")
public class EquMapController {

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @ApiOperation(value = "getRealDataQuery")
    @GetMapping("/getJW/{usercode}")
    @ResponseBody
    public List<MapQuery> getRealDataQuery(@PathVariable String usercode) {
        return iEquEquipmentService.getJW(usercode);
    }

}
