package com.jan.wat.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

}
