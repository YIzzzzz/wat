package com.jan.wat.pojo.vo;

import com.jan.wat.pojo.EquEquipmentgroup;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @hor:ZXJ
 * @time:2021/7/27下午4:22
 * @description
 */
@Data
public class EquipmentTree extends EquEquipmentgroup {

    private List<EquipmentTree> children = new ArrayList<>();


    public EquipmentTree(){}
    public EquipmentTree(EquEquipmentgroup equEquipmentgroup){
        super(equEquipmentgroup);
    }


    private static Map<Integer,List<Integer>> map;
    private static List<Integer> memory;

    public static void createTree(List<EquEquipmentgroup> list, List<EquipmentTree> tree){
        map = new HashMap<>();
        memory = new ArrayList<>();
        
        for(EquEquipmentgroup event : list){
            map.put(event.getId(),new ArrayList<>());
        }
        int index = 0;
        for(EquEquipmentgroup event : list){
            if(event.getParentid().equals("0")){
                memory.add(index++);
                tree.add(new EquipmentTree(event));
                continue;
            }
            map.get(Integer.valueOf(event.getParentid())).add(index++);

        }
        index = 0;
        for(int i : memory) {
            bfs(i, list, tree.get(index++).getChildren());
        }
    }

    public static void bfs(int index, List<EquEquipmentgroup> list, List<EquipmentTree> tree){

        int current = list.get(index).getId();
        for(int branch : map.get(current)){
            EquipmentTree equipmentTree = new EquipmentTree(list.get(branch));
            tree.add(equipmentTree);
            bfs(branch,list,equipmentTree.getChildren());
        }

    }

}
