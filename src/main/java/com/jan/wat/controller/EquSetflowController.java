package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jan.wat.pojo.*;
import com.jan.wat.pojo.vo.EquEquipmentQuery;
import com.jan.wat.service.IEquEquipmentService;
import com.jan.wat.service.IWatFlowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/outflow")
public class EquSetflowController {

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @Autowired
    IWatFlowService iWatFlowService;

    @ApiOperation(value = "查询设置水表列表")
    @PostMapping("/getall")
    @ResponseBody
    public List<EquEquipmentQuery> getallEquipment(@RequestBody JSONObject json){

        System.out.println(json.toJSONString());
        String usercode = (String) json.get("usercode");
        String equipment_id = (String) json.get("equipment_id");
        String equipmentgroup_id = (String) json.get("equipmentgroup_id");

        List<EquEquipmentQuery> check = iEquEquipmentService.getEquEquipment(usercode, equipment_id, equipmentgroup_id);
        List<EquEquipmentQuery> ans = new ArrayList<>();

        for(EquEquipmentQuery checkTrue : check){

//            System.out.println(checkTrue.isChecked());

            if(checkTrue.isChecked()){
                ans.add(checkTrue);
            }
        }

        for(EquEquipmentQuery checkFalse : check){
            if(!checkFalse.isChecked()){
                ans.add(checkFalse);
            }
        }

        return ans;
    }


    @ApiOperation(value = "删除和确认")
    @PostMapping("/addanddelete")
    @ResponseBody
    public RespBean addanddelete(@RequestBody JSONObject json){


        ArrayList<String> add = (ArrayList<String>) json.get("add");
        ArrayList<String> delete = (ArrayList<String>) json.get("delete");

        for(String equipment_id : add){
            WatFlow watFlow = new WatFlow();
            watFlow.setEquipmentId(equipment_id);
            if(!iWatFlowService.save(watFlow)){
                return RespBean.error("失败！");
            }
        }

        for(String equipment_id : delete){
            LambdaQueryWrapper<WatFlow> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(WatFlow::getEquipmentId, equipment_id);
            if(!iWatFlowService.remove(wrapper)){
                return RespBean.error("失败！");

            }

        }
        return RespBean.success("成功");
    }
}
