package com.jan.wat.pojo.vo;

import com.jan.wat.pojo.EquPara;
import com.jan.wat.pojo.EquParagroup;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @hor:ZXJ
 * @time:2021/7/27上午10:52
 * @description
 */
@Data
public class ParaTree extends EquParagroup implements Serializable {

    public ParaTree(EquParagroup equParagroup){
        super(equParagroup);
    }

    public ParaTree(){
        super();
    }

    List<EquPara> children = new ArrayList<>();
}
