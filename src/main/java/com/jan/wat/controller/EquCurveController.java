package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.EquDatatype;
import com.jan.wat.pojo.vo.MapQuery;
import com.jan.wat.service.IEquDatatypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/10/30下午7:09
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/curve")
public class EquCurveController {

    @Autowired
    IEquDatatypeService iEquDatatypeService;


    @ApiOperation(value = "getRealDataQuery")
    @GetMapping("/getRealDataQuery/{equipment_id}")
    @ResponseBody
    public List<EquDatatype> getRealDataQuery(@PathVariable String equipment_id) {
        return iEquDatatypeService.getEquDataType(equipment_id);
    }

    /**
    * @return: java.lang.String
    * @author: zxj
    * @time: 2021/10/30 下午8:06
     * @description:
    {
        "usercode":"zxj",
        "equipment_id":"77777777",
        "datatype_id":"3",
        "startTime":"2021-05-01 10:10:10",
        "endTime":"2021-07-01 10:10:10"
    }
     */

    @ApiOperation(value = "getRealCurve")
    @PostMapping("/getRealCurve/")
    @ResponseBody
    public String getRealCurve(@RequestBody JSONObject json) {
        String usercode = (String) json.get("usercode");
        String equipment_id = (String) json.get("equipment_id");
        String datatype_id = (String) json.get("datatype_id");
        String startTime = (String) json.get("startTime");
        String endTime = (String) json.get("endTime");

        return iEquDatatypeService.getRealCurve(usercode, equipment_id, datatype_id, startTime, endTime).toJSONString();
    }


}
