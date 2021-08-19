package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquAlarmrecord;
import com.jan.wat.mapper.EquAlarmrecordMapper;
import com.jan.wat.pojo.vo.EquAlarmQuery;
import com.jan.wat.service.IEquAlarmrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jan.wat.service.IEquEquipmentgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
@Service("EquAlarmrecordServiceImpl")
public class EquAlarmrecordServiceImpl extends ServiceImpl<EquAlarmrecordMapper, EquAlarmrecord> implements IEquAlarmrecordService {

    @Autowired
    EquAlarmrecordMapper equAlarmrecordMapper;

    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;

    @Override
    public List<EquAlarmQuery> getEquUnrecoveryalarm(String usercode, String equipment_id, String equipmentgroup_id) {
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
        return equAlarmrecordMapper.getEquUnrecoveryalarm(usercode,set.toString(),equipment_id,equipmentgroup_id);
    }

    @Override
    public List<EquAlarmQuery> getEquYesterdayalarm(String usercode, String equipment_id, String equipmentgroup_id) {
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
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime minus = localDateTime.minus(Period.ofDays(1));
        String start = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
        String end = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"));
//        String start = "2021-02-01 00:00:00";
//        String end = "2021-02-01 23:59:59";

        return equAlarmrecordMapper.getEquYesterdayalarm(usercode,start,end,set.toString(),equipment_id,equipmentgroup_id);
    }
}
