package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquCommand;
import com.jan.wat.mapper.EquCommandMapper;
import com.jan.wat.pojo.vo.EquUncheckcommandQuery;
import com.jan.wat.service.IEquCommandService;
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
 * @since 2021-07-06
 */
@Service("EquCommandServiceImpl")
public class EquCommandServiceImpl extends ServiceImpl<EquCommandMapper, EquCommand> implements IEquCommandService {

    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;
    @Autowired
    EquCommandMapper equCommandMapper;

    @Override
    public List<EquUncheckcommandQuery> getEquUncheckcommand(String usercode, String equipmentId, String equipmentgroupId) {
        List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroupId);
        StringBuilder set = new StringBuilder();
        int count = 0;
        for(int i : childrengroupId){
            count++;
            set.append(String.valueOf(i));
            if(count != childrengroupId.size())
                set.append(",");
        }

        return equCommandMapper.getEquUncheckcommand(usercode,set.toString(),equipmentId,equipmentgroupId);
    }
}
