package com.jan.wat.service.impl;

import com.jan.wat.pojo.WatFlow;
import com.jan.wat.mapper.WatFlowMapper;
import com.jan.wat.pojo.vo.FlowViewQuery;
import com.jan.wat.service.IEquEquipmentgroupService;
import com.jan.wat.service.IWatFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("WatFlowServiceImpl")
public class WatFlowServiceImpl extends ServiceImpl<WatFlowMapper, WatFlow> implements IWatFlowService {

    @Autowired
    WatFlowMapper watFlowMapper;

    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;

    @Override
    public List<FlowViewQuery> getAllFlow(String usercode, String equipment_id, String equipmentgroup_id) {
        StringBuilder set = new StringBuilder();
        if(equipmentgroup_id != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroup_id);
            int count = 0;
            for(int i : childrengroupId){
                count++;
                set.append(String.valueOf(i));
                if(count != childrengroupId.size())
                    set.append(",");
            }
        }

        return watFlowMapper.getAllFlow(usercode,set.toString(),equipment_id,equipmentgroup_id);
    }

    @Override
    public List<Integer> getAlarmtypeid(String equipment_id) {
        return watFlowMapper.getAlarmtypeid(equipment_id);
    }

//    @Override
//    public List<FlowViewQuery> getAllFlow() {
//        return watFlowMapper.getAllFlow();
//    }

//    @Override
//    public List<FlowViewQuery> getAllFlowtypeList(String equipmentGroupId, String equipmentId) {
//        return watFlowMapper.getByCondition(equipmentGroupId, equipmentId);
//    }
}
