package com.jan.wat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.vo.MulEquipparaQuery;
import com.jan.wat.pojo.vo.SigEuipementparaQuery;
import com.jan.wat.service.IEquParaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    /**
    * @return: java.util.List<com.jan.wat.pojo.vo.MulEquipparaQuery>
    * @author: zxj
    * @time: 2021/10/26 上午10:26
     * @description:
    {
        "ids":["123","456"]
    }
     */
    @ApiOperation(value = "获取多设备参数")
    @PostMapping("/getSigEquipmentPara")
    @ResponseBody
    public List<MulEquipparaQuery> getSigEquipmentPara(@RequestBody JSONObject json){
        ArrayList<String> equipment_ID = (ArrayList<String>) json.get("ids");
        return iEquParaService.getMulEquipmentPara(equipment_ID);
    }
    /**
    * @return: com.jan.wat.pojo.RespBean
    * @author: zxj
    * @time: 2021/10/25 下午4:51
     * @description:
    {
        "usercode":"zxj",
        "data":[
            {
              "para_ID":""，
              "pType":""，
              "para_Name":""，
              "UpLimit":""，
              "DownLimit":""，
              "ChangeValue":""
            },
            {
            }
        ]
        "ids":["123","456"]
    }
     */
    @ApiOperation(value = "发送")
    @PostMapping("/send")
    @ResponseBody
    public RespBean send(@RequestBody JSONObject json){

        String usercode = (String) json.get("usercode");
        String data =  (String) json.get("data");
//        String equIds = (String) json.get("ids");
        JSONArray jsonArray = JSONArray.parseArray(data);
        ArrayList<MulEquipparaQuery> queries = (ArrayList<MulEquipparaQuery>) jsonArray.toJavaList(MulEquipparaQuery.class);
        List<String> ids = (ArrayList<String>) json.get("ids");
        ArrayList<SigEuipementparaQuery> sigEuipementparaQueries = new ArrayList<>();
        for(MulEquipparaQuery query : queries){
            SigEuipementparaQuery item = new SigEuipementparaQuery(query,"");
            sigEuipementparaQueries.add(item);
        }
        for(String id : ids){
            for(SigEuipementparaQuery query : sigEuipementparaQueries){
                   query.setEquipment_ID(id);
            }
            if(iEquParaService.sendCommand(sigEuipementparaQueries, usercode))
                return RespBean.success("success!");
        }

        return RespBean.error("error!");

    }

}
