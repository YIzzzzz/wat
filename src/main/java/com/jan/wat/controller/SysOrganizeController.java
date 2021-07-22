package com.jan.wat.controller;

import com.jan.wat.pojo.CreateTree;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.SysOrganize;
import com.jan.wat.pojo.WatClassify;
import com.jan.wat.service.ISysOrganizeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * wsk
 */
@CrossOrigin
@RestController
@RequestMapping("/sys/organize")
public class SysOrganizeController {

    @Autowired
    ISysOrganizeService iSysOrganizeService;

    @ApiOperation(value = "查询组织机构列表")
    @GetMapping("/getall")
    public List<CreateTree>getAllEquSimpackagelist(){
        List<SysOrganize> list = iSysOrganizeService.list();
        List<CreateTree> trees = new ArrayList<>();

        func("0",0,list,trees);
        return trees;
    }

    static List<String> F = new ArrayList<>();
    static List<List<CreateTree>> T = new ArrayList<>();

    public void func(String father, int index, List<SysOrganize> list, List<CreateTree> trees){

        if(index == list.size())
            return;
        SysOrganize s = list.get(index);
        CreateTree tree = new CreateTree(s);

        if(s.getParentcode().equals(father)){
            trees.add(tree);
            F.add(father);
            T.add(trees);
            func(s.getOrganizecode(),index+1, list, tree.getChildren());
        }else{
            String fa = F.remove(F.size()-1);
            List<CreateTree> tr = T.remove(T.size()-1);
            func(fa,index,list,tr);
        }
        return;
    }

    @ApiOperation(value = "添加组织机构信息")
    @PostMapping("/add")
    public RespBean addSysOrganize(@RequestBody SysOrganize sysOrganize){
        if (iSysOrganizeService.save(sysOrganize)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新组织机构信息")
    @PutMapping("/update")
    public RespBean updateSysOrganize(@RequestBody SysOrganize sysOrganize){
        if (iSysOrganizeService.updateById(sysOrganize)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "批量更新组织机构信息")
    @PutMapping("updatebatch")
    public RespBean updateBatchSysOrganize(@RequestBody List<SysOrganize> sysOrganizeList){
        if(iSysOrganizeService.updateBatchById(sysOrganizeList)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除组织机构信息")
    @DeleteMapping("/{id}")
    public RespBean deleteSysOrganize(@PathVariable String id){
        if (iSysOrganizeService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除组织机构信息")
    @DeleteMapping("/delete")
    public RespBean deleteSysOrganizeByIds(@RequestBody Integer[] ids){
        if (iSysOrganizeService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
