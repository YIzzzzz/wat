package com.jan.wat.controller;

import com.jan.wat.pojo.EquSim;
import com.jan.wat.service.IEquSimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * phm
 * 2021/7/20 上午10:53
 * 1.0
 */

@CrossOrigin
@RequestMapping("equ/simalarmbyuser")
@RestController
public class EquSimalarmbyuser {

    @Autowired
    IEquSimService equSimService;

    @GetMapping("getall")
    public List<EquSim> getAllEquSimalarmbyuserlist()
    {
        return equSimService.list();
    }
}
