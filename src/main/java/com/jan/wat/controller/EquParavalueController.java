package com.jan.wat.controller;

import com.jan.wat.pojo.vo.ParaTree;
import com.jan.wat.service.IEquParaService;
import com.jan.wat.service.IEquParavalueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/7/27上午10:09
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/paravalue")
public class EquParavalueController {

    @Autowired
    IEquParaService iEquParaService;

    @ApiOperation(value = "获取参数树")
    @GetMapping("/getTree")
    public List<ParaTree> getTree(){
        return iEquParaService.getTree();
    }

}
