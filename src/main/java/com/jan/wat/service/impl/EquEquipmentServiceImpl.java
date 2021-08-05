package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquEquipment;
import com.jan.wat.mapper.EquEquipmentMapper;
import com.jan.wat.pojo.vo.EuipmentsQuery;
import com.jan.wat.service.IEquEquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jan.wat.service.IEquEquipmentgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-06-25
 */
@Service("EquEquipmentServiceImpl")
public class EquEquipmentServiceImpl extends ServiceImpl<EquEquipmentMapper, EquEquipment> implements IEquEquipmentService {

    @Autowired
    EquEquipmentMapper equEquipmentMapper;

    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;
    @Override
    public List<EuipmentsQuery> getEuipments(String euipmentGroupID, String equipmentId, String userCode) {
        StringBuilder where = new StringBuilder("");

        if(!euipmentGroupID.equals("")){
            List<Integer> childrenGroupId = iEquEquipmentgroupService.getChildrenGroupId(userCode, euipmentGroupID);
            if(childrenGroupId.size()>0){
                where.append(" and A.ID in (");
                int count = 0;
                int length = childrenGroupId.size();
                for(Integer i : childrenGroupId){
                    where.append(i);
                    count++;
                    if(count == length)
                        continue;
                    where.append(",");
                }
                where.append(")");
            }
        }
        if(!equipmentId.equals("0") || !equipmentId.equals("")){
            where.append(String.format(" and A.ID=%s",equipmentId));
        }

        return equEquipmentMapper.getEuipments(userCode, where.toString());
    }
}
