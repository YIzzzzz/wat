package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jan.wat.pojo.EquCommand;
import com.jan.wat.pojo.EquEquipment;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.vo.EquUncheckcommandQuery;
import com.jan.wat.pojo.vo.EuipmentsQuery;
import com.jan.wat.service.IEquCommandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/equ/uncheckcommand")
public class EquUncheckcommandController {
    @Autowired
    IEquCommandService iEquCommandService;

    @ApiOperation(value = "查询命令确认")
    @GetMapping("/{euipmentGroupID}/{equipmentId}/{userCode}")
    public List<EquUncheckcommandQuery> getEuipments(@PathVariable String euipmentGroupID, @PathVariable String equipmentId, @PathVariable String userCode){
        return iEquCommandService.getEquUncheckcommand(userCode, equipmentId,euipmentGroupID);
    }

    @ApiOperation(value = "确认描述")
    @GetMapping("/checkdes")
    public RespBean checkDes(@RequestBody JSONObject json){

        String equipment_id = (String) json.get("equipment_id");
        String checkdes = (String) json.get("checkdes");
        //LocalDateTime now = LocalDateTime.now();
        LambdaUpdateWrapper<EquCommand> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(EquCommand::getId,equipment_id);
        wrapper.set(EquCommand::getCheckdescribe, checkdes);
        //wrapper.set(EquCommand::getChecktime, now);
        if(iEquCommandService.update(wrapper)){
            return RespBean.success("成功！");
        }
        return RespBean.error("失败！");
    }

    @ApiOperation(value = "确认报警")
    @GetMapping("/checkcommand")
    public RespBean checkcommand(@RequestBody JSONObject json){

        String equipment_id = (String) json.get("equipment_id");
        LocalDateTime now = LocalDateTime.now();
        LambdaUpdateWrapper<EquCommand> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(EquCommand::getId,equipment_id);
        wrapper.set(EquCommand::getChecktime, now);
        if(iEquCommandService.update(wrapper)){
            return RespBean.success("成功！");
        }
        return RespBean.error("失败！");
    }

    @ApiOperation(value = "确认全部报警")
    @GetMapping("/checkallcommand")
    public RespBean checkallcommand(@RequestBody JSONObject json){


        ArrayList<String> equipment_ids = (ArrayList<String>) json.get("equipment_id");
        for(String equipment_id : equipment_ids){
            LambdaUpdateWrapper<EquCommand> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(EquCommand::getId,equipment_id);
            LocalDateTime now = LocalDateTime.now();
            wrapper.set(EquCommand::getChecktime, now);

            if(!iEquCommandService.update(wrapper)){
                return RespBean.error("失败！");

            }
        }
        return RespBean.success("成功");
    }




}
