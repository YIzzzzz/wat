package com.jan.wat.pojo.vo;

import com.jan.wat.pojo.EquEquipmentgroup;
import com.jan.wat.pojo.SysOrganize;
import com.jan.wat.pojo.SysRole;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @hor:ZXJ
 * @time:2021/8/2下午8:05
 * @description
 */
@Data
public class RoleTree extends SysRoleeditQuery{

    private List<RoleTree> children = new ArrayList<>();

    public RoleTree(){}
    public RoleTree(SysRoleeditQuery query){
        super(query);
    }

    private static Map<String,List<Integer>> map;
    private static List<Integer> memory;

    public static void createTree(List<SysRoleeditQuery> list, List<RoleTree> tree){
        map = new HashMap<>();
        memory = new ArrayList<>();

        for(SysRoleeditQuery event : list){
            map.put(event.getMenucode(),new ArrayList<>());
        }
        int index = 0;
        for(SysRoleeditQuery event : list){
            if(event.getParentcode().equals("0") || event.getParentcode().equals("")){
                memory.add(index++);
                tree.add(new RoleTree(event));
                continue;
            }
            map.get(event.getParentcode()).add(index++);
        }
        index = 0;
        for(int i : memory) {
            dfs(i, list, tree.get(index++).getChildren());
        }
    }

    public static void dfs(int index, List<SysRoleeditQuery> list, List<RoleTree> tree){

        String current = list.get(index).getMenucode();

        for(int branch : map.get(current)){
            RoleTree roleTree = new RoleTree(list.get(branch));
            tree.add(roleTree);
            dfs(branch,list,roleTree.getChildren());
        }

    }

}
