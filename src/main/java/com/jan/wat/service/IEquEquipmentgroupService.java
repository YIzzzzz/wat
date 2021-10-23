package com.jan.wat.service;

import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.EquEquipmentgroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.RespBean;
import com.jan.wat.pojo.vo.EquipmentTree;
import org.springframework.web.bind.annotation.RequestBody;

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

    public List<String> getChildrenId(String userCode, String euipmentGroupID);
    public List<Integer> getChildrenGroupId(String userCode, String euipmentGroupID);

    public Integer getMaxId();

}
