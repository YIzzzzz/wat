package com.jan.wat.controller;

import com.jan.wat.pojo.CreateTree;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.SysOrganize;
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
        createTree(trees, list);
        return trees;
    }
    public List<CreateTree> createTree(List<CreateTree> trees, List<SysOrganize> list)
    {
        trees.add(new CreateTree(list.get(0)));
        func(list,trees);
        return trees;
    }
//    public SysOrganize getLastNode(List<SysOrganize> list, int index)
//    {
//        return list.get(index-1);
//    }
    public void func(List<SysOrganize> list, List<CreateTree> trees){

        for (int index =1; index < list.size(); index++)
        {
            if(list.get(index).getParentcode().equals(list.get(index-1).getParentcode()))
            {
                trees.add(new CreateTree(list.get(index)));
            }
            else
            {
                for (int i = 0; i < list.size(); i++)
                {
                    if (list.get(index).getParentcode().equals(list.get(i).getParentcode())){
                        break;
                    }
                }
                trees.add(new CreateTree(list.get(index)));
            }
        }

        /*if(index == list.size())
            return;
*/
        /*SysOrganize s = list.get(index);
        CreateTree tree = new CreateTree(s);

        if(s.getParentcode().equals(father)){
            trees.add(tree);
            func(s.getOrganizecode(),index+1, list, tree.getChildren());
        }else{
            func(s.getParentcode(),index, list, trees);
        }*/

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
