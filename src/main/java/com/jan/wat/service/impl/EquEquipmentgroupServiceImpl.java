package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquEquipmentgroup;
import com.jan.wat.mapper.EquEquipmentgroupMapper;
import com.jan.wat.pojo.vo.EquipmentTree;
import com.jan.wat.service.IEquEquipmentgroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("EquEquipmentgroupServiceImpl")
public class EquEquipmentgroupServiceImpl extends ServiceImpl<EquEquipmentgroupMapper, EquEquipmentgroup> implements IEquEquipmentgroupService {

    @Autowired
    EquEquipmentgroupMapper equEquipmentgroupMapper;

    @Override
    public List<EquipmentTree> createTree(String userCode) {

        List<EquEquipmentgroup> list = equEquipmentgroupMapper.getListByUser(userCode);
        List<EquipmentTree> tree = new ArrayList<>();

        EquipmentTree.createTree(list,tree);

        return tree;
    }
    private List<EquipmentTree> children = new ArrayList<EquipmentTree>();

    public List<Integer> getChildrenGroupId(String userCode, String euipmentGroupID){
        children = new ArrayList<EquipmentTree>();
        List<Integer> list = new ArrayList<>();

        List<EquipmentTree> tree = createTree(userCode);


        find(euipmentGroupID, tree);

        if(children == null)
            return null;
        addList(list);
        return list;
    }

    private void find (String euipmentGroupID, List<EquipmentTree> tree){
        if(tree == null)
            return;
        for(EquipmentTree e : tree){
            if(euipmentGroupID.equals(String.valueOf(e.getId()))){
                children = e.getChildren();
                return;
            }
            find(euipmentGroupID, e.getChildren());
        }
        return;
    }

    private void addList(List<Integer> list){
        if(children == null)
            return;
        for( EquipmentTree e : children){
            list.add(e.getId());
        }
    }

}
