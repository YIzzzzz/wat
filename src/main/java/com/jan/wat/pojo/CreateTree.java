package com.jan.wat.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

@Data
public class CreateTree {

    private String organizecode;

    private String parentcode;

    private String organizeseq;

    private String organizename;

    private String description;

    private String createperson;

    private Date createdate;

    private String updateperson;

    private Date updatedate;

    private List<CreateTree> children = new ArrayList<>(); //child

    public CreateTree() {

    }

    public CreateTree(SysOrganize s){
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


    private static Stack<String> fatherNodeStack = new Stack<>();
    private static Stack<List<CreateTree>> treesNodeStack = new Stack<>();
    private static List<CreateTree> treesNode;
    private static String fatherNode;

    public static void createTree(String father, int index, List<SysOrganize> list, List<CreateTree> trees){

        if(index == list.size())
            return;
        SysOrganize s = list.get(index);
        CreateTree tree = new CreateTree(s);

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



}
