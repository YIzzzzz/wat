package com.jan.wat.controller;

import com.jan.wat.service.IEquSimpackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *phm
 *2021/7/16 下午5:23
 * 1.0
 */

@Controller
@CrossOrigin
@RequestMapping("/equ")
public class EquController {
    @Autowired
    IEquSimpackageService equSimpackageService;




}
