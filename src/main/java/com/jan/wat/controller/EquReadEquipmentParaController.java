package com.jan.wat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.EquEquipmentgroup;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.vo.EquipmentTree;
import com.jan.wat.pojo.vo.EuipmentsQuery;
import com.jan.wat.service.IEquEquipmentService;
import com.jan.wat.service.IEquEquipmentgroupService;
import com.jan.wat.service.IEquParaService;
import com.jan.wat.service.IEquParavalueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/7/27下午4:51
 * @description
 */
@CrossOrigin
@RestController
@RequestMapping("equ/readequipmentpara")
public class EquReadEquipmentParaController {
    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;
    @Autowired
    IEquEquipmentService iEquEquipmentService;
    @Autowired
    IEquParaService iEquParaService;

    @ApiOperation(value = "获取全部设备树")
    @GetMapping("/getTree")
    public List<EquipmentTree> getAllTree(){

        List<EquEquipmentgroup> list = iEquEquipmentgroupService.list();
        List<EquipmentTree> tree = new ArrayList<>();
        EquipmentTree.createTree(list,tree);
        return tree;
    }

    @ApiOperation(value = "获取设备树")
    @GetMapping("/getTree/{userCode}")
    public List<EquipmentTree> getTree(@PathVariable String userCode){
        return iEquEquipmentgroupService.createTree(userCode);
    }



    @ApiOperation(value="获取设备列表")
    @GetMapping("/getEquipmentList/{userCode}/{equipemtnGroupId}")
    public List<String> getEquipmentList(@PathVariable String userCode, @PathVariable String equipemtnGroupId){
        return iEquEquipmentgroupService.getChildrenId(userCode,equipemtnGroupId);
    }



    @ApiOperation(value = "查询参数值管理")
    @GetMapping("/getequipments/{euipmentGroupID}/{equipmentId}/{userCode}")
    public List<EuipmentsQuery> getEuipments(@PathVariable String euipmentGroupID, @PathVariable String equipmentId, @PathVariable String userCode){
        return iEquEquipmentService.getEuipments(euipmentGroupID, equipmentId,userCode);
    }

    @ApiOperation(value = "添加参数")
    @PostMapping("/addParaCommands")
    @ResponseBody
    public RespBean addParaCommands(@RequestBody JSONObject json){

        if (iEquParaService.addParaCommands(json)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }



}
