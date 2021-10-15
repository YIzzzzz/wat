package com.jan.wat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jan.wat.pojo.EquServer;
import com.jan.wat.pojo.EquServerEquipmentMap;
import com.jan.wat.pojo.SysRole;
import com.jan.wat.pojo.SysUserrolemap;
import com.jan.wat.pojo.vo.EquServerQuery;
import com.jan.wat.pojo.vo.EquServerequipmentsQuery;
import com.jan.wat.service.IEquServerEquipmentMapService;
import com.jan.wat.service.IEquServerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/equ/serverequipments")
public class EquServerequipmentsController {


    @Autowired
    IEquServerService iEquServerService;

    @Autowired
    IEquServerEquipmentMapService iEquServerEquipmentMapService;

    @ApiOperation(value = "查询转发数据类型")
    @GetMapping("/getall/{server_id}")
    public List<EquServerequipmentsQuery> getAllServerequipments(@PathVariable Integer server_id){

        LambdaQueryWrapper<EquServerEquipmentMap> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EquServerEquipmentMap::getServerId, server_id);
        List<EquServerEquipmentMap> equServerEquipmentMaps = iEquServerEquipmentMapService.list(wrapper);

        List<EquServerequipmentsQuery> equServerequipmentsQueryList = new ArrayList<>();

        for(EquServerEquipmentMap equServerEquipmentMap : equServerEquipmentMaps){

            String equipment_id = equServerEquipmentMap.getEquipmentId();
            List<EquServerQuery> equServerQueryList = iEquServerService.getList(equipment_id);

            EquServerequipmentsQuery equServerequipmentsQuery = new EquServerequipmentsQuery();
            equServerequipmentsQuery.setEquipment_id(equipment_id);

            String data = "";
            for(EquServerQuery equServerQuery : equServerQueryList){
                if(!equServerQuery.getUnitname().equals("")){
                    data += equServerQuery.getDatatypename() + "(" + equServerQuery.getUnitname() + "),";
                }else{
                    data += equServerQuery.getDatatypename() + ",";
                }
            }

            if(data.length() > 1){
                data = data.substring(0, data.length() - 1);
            }


            equServerequipmentsQuery.setData(data);

            equServerequipmentsQueryList.add(equServerequipmentsQuery);

        }

        return equServerequipmentsQueryList;
    }
}
