package com.jan.wat.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jan.wat.mapper.WatFlowMapper;
import com.jan.wat.pojo.*;
import com.jan.wat.pojo.vo.FlowViewQuery;
import com.jan.wat.service.IWatAlarmtypeFlowMapService;
import com.jan.wat.service.IWatFlowService;
import com.jan.wat.service.IWatFlowtypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@Controller
@RequestMapping("/wat/flow")
public class WatFlowController {

    @Autowired
    IWatFlowService iWatFlowService;

    @Autowired
    IWatAlarmtypeFlowMapService iWatAlarmtypeFlowMapService;

    @ApiOperation(value = "查询水表管理")
    @PostMapping("/getall")
    @ResponseBody
    public List<FlowViewQuery> getAllFlow(@RequestBody JSONObject json){

        String usercode = (String) json.get("usercode");
        String equipment_id = (String) json.get("equipment_id");
        String equipmentgroup_id = (String) json.get("equipmentgroup_id");

        return iWatFlowService.getAllFlow(usercode,equipment_id,equipmentgroup_id);
    }

    @ApiOperation(value = "修改水表管理")
    @PutMapping("/update")
    @ResponseBody
    public RespBean updateFlow(@RequestBody JSONObject json){

        Integer caliber_id = (Integer) json.get("caliber_id");
        Integer classify_id = (Integer) json.get("classify_id");
        String model = (String) json.get("model");
        String clientname = (String) json.get("clientname");
        String bookno = (String) json.get("bookno");

//        String price1 = String.valueOf(json.get("price"));
//        String areas1 = String.valueOf(json.get("areas"));
//        String persons1 = String.valueOf(json.get("persons"));
//        System.out.println("====="+ price1);
//        System.out.println("====="+ areas1);
//        System.out.println("====="+ persons1);

        double price = Double.valueOf(String.valueOf(json.get("price")));
        double areas = Double.valueOf(String.valueOf(json.get("areas")));
        double persons = Double.valueOf(String.valueOf(json.get("persons")));
//        double price = ((Integer)json.get("price")).doubleValue();
//        double areas = ((Integer)json.get("areas")).doubleValue();
//        double persons = ((Integer)json.get("persons")).doubleValue();
//        double areas = (double) json.get("areas");
//        double persons = (double) json.get("persons");
        Integer category = (Integer) json.get("category");
        boolean status = (boolean) json.get("status");
        Integer flowtype_id = (Integer) json.get("flowtype_id");
        String des = (String) json.get("des");
        String siteno = (String) json.get("siteno");
        String storemode = (String) json.get("storemode");
        String equipment_id = (String) json.get("equipment_id");

        LambdaUpdateWrapper<WatFlow> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(WatFlow::getEquipmentId,equipment_id);
        wrapper.set(WatFlow::getCaliberId,caliber_id);
        wrapper.set(WatFlow::getClassifyId,classify_id);
        wrapper.set(WatFlow::getModel,model);
        wrapper.set(WatFlow::getClientname,clientname);
        wrapper.set(WatFlow::getBookno,bookno);
        wrapper.set(WatFlow::getPrice,price);
        wrapper.set(WatFlow::getAreas,areas);
        wrapper.set(WatFlow::getPersons,persons);
        wrapper.set(WatFlow::getCategory,category);
        wrapper.set(WatFlow::getFlowtypeId,flowtype_id);
        wrapper.set(WatFlow::getDes,des);
        wrapper.set(WatFlow::getSiteno,siteno);
        wrapper.set(WatFlow::getStoremode,storemode);
        if(iWatFlowService.update(wrapper)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");

    }

    @ApiOperation(value = "查询报警类型id")
    @PostMapping("/getalarmtypeid/{equipment_id}")
    @ResponseBody
    public List<Integer> getAlarmtypeid(@PathVariable String equipment_id){

        return iWatFlowService.getAlarmtypeid(equipment_id);
    }

    @ApiOperation(value = "设置报警类型1")
    @PutMapping("/setalarmtype1")
    @ResponseBody
    public RespBean setalarmtype1(@RequestBody JSONObject json) {

        Integer alarmtype_id = (Integer) json.get("alarmtype_id");
        ArrayList<String> delEquipment_id = (ArrayList<String>) json.get("delete");
        ArrayList<String> addEquipment_id = (ArrayList<String>) json.get("add");

        if(delEquipment_id != null){
            for(String equipment_id : delEquipment_id){
                LambdaQueryWrapper<WatAlarmtypeFlowMap> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(WatAlarmtypeFlowMap::getAlarmtypeId, alarmtype_id);
                wrapper.eq(WatAlarmtypeFlowMap::getEquipmentId, equipment_id);
                if(!iWatAlarmtypeFlowMapService.remove(wrapper)){
                    return RespBean.error("删除失败！");
                }
            }
        }
        if(addEquipment_id != null){
            for(String equipment_id : addEquipment_id){
                WatAlarmtypeFlowMap watAlarmtypeFlowMap = new WatAlarmtypeFlowMap();
                watAlarmtypeFlowMap.setAlarmtypeId(alarmtype_id);
                watAlarmtypeFlowMap.setEquipmentId(equipment_id);
                if(!iWatAlarmtypeFlowMapService.save(watAlarmtypeFlowMap)){
                    return RespBean.error("删除失败！");
                }
            }
        }
        return RespBean.success("成功");

    }

    @ApiOperation(value = "设置报警类型2")
    @PutMapping("/setalarmtype2")
    @ResponseBody
    public RespBean setalarmtype2(@RequestBody JSONObject json) {

        String equipment_id = (String) json.get("equipment_id");
        ArrayList<Integer> delAlarmtype_id = (ArrayList<Integer>) json.get("delete");
        ArrayList<Integer> addAlarmtype_id = (ArrayList<Integer>) json.get("add");

        if(delAlarmtype_id != null){
            for(Integer alarmtype_id : delAlarmtype_id){
                LambdaQueryWrapper<WatAlarmtypeFlowMap> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(WatAlarmtypeFlowMap::getAlarmtypeId, alarmtype_id);
                wrapper.eq(WatAlarmtypeFlowMap::getEquipmentId, equipment_id);
                if(!iWatAlarmtypeFlowMapService.remove(wrapper)){
                    return RespBean.error("删除失败！");
                }
            }
        }
        if(addAlarmtype_id != null){
            for(Integer alarmtype_id : addAlarmtype_id){
                WatAlarmtypeFlowMap watAlarmtypeFlowMap = new WatAlarmtypeFlowMap();
                watAlarmtypeFlowMap.setAlarmtypeId(alarmtype_id);
                watAlarmtypeFlowMap.setEquipmentId(equipment_id);
                if(!iWatAlarmtypeFlowMapService.save(watAlarmtypeFlowMap)){
                    return RespBean.error("删除失败！");
                }
            }
        }
        return RespBean.success("成功");

    }
}
