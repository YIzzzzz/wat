package com.jan.wat.controller;

import com.jan.wat.pojo.*;
import com.jan.wat.service.*;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

/**
 * wsk
 */
@RestController
@CrossOrigin
@RequestMapping("/equ/paragroup")
public class EquParagroupController {

    @Autowired
    IEquParagroupService iEquParagroupService;
}
