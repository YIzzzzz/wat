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
}
