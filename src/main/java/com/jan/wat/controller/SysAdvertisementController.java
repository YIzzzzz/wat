package com.jan.wat.controller;


import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import sun.java2d.pipe.AAShapePipe;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * wsk
 */
@RestController
@CrossOrigin
@RequestMapping("/sys/advertisement")
public class SysAdvertisementController {

    @Autowired
    ISysAdvertisementService iSysAdvertisementService;

    @ApiOperation(value = "查询广告管理")
    @GetMapping("getall")
    public List<SysAdvertisement> getSysAdvertisement(){
        return iSysAdvertisementService.list();
    }

    @ApiOperation(value = "添加广告管理")
    @PostMapping("/add")
    public RespBean addSysAdvertisement(@RequestBody SysAdvertisement sysAdvertisement){
        if(iSysAdvertisementService.save(sysAdvertisement)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更改广告管理信息")
    @PutMapping("/update")
    public RespBean updateSysAdvertisement(@RequestBody SysAdvertisement sysAdvertisement){

        if (iSysAdvertisementService.updateById(sysAdvertisement)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新广告管理信息")
    @PutMapping("/updatebatch")
    public RespBean updateBatchSysAdvertisement(@RequestBody List<SysAdvertisement> sysAdvertisementList){

//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        for (SysAdvertisement sysAdvertisement : sysAdvertisementList){
            sysAdvertisement.setUpdatedate(new Date());
        }
        if(iSysAdvertisementService.updateBatchById(sysAdvertisementList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除广告管理信息")
    @DeleteMapping("/{id}")
    public RespBean deleteSysAdvertisement(@PathVariable String id){
        if (iSysAdvertisementService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除广告管理信息")
    @DeleteMapping("/delete")
    public RespBean deleteSysAdvertisementByIds(@RequestBody String[] ids){
        if (iSysAdvertisementService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
