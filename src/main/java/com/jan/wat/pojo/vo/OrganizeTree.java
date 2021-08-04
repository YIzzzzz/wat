package com.jan.wat.pojo.vo;


import com.jan.wat.pojo.EquEquipmentgroup;
import com.jan.wat.pojo.SysOrganize;
import lombok.Data;

import java.util.*;

@Data
public class OrganizeTree {

    private String organizecode;

    private String parentcode;

    private String organizeseq;

    private String organizename;

    private String description;

    private String createperson;

    private Date createdate;

    private String updateperson;

    private Date updatedate;

    private List<OrganizeTree> children = new ArrayList<>(); //child

    public OrganizeTree() {

    }

    public OrganizeTree(SysOrganize s){
        organizecode = s.getOrganizecode();
        parentcode = s.getParentcode();
        organizeseq = s.getOrganizeseq();
        organizename = s.getOrganizename();
        description = s.getDescription();
        createperson = s.getCreateperson();
        createdate = s.getCreatedate();
        updateperson = s.getUpdateperson();
        updatedate = s.getUpdatedate();
    }

    public static void init(){
        fatherNodeStack = new Stack<>();
        treesNodeStack = new Stack<>();
    }

    private static Stack<String> fatherNodeStack;
    private static Stack<List<OrganizeTree>> treesNodeStack;
    private static List<OrganizeTree> treesNode;
    private static String fatherNode;

    public static void createTree(String father, int index, List<SysOrganize> list, List<OrganizeTree> trees){

        if(index == list.size())
            return;
        SysOrganize s = list.get(index);
        OrganizeTree tree = new OrganizeTree(s);

        if(s.getParentcode().equals(father)){
            trees.add(tree);
            fatherNodeStack.add(father);
            treesNodeStack.add(trees);
            createTree(s.getOrganizecode(),index+1, list, tree.getChildren());
        }else{
            fatherNode = fatherNodeStack.pop();
            treesNode= treesNodeStack.pop();
            createTree(fatherNode,index,list,treesNode);
        }
        return;
    }

    private static Map<String,List<Integer>> map;
    private static List<Integer> memory;

    public static void createTree(List<SysOrganize> list, List<OrganizeTree> tree){
        map = new HashMap<>();
        memory = new ArrayList<>();

        for(SysOrganize event : list){
            map.put(event.getOrganizecode(),new ArrayList<>());
        }
        int index = 0;
        for(SysOrganize event : list){
            if(event.getParentcode().equals("0")){
                memory.add(index++);
                tree.add(new OrganizeTree(event));
                continue;
            }
            map.get(event.getParentcode()).add(index++);
        }
        index = 0;
        for(int i : memory) {
            dfs(i, list, tree.get(index++).getChildren());
        }
    }

    public static void dfs(int index, List<SysOrganize> list, List<OrganizeTree> tree){

        String current = list.get(index).getOrganizecode();

        for(int branch : map.get(current)){
            OrganizeTree organizeTree = new OrganizeTree(list.get(branch));
            tree.add(organizeTree);
            dfs(branch,list,organizeTree.getChildren());
        }

    }


}
