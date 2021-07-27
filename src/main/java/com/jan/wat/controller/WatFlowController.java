package com.jan.wat.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jan.wat.pojo.WatFlowtype;
import com.jan.wat.pojo.vo.FlowViewQuery;
import com.jan.wat.service.IWatFlowService;
import com.jan.wat.service.IWatFlowtypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Controller
@RequestMapping("/wat/flow")
public class WatFlowController {

    @Autowired
    IWatFlowService iWatFlowService;

//    @ApiOperation(value = "")
//    @GetMapping("/getByCondition/{equipmentGroupId}/{equipmentId}")
//    @ResponseBody
//    public List<FlowViewQuery> getAllFlowtypeList(@PathVariable String equipmentGroupId, @PathVariable String equipmentId){
//        return iWatFlowService.getAllFlowtypeList(equipmentGroupId,equipmentId);
//    }

}
