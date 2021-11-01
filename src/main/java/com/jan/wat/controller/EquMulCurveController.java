package com.jan.wat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.EquDatatype;
import com.jan.wat.service.IEquDatatypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/10/30下午8:38
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/mulcurve")
public class EquMulCurveController {

    @Autowired
    IEquDatatypeService iEquDatatypeService;

    @ApiOperation(value = "getRealDataQuery")
    @PostMapping("/getRealDataQuery")
    @ResponseBody
    public List<EquDatatype> getRealDataQuery(@RequestBody List<String> list) {

        return iEquDatatypeService.getMulDataType(list);
    }

    /**
     * @return: java.lang.String
     * @author: zxj
     * @time: 2021/10/30 下午8:06
     * @description:
    {
        "usercode":"zxj",
        "equipment_ids":["77777777","66666666"],
        "equipment_names":["77","66"],
        "datatype_id":"3",
        "startTime":"2021-05-01 10:10:10",
        "endTime":"2021-07-01 10:10:10"
    }
     */

    @ApiOperation(value = "getMulCurve")
    @PostMapping("/getMulCurve/")
    @ResponseBody
    public String getMulCurve(@RequestBody JSONObject json) {
        String usercode = (String) json.get("usercode");
        List<String> equipment_ids = (List<String>) json.get("equipment_ids");
        List<String> equipment_names = (List<String>) json.get("equipment_names");
        String datatype_id = (String) json.get("datatype_id");
        String startTime = (String) json.get("startTime");
        String endTime = (String) json.get("endTime");

        JSONArray object = new JSONArray();

        for(int i = 0; i < equipment_ids.size(); ++i){
            JSONObject realCurve = iEquDatatypeService.getRealCurve(usercode, equipment_ids.get(i), datatype_id, startTime, endTime);
            realCurve.put("name", equipment_names.get(i));
            object.add(realCurve);
        }
        return object.toJSONString();
    }
}
