package com.jan.wat.service;

import com.jan.wat.pojo.EquEquipmentgroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.EquipmentTree;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
public interface IEquEquipmentgroupService extends IService<EquEquipmentgroup> {

    public List<EquipmentTree> createTree(String userCode);

}
